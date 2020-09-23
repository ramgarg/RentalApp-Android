package com.eazyrento.tracking.display

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizCustomBitmapDes
import com.eazyrento.appbiz.AppBizLogger
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class DisplayMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private var mDummyInt = 0.0
    private val  mHandler = Handler()

    private val mRunnable = Runnable{

        val lat = 22.44+mDummyInt
        val lang = 77.46+mDummyInt

        mDummyInt++

        setMarker(VehicleModel("1",lat,lang,"2"))

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"update map : lat $lat , lang $lang")

        postDelayedMarker(10000)

    }


    private lateinit var mMap: GoogleMap

    private val mHashMapMarkers = HashMap<String , Marker>()



    companion object{

        const val ZOOM_PREFERENCE= 12.0f
        const val ZOOM_LEVEL = 300
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_maps)

        mapInit()
    }

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

        mMap.setMaxZoomPreference(ZOOM_PREFERENCE)

        subscribeToUpdates()

        // Add a marker in Sydney and move the camera
       /* val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/


    }


   private fun subscribeToUpdates() {
       // call to API get updated location
       // Location[fused 22.448361,77.464468 ]
       postDelayedMarker(10000)
    }



   private fun postDelayedMarker(delay : Long){
       mHandler.postDelayed(mRunnable,delay)
   }

   private fun getMarkerByKey(key:String): Marker? {
       return mHashMapMarkers[key]
   }
    private fun createNewMarker(title: String, location: LatLng): MarkerOptions {

//       val drawle =  ContextCompat.getDrawable(this@DisplayMapsActivity , R.drawable.ic_tracker)

       return MarkerOptions().apply{
            setTitle(title)
            icon(AppBizCustomBitmapDes.getBitmapFromDrawable(R.mipmap.vehicle_marker))
            position(location)
        }
    }
   private fun setMarker(vehicleModel: VehicleModel) {

        val location = LatLng(vehicleModel.lat , vehicleModel.lang)
        // get exist marker
       if (mHashMapMarkers.containsKey(vehicleModel.key)){

           AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, "key contains ${vehicleModel.key}")

            getMarkerByKey(vehicleModel.key)?.position = location
       }else{

           AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, "key does not contains ${vehicleModel.key}")

           // add new marker
           mHashMapMarkers[vehicleModel.key] = mMap.addMarker(createNewMarker(vehicleModel.key,location))
       }


       // bounds marker
       val builder = LatLngBounds.builder()
       for (objMarker in mHashMapMarkers.values){
           builder.include(objMarker.position)
       }

       // animate map
//       mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), ZOOM_LEVEL))
   }
}