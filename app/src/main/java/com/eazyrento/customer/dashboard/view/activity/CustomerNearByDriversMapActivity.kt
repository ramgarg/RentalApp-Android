package com.eazyrento.customer.dashboard.view.activity


import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.common.model.modelclass.DriverList
import com.eazyrento.common.view.LiveDataActivityClass
import com.eazyrento.common.viewmodel.DriverListingViewModel
import com.eazyrento.customer.utils.Common.Companion.setCurrentAddressOnTopInMap
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.tracking.data.model.LatLong

import com.eazyrento.tracking.googlemap.BaseNearByDriversMapActivity
import com.google.android.gms.maps.GoogleMap

import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.view_map_top_location_card.*


class CustomerNearByDriversMapActivity : BaseNearByDriversMapActivity(), OnMapReadyCallback {


    override fun nearByDriver(it: LiveDataActivityClass): LiveData<DataWrapper<DriverList>> {
        return it.callAPIActivity<DriverListingViewModel>(this)
            .getNearByDriversToCustomer(lati, longi)
    }

    override fun onCurrentLocation(currentLatLng: LatLong?) {

        tv_address_line_map.setCurrentAddressOnTopInMap(
            this@CustomerNearByDriversMapActivity,
            lati,
            longi
        )
        nearByDriverApi()
    }

    /* on create */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_nearby_drivers_maps)

        topBarWithBackIconAndTitle(resources.getString(R.string.near_by_driver))

        initBaseMapData()

    }

    override fun onMapReady(googleMap: GoogleMap) {
        super.onMapReady(googleMap)
    }

    fun bookingButtonClick(view: View) {
        MoveToAnotherComponent.moveToActivityWithIntentValue<CustomerMainActivity>(
            this,
            Constant.KEY_INTENT_NEAR_BY_DRIVER, Constant.VALUE_INTENT_NEAR_BY_DRIVER
        )
    }

    override fun <T> onSuccessApiResult(data: T) {
        super.onSuccessApiResult(data)

    }


}