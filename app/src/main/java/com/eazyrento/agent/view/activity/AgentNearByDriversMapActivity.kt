package com.eazyrento.agent.view.activity


import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.agent.model.modelclass.AssignMerchantsReqModel
import com.eazyrento.agent.model.modelclass.Merchant
import com.eazyrento.agent.viewmodel.AgentAssignMerchantViewModel
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.common.model.modelclass.BookingListItem
import com.eazyrento.common.model.modelclass.DriverList
import com.eazyrento.common.model.modelclass.Drivers
import com.eazyrento.common.view.LiveDataActivityClass
import com.eazyrento.common.viewmodel.DriverListingViewModel
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.Common.Companion.setCurrentAddressOnTopInMap
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.tracking.data.model.LatLong

import com.eazyrento.tracking.googlemap.BaseNearByDriversMapActivity
import com.google.android.gms.maps.GoogleMap

import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.Marker
import com.google.gson.JsonElement
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_nearby_drivers_maps.*
import kotlinx.android.synthetic.main.dialog_assign_user_marker.*
import kotlinx.android.synthetic.main.phone_view.*
import kotlinx.android.synthetic.main.view_map_top_location_card.*


class AgentNearByDriversMapActivity : BaseNearByDriversMapActivity(), OnMapReadyCallback {

    private lateinit var  bookingITem: BookingListItem

    override fun nearByDriver(it:LiveDataActivityClass): LiveData<DataWrapper<DriverList>> {
        return it.callAPIActivity<DriverListingViewModel>(this)
            .getNearByDriversToAgent(bookingITem.id)
    }

    override fun onCurrentLocation(currentLatLng: LatLong?) {
        tv_address_line_map.setCurrentAddressOnTopInMap(
            this@AgentNearByDriversMapActivity,
            lati,
            longi
        )
        nearByDriverApi()
    }

    /* on create */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_nearby_drivers_maps)

        booking_button_from_map.visibility = View.GONE

        bookingITem = intent.getParcelableExtra<BookingListItem>(Constant.BOOKING_SUMMERY_KEY)

        topBarWithBackIconAndTitle(resources.getString(R.string.near_by_driver))

        initBaseMapData()

    }

    private fun assignTheDriver(driver: Drivers) {

        //driverList?.run {


        val merchant = Merchant(bookingITem.product_detail.starting_price,
            driver.details.merchantDetails.id,bookingITem.product_detail.starting_price,1)

        val  assignMerchantsReqModel = AssignMerchantsReqModel()

        assignMerchantsReqModel.booking_id = bookingITem.id
        assignMerchantsReqModel.order_id  = bookingITem.order_id
        assignMerchantsReqModel.merchant_list = arrayListOf(merchant)


        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<AgentAssignMerchantViewModel>(this@AgentNearByDriversMapActivity)
                    .assignMerchants(assignMerchantsReqModel)
                , this@AgentNearByDriversMapActivity, this@AgentNearByDriversMapActivity
            )
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        super.onMapReady(googleMap)
        setMarkerClickLisetener()
    }

    private fun setMarkerClickLisetener() {


        mMap?.setOnMarkerClickListener {

//            val driver = mHasMapMarker[it.tag]

            driverList?.run {

                val index = it.tag as Int
                val driver = driversList[index]

                assign_diver_from_map.visibility = View.VISIBLE
                driverPopUp(driver)

                true
            }
            false
        }
    }

    private fun driverPopUp(driver: Drivers){

        val merchantDetails = driver.details.merchantDetails

        // set data
        tv_distance.text = driver.details.distance.toString().plus(" km")

        tv_quantity.text = bookingITem.product_detail.quantity.toString()

        tv_starting_price.text = resources.getString(R.string.dollar).plus( bookingITem.product_detail.starting_price)
        tv_user_name.text = merchantDetails.fullName
        Picasso.with(this).load(merchantDetails.profileImage).into(customer_profile_pic)

        //listeners
        phone_view.setOnClickListener{
            Common.phoneCallWithNumber(merchantDetails.mobileNumber,this@AgentNearByDriversMapActivity)
        }
        img_close_on_map.setOnClickListener{
            assign_diver_from_map.visibility = View.GONE
        }

        btn_assign_driver.setOnClickListener {
            assignTheDriver(driver)
        }
    }

    override fun <T> onSuccessApiResult(data: T) {
        super.onSuccessApiResult(data)
        if (data is JsonElement){

            MoveToAnotherComponent.moveToActivityWithIntentValue<AgentMainActivity>(this,Constant.INTENT_AGENT_NEAR_BY_DRIVER_ASSIGNED,2)
            showToast(R.string.REQUEST_SUCCESSED)
            return
        }
    }

}