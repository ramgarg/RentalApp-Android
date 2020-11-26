package com.eazyrento.tracking.googlemap


import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.appbiz.location.LocationUtils.Companion.isGPSEnabled

import com.eazyrento.R
import com.eazyrento.appbiz.*

import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.common.model.modelclass.DriverList

import com.eazyrento.common.view.BaseActivity
import com.eazyrento.common.view.LiveDataActivityClass
import com.eazyrento.customer.utils.Common.Companion.setCurrentAddressOnTopInMap
import com.eazyrento.tracking.data.model.LatLong
import com.eazyrento.tracking.googlemap.mylocation.AppBizLocationCallback
import com.eazyrento.tracking.googlemap.mylocation.AppBizLocationProvider
import com.google.android.gms.location.LocationResult

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_nearby_drivers_maps.*
import kotlinx.android.synthetic.main.map_marker_custom_view.view.*
import kotlinx.android.synthetic.main.view_map_top_location_card.*
import java.lang.Exception
import kotlin.math.roundToInt


abstract class BaseNearByDriversMapActivity : LocationActivity(), OnMapReadyCallback {

    protected var driverList:DriverList?=null

    protected var mMap: GoogleMap? = null

   /* protected var mHashMapDriversLocation: HashMap<String, LatLong> = hashMapOf(
        "d1" to LatLong(22.1, 77.4),
        "d2" to LatLong(23.1, 78.4), "d3" to LatLong(24.1, 79.4), "d4" to LatLong(25.1, 86.4)
    )*/
    protected val mHasMapMarker = HashMap<Int, Marker>()

//    protected var mCurrentLatLng: LatLong? = null

    private val mTAG = "NearByDriversMapActivity:-"

    companion object {
        const val ZOOM_PREFERENCE = 5.0f
        const val ZOOM_LEVEL = 5
        const val DELAYS = 1000L
    }

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    /* on create */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    protected fun initBaseMapData(){
        mapInit()

    }



    /* init map*/
    private fun mapInit() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap


        requestForLocation()

    }


    private fun updateDistanceOnMarker(marker: Marker): Double {

        val calculatingDistance = CalculatingDistance()

        mCurrentLatLng?.run {

            val km = calculatingDistance.distance(
                latitude,
                longitude,
                marker.position.latitude,
                marker.position.longitude
            )

            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, "$mTAG updateMarkersData : $km")
            return km

        }
        return 0.0

    }

    private fun createNewMarker(title: String, location: LatLng): MarkerOptions {

        return MarkerOptions().apply {
            position(location)

        }
    }


    fun getBitmapDescriptorFactoryFromBitmap(bitmap: Bitmap): BitmapDescriptor? {
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    private fun setBitMapOnMarker() {

       driverList?.run {

        var index = 0
        while( index < driversList.size) {
            val driver = driversList[index].details
            val marker = setMarker(driver.latitude,driver.longitude)!!
            marker.tag = index
            mHasMapMarker[driver.driverId] = marker
            customMarkerDrivers(marker,driver.distance)
            index++
        }

        val builder = LatLngBounds.builder()
        for (value in mHasMapMarker.values) {
            builder.include(value.position)
        }

        mMap?.setOnMapLoadedCallback {
            val dim = resources.getDimension( R.dimen._100sdp).toInt()
            val cu = CameraUpdateFactory.newLatLngBounds(builder.build(),dim,dim,0)

            mMap?.moveCamera(cu)
        }

       }

    }

    private fun customMarkerDrivers(marker: Marker,distance:Double){
        val view = layoutInflater.inflate(R.layout.map_marker_custom_view, null)

        // marker update title
        //val disInDouble = updateDistanceOnMarker(marker)

        view.km_map.text = " ".plus((distance * 10.0).roundToInt() / 10.0).plus("km")

        val w = resources.getDimension(R.dimen._60sdp).toInt()
        val h = resources.getDimension(R.dimen._60sdp).toInt()

        val bitmap = AppBizCustomBitmapDes.loadBitmapFromView(view, w, h)

        Handler().postDelayed({

            marker.setIcon(getBitmapDescriptorFactoryFromBitmap(bitmap!!))

        }, 500)
    }

    private fun setMarker(lati:Double , longi:Double): Marker? {

        return mMap?.run {

            val markerLocationUpdate = this.addMarker(
                createNewMarker(
                    resources.getString(R.string.select_location),
                    LatLng(lati, longi)
                )
            )

            markerLocationUpdate
        }
    }

    override fun <T> onSuccessApiResult(data: T) {
        super.onSuccessApiResult(data)


        //driverList = DummeyJsonToObjectConvertor.convertJsonToClass(JsonDataResponse.jsonDriverListData)
        if (data is DriverList){
            driverList = data
        if (driverList!!.driversList.isEmpty()) {
            showToast(R.string.no_result_found)
            finish()
            return
        }
        setBitMapOnMarker()
    }
  }

    @SuppressLint("MissingPermission")
    override fun onCurrentLocation(currentLatLng: LatLong?) {

        mMap?.run {
            if(isMyLocationEnabled.not()){
                isMyLocationEnabled=true
                moveCameraOnSpecificLocation(mMap,currentLatLng)
                myCurrentButtonLocationPosition()
            }
        }
    }

    private fun myCurrentButtonLocationPosition(){
        try {
            val locationButton: View =
                (map.findViewById<View>("1".toInt()).getParent() as View).findViewById("2".toInt())

            val rlp = locationButton.getLayoutParams() as RelativeLayout.LayoutParams

            rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0)
            rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE)

            rlp.setMargins(
                0,
                resources.getDimension(R.dimen._200sdp).toInt(),
                resources.getDimension(R.dimen._200sdp).toInt(),
                0
            )
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

}