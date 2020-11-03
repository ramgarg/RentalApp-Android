package com.eazyrento.tracking.googlemap

import android.widget.Toast
import androidx.lifecycle.LiveData
import com.appbiz.location.LocationUtils.Companion.isGPSEnabled
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.common.model.modelclass.DriverList
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.common.view.LiveDataActivityClass
import com.eazyrento.customer.utils.Common.Companion.setCurrentAddressOnTopInMap
import com.eazyrento.tracking.data.model.LatLong
import com.eazyrento.tracking.googlemap.mylocation.AppBizLocationCallback
import com.eazyrento.tracking.googlemap.mylocation.AppBizLocationProvider
import com.google.android.gms.location.LocationResult
import kotlinx.android.synthetic.main.view_map_top_location_card.*

abstract class LocationActivity:BaseActivity()  {

    protected  var lati:Double = 0.0
    protected  var longi:Double = 0.0

    abstract  fun onCurrentLocation(currentLatLng: LatLong?)
    abstract fun nearByDriver(it: LiveDataActivityClass): LiveData<DataWrapper<DriverList>>

    private var appBizLocationProvider: AppBizLocationProvider? = null

    protected var mCurrentLatLng: LatLong? = null

    private val mTAG = "LocationActivity:-"



    /*
    *
    * requesting for location
    * */
    protected fun requestForLocation() {
        //location

        appBizLocationProvider = AppBizLocationProvider(this)

        appBizLocationProvider?.setLocationCallback(object : AppBizLocationCallback {
            override fun canRequestLocation(canRequest: Boolean) {
                if (canRequest)
                    appBizLocationProvider?.requestLocationUpdate(this@LocationActivity)
                else
                    Toast.makeText(
                        this@LocationActivity,
                        resources.getString(R.string.enable_GPS_permission),
                        Toast.LENGTH_SHORT
                    ).show()
            }

            override fun onLocation(locationResult: LocationResult?) {
                AppBizLogger.log(
                    AppBizLogger.LoggingType.DEBUG,
                    "$mTAG location result $locationResult"
                )


                locationResult?.lastLocation?.run {

                   /* tv_address_line_map.setCurrentAddressOnTopInMap(
                        this@LocationActivity,
                        latitude,
                        longitude
                    )*/

                        lati = latitude
                        longi = longitude

                    mCurrentLatLng = LatLong(latitude, longitude)

                    onCurrentLocation(mCurrentLatLng)

//                    setBitMapOnMarker()

                }

            }

            override fun permissionGiven() {
                if (isGPSEnabled())
                    appBizLocationProvider?.requestLocationUpdate(this@LocationActivity)
                else {
                    Toast.makeText(
                        this@LocationActivity,
                        resources.getString(R.string.enable_GPS_permission),
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }

            }

        })?.start()
    }

    /*
   * request for permission
   *
   * */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        appBizLocationProvider?.onRequestPermissionsResult(requestCode, permissions, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    /*
    * remove location callbacks
    * */
    override fun onDestroy() {
        appBizLocationProvider?.removeLocationCallbacks()
        super.onDestroy()
    }

    /*
    * Api calling
    *
    * */

    protected fun nearByDriverApi() {

        // dummy api testing
        // dummy json for testing
        /* driverList = DummeyJsonToObjectConvertor.convertJsonToClass(JsonDataResponse.jsonDriverListData)
         setBitMapOnMarker()*/


        callAPI()?.let {
            it.observeApiResult(
                nearByDriver(it)
                , this, this
            )
        }
    }

    override fun <T> onSuccessApiResult(data: T) {
        super.onSuccessApiResult(data)

    }
}