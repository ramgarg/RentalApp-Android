package com.eazyrento.merchant.view.activity

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazyrento.R
import com.eazyrento.appbiz.DummeyJsonToObjectConvertor
import com.eazyrento.appbiz.JsonDataResponse
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.common.model.modelclass.DriverList
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.common.view.LiveDataActivityClass
import com.eazyrento.common.viewmodel.DriverListingViewModel
import com.eazyrento.merchant.view.adapter.DriverAvailabilityAdapter
import com.eazyrento.tracking.data.model.LatLong
import com.eazyrento.tracking.googlemap.LocationActivity
import kotlinx.android.synthetic.main.activity_driver_availability.*

class DriverAvailabilityActivity :LocationActivity() {

    private lateinit var driverList:DriverList

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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_driver_availability)

        topBarWithBackIconAndTitle(getString(R.string.driver))

        requestForLocation()

        //driverList = DummeyJsonToObjectConvertor.convertJsonToClass(JsonDataResponse.jsonDriverListData)
        //setDriverAvailabilityAdapter()

    }

    private fun setDriverAvailabilityAdapter() {


        rec_driver_list.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)
        val recycleAdapter = DriverAvailabilityAdapter(driverList.driversList, this)
        rec_driver_list.adapter = recycleAdapter
    }

    override fun <T> onSuccessApiResult(data: T) {
        super.onSuccessApiResult(data)

        driverList = data as DriverList

        driverList = DummeyJsonToObjectConvertor.convertJsonToClass(JsonDataResponse.jsonDriverListData)

        setDriverAvailabilityAdapter()

    }
}