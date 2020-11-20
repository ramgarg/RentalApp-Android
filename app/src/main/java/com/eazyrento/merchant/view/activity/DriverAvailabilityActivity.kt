package com.eazyrento.merchant.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.appbiz.DummeyJsonToObjectConvertor
import com.eazyrento.appbiz.JsonDataResponse
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.common.model.modelclass.BookingListItem
import com.eazyrento.common.model.modelclass.DriverList
import com.eazyrento.common.model.modelclass.Drivers
import com.eazyrento.common.model.modelclass.MerchantAssignDriver
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.common.view.LiveDataActivityClass
import com.eazyrento.common.viewmodel.DriverListingViewModel
import com.eazyrento.customer.dashboard.model.modelclass.OrderDetailsResModel
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.login.viewmodel.LoginUserViewModel
import com.eazyrento.merchant.view.adapter.DriverAvailabilityAdapter
import com.eazyrento.merchant.viewModel.MerchantAssignDriverViewModel
import com.eazyrento.tracking.data.model.LatLong
import com.eazyrento.tracking.googlemap.LocationActivity
import kotlinx.android.synthetic.main.activity_driver_availability.*

class DriverAvailabilityActivity :LocationActivity() {

    private lateinit var driverList:DriverList
    private  var selectedDrivers: Drivers? = null

    private lateinit var orderDetailsResModel: OrderDetailsResModel

    override fun onCurrentLocation(currentLatLng: LatLong?) {
        currentLatLng?.run {
            lati = latitude
            longi = longitude
        }
        nearByDriverApi()
    }

    override fun nearByDriver(it: LiveDataActivityClass): LiveData<DataWrapper<DriverList>> {
        return it.callAPIActivity<DriverListingViewModel>(this)
            .getMerchantDrivers(lati, longi)
    }

    override fun <T> moveOnSelecetedItem(type: T) {
        selectedDrivers = type as Drivers

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_driver_availability)

        topBarWithBackIconAndTitle(getString(R.string.driver))

        requestForLocation()

        orderDetailsResModel = intent.getParcelableExtra(Constant.DRIVER_ASSIGN_ORDER_KEY)

        //driverList = DummeyJsonToObjectConvertor.convertJsonToClass(JsonDataResponse.jsonDriverListData)
        //setDriverAvailabilityAdapter()

    }

    private fun setDriverAvailabilityAdapter() {


        rec_driver_list.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)

        val recycleAdapter = DriverAvailabilityAdapter(driverList.driversList, this)

        rec_driver_list.adapter = recycleAdapter

        rec_driver_list.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
    }

    override fun <T> onSuccessApiResult(data: T) {
        super.onSuccessApiResult(data)

        if (data is DriverList) {
            driverList = data

            if (driverList.driversList.isEmpty()){
                showToast(R.string.no_result_found)
                finish()
                return
            }
            /*driverList =
                DummeyJsonToObjectConvertor.convertJsonToClass(JsonDataResponse.jsonDriverListData)*/

            setDriverAvailabilityAdapter()
        }else{
            showToast(R.string.REQUEST_SUCCESSED)

            MoveToAnotherComponent.moveToActivityWithIntentValue<MerchantMainActivity>(
                this,
                Constant.INTENT_DRIVER_ASSIGNED_BY_MERCHANT, 2
            )
            finish()
        }

    }

    fun assignSelectedDriverByMerchant(view:View){

        assginDriverApiCall()
    }

    private fun assginDriverApiCall() {

        selectedDrivers?.run {
            val merchantAssignDriver = MerchantAssignDriver(
                orderDetailsResModel.order_id,
                orderDetailsResModel.id, details.driverId
            )

            callAPI()?.let {
                it.observeApiResult(
                    it.callAPIActivity<MerchantAssignDriverViewModel>(this@DriverAvailabilityActivity)
                        .merchantAssignDrivers(merchantAssignDriver), this@DriverAvailabilityActivity, this@DriverAvailabilityActivity
                )
            }
          return
        }
      showToast(R.string.select_driver)
    }
}