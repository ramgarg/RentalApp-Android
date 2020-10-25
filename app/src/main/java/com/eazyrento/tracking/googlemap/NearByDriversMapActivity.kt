package com.eazyrento.tracking.googlemap


import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.appbiz.location.LocationUtils.Companion.isGPSEnabled
import com.eazyrento.Constant

import com.eazyrento.R
import com.eazyrento.appbiz.AppBizCustomBitmapDes
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.appbiz.CalculatingDistance

import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.dashboard.view.activity.CustomerMainActivity
import com.eazyrento.customer.utils.Common.Companion.setCurrentAddressOnTopInMap
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.tracking.data.model.LatLong
import com.eazyrento.tracking.googlemap.mylocation.AppBizLocationCallback
import com.eazyrento.tracking.googlemap.mylocation.AppBizLocationProvider
import com.google.android.gms.location.LocationResult

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.map_marker_custom_view.*
import kotlinx.android.synthetic.main.view_map_top_location_card.*


class NearByDriversMapActivity : BaseActivity(),OnMapReadyCallback {

    private var appBizLocationProvider: AppBizLocationProvider?=null

    private lateinit var marker: Marker

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
*
* requesting for location
* */
    private fun requestForLocation(){
        //location

        appBizLocationProvider = AppBizLocationProvider(this)

        appBizLocationProvider?.setLocationCallback(object : AppBizLocationCallback {
            override fun canRequestLocation(canRequest: Boolean) {
                if (canRequest)
                    appBizLocationProvider?.requestLocationUpdate(this@NearByDriversMapActivity)
                else
                    Toast.makeText(this@NearByDriversMapActivity, resources.getString(R.string.enable_GPS_permission), Toast.LENGTH_SHORT).show()
            }

            override fun onLocation(locationResult: LocationResult?) {
                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"$mTAG location result $locationResult")
                locationResult?.lastLocation?.run {

                    tv_address_line_map.setCurrentAddressOnTopInMap(this@NearByDriversMapActivity,latitude,longitude)

                    // marker update title
                    updateMarkersData(latitude,longitude)
                }

            }

            override fun permissionGiven() {
                if (isGPSEnabled())
                    appBizLocationProvider?.requestLocationUpdate(this@NearByDriversMapActivity)
                else {
                    Toast.makeText(
                        this@NearByDriversMapActivity,
                        resources.getString(R.string.enable_GPS_permission),
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }

            }

        })?.start()
    }

  private fun updateMarkersData(currentLatitude: Double, currentLongitude: Double) {
      val calculatingDistance = CalculatingDistance()

     val values = mHashMapDriversLocation.values

     for (value in values) {

         val km = calculatingDistance.distance(
             currentLatitude,
             currentLongitude,
             value.latitude,
             value.longitude
         )

         AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, "$mTAG updateMarkersData : $km")
     }

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

        mMap?.setMaxZoomPreference(ZOOM_PREFERENCE)

        //subscribeToUpdates()

        // Add a marker in Sydney and move the camera
       /* val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/

         for (value in mHashMapDriversLocation.values){
             setMarker(value)
       }

        getBitMap()


    }

  /* private fun postDelayedMarker(){
       mHandler.postDelayed(mRunnable, DELAYS)
   }*/

   /*private fun getMarkerByKey(key:String): Marker? {
       return mHashMapMarkers[key]
   }*/
    private fun createNewMarker(title: String, location: LatLng): MarkerOptions {

//       val drawle =  ContextCompat.getDrawable(this@DisplayMapsActivity , R.drawable.ic_tracker)
      /* val view = layoutInflater.inflate(R.layout.map_marker_custom_view,null)
       val w = resources.getDimension(R.dimen._60sdp).toInt()
       val bitmap = AppBizCustomBitmapDes.getBitmapFromView(view,w,w)

       AppBizCustomBitmapDes.getBitmapFromView(view,this) {

       }*/
       return MarkerOptions().apply{
//            icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
//            icon(getBitmapFromDrawable(R.mipmap.mover_map_icon_))

//              icon(getBitmapDescriptorFactoryFromBitmap(bitmap!!))
              position(location)

        }
    }

     fun getBitmapFromDrawable(icon :Int): BitmapDescriptor? {

        return BitmapDescriptorFactory.fromResource(icon)
    }

    fun getBitmapDescriptorFactoryFromBitmap(bitmap:Bitmap): BitmapDescriptor? {
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
   private fun getBitMap(){

       val view = layoutInflater.inflate(R.layout.map_marker_custom_view,null)
       //val view = map_frame_layout
       val w = resources.getDimension(R.dimen._60sdp).toInt()
       //val bitmap = AppBizCustomBitmapDes.getBitmapFromView(view,200,200)
     //   val bitmap = AppBizCustomBitmapDes.getMarkerIconWithLabel("knnnn",0.0f,this)
       //val bitmap = AppBizCustomBitmapDes.makeBitmap(this,"dfbfnd")

       val bitmap = AppBizCustomBitmapDes.loadBitmapFromView(view)

//       val bitmap = AppBizCustomBitmapDes.getBitmapFromView(view,200,200)

       Handler().postDelayed({

               marker.setIcon(getBitmapDescriptorFactoryFromBitmap(bitmap!!))



         //  marker.setIcon(getBitmapDescriptorFactoryFromBitmap(bitmap!!))

       },2000)

   }

   private fun setMarker(location:LatLong) {

       mMap?.run {

       AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"$mTAG setMarker-$location")

/*       mMarkerLocationUpdate?.run {
           remove()
       }*/
       val mMarkerLocationUpdate = this.addMarker(createNewMarker(resources.getString(R.string.select_location),LatLng(location.latitude , location.longitude)))

           marker = mMarkerLocationUpdate
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