package com.eazyrento.tracking.googlemap


import android.os.Bundle
import android.view.View
import com.eazyrento.Constant

import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger

import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.dashboard.view.activity.CustomerMainActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.tracking.googlemap.model.modelclass.LatLong

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*


class NearByDriversMapActivity : BaseActivity(),OnMapReadyCallback {


    private var mMap: GoogleMap? =null
    private var mHashMapDriversLocation:HashMap<String,LatLong> = hashMapOf("d1" to LatLong(22.1,77.4),
        "d2" to LatLong(23.1,78.4),"d3" to LatLong(24.1,79.4),"d4" to LatLong(25.1,86.4))

    private val mTAG = "NearByDriversMapActivity:-"

    companion object{
        const val ZOOM_PREFERENCE= 5.0f
        const val ZOOM_LEVEL = 11
        const val DELAYS = 1000L
    }

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    /* on create */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_nearby_drivers_maps)

        topBarWithBackIconAndTitle(resources.getString(R.string.near_by_driver))

        mapInit()

    }

    /* init map*/
    private fun mapInit(){
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

        mMap?.setMaxZoomPreference(ZOOM_PREFERENCE)

        //subscribeToUpdates()

        // Add a marker in Sydney and move the camera
       /* val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/

         for (value in mHashMapDriversLocation.values){
             setMarker(value)
       }


    }

  /* private fun postDelayedMarker(){
       mHandler.postDelayed(mRunnable, DELAYS)
   }*/

   /*private fun getMarkerByKey(key:String): Marker? {
       return mHashMapMarkers[key]
   }*/
    private fun createNewMarker(title: String, location: LatLng): MarkerOptions {

//       val drawle =  ContextCompat.getDrawable(this@DisplayMapsActivity , R.drawable.ic_tracker)

       return MarkerOptions().apply{
            setTitle(title)
//            icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
           icon(getBitmapFromDrawable(R.mipmap.mover_map_icon_))
            position(location)
        }
    }

     fun getBitmapFromDrawable(icon :Int): BitmapDescriptor? {

        return BitmapDescriptorFactory.fromResource(icon)
    }


   private fun setMarker(location:LatLong) {

       mMap?.run {

       AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"$mTAG setMarker-$location")

/*       mMarkerLocationUpdate?.run {
           remove()
       }*/
       val mMarkerLocationUpdate = this.addMarker(createNewMarker(resources.getString(R.string.select_location),LatLng(location.latitude , location.longitude)))
       // bounds marker
       val builder = LatLngBounds.builder()
           builder.include(mMarkerLocationUpdate.position)

      /* for (objMarker in mHashMapDriversLocation.values){
           builder.include()
       }
*/
       builder.include(mMarkerLocationUpdate!!.position)
        animateMapCamera(builder)

       }
   }

 private fun animateMapCamera(builder: LatLngBounds.Builder) {
     // animate map
     mMap?.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), ZOOM_LEVEL))
 }

    fun bookingButtonClick(view:View){
        MoveToAnotherComponent.moveToActivityWithIntentValue<CustomerMainActivity>(this,
            Constant.KEY_INTENT_NEAR_BY_DRIVER, Constant.VALUE_INTENT_NEAR_BY_DRIVER)
    }
}