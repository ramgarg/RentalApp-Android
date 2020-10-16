package com.eazyrento.tracking.googlemap


import android.location.Location
import android.os.Bundle

import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger

import com.eazyrento.common.view.BaseActivity

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*


class TrackingMapActivity : BaseActivity(),OnMapReadyCallback {


    private var mMap: GoogleMap? =null
    private var mMarkerLocationUpdate:Marker?=null

    private val mTAG = "TrackingMapActivity:-"

    companion object{
        const val ZOOM_PREFERENCE= 12.0f
        const val ZOOM_LEVEL = 11
        const val DELAYS = 1000L
    }

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    /* on create */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_tracking_maps)

        topBarWithBackIconAndTitle(resources.getString(R.string.tracking))

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
            icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
           //icon(getBitmapFromDrawable(R.mipmap.ic_tracker))
            position(location)
        }
    }

     fun getBitmapFromDrawable(icon :Int): BitmapDescriptor? {

        return BitmapDescriptorFactory.fromResource(icon)
    }


   private fun setMarker(location:Location) {

       mMap?.run {

       AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"$mTAG setMarker")

       mMarkerLocationUpdate?.run {
           remove()
       }
       mMarkerLocationUpdate = this.addMarker(createNewMarker(resources.getString(R.string.select_location),LatLng(location.latitude , location.longitude)))
       // bounds marker
       val builder = LatLngBounds.builder()

       /*for (objMarker in mHashMapMarkers.values){
           builder.include(objMarker.position)
       }*/

       builder.include(mMarkerLocationUpdate!!.position)

       // animate map
       this.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), ZOOM_LEVEL))
       }
   }


}