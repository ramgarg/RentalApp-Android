package com.eazyrento.tracking.googlemap


import android.animation.ValueAnimator
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.view.animation.LinearInterpolator
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.tracking.data.api.GoogleApiCreation
import com.eazyrento.tracking.data.model.Response
import com.eazyrento.tracking.data.model.Route
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*


class TrackingMapActivity : BaseActivity(),OnMapReadyCallback {

    private  var sydney:LatLng = LatLng(22.1, 77.4)
    private var v = 0f
    private var lat:Double = 0.0
    private  var lng:Double = 0.0

    private lateinit var handler: Handler
    private lateinit var startPosition: LatLng
    private lateinit var endPosition:LatLng
    private  var index :Int =0
    private  var next:Int = 0

    private lateinit var mCarmarker: Marker
   // private var polylineOptions: PolylineOptions? = null
    //private  var blackPolylineOptions:PolylineOptions? = null

  //  private lateinit var blackPolyline: Polyline
    //private  lateinit var greyPolyLine:Polyline

    private lateinit var polyLineLatLongList: List<LatLng>
    private lateinit var mMap: GoogleMap
    private var mMarkerLocationUpdate:Marker?=null

    private val mTAG = "TrackingMapActivity:-"

    companion object{
        const val ZOOM_PREFERENCE= 12.0f
        const val ZOOM_LEVEL = 5
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

       // mMap?.setMaxZoomPreference(ZOOM_PREFERENCE)

        mapSetting()
        //newMarker(googleMap)
        GoogleDirectionApiCall()

    }

    private fun GoogleDirectionApiCall() {

        val latitude = 22.1
        val longitude = 77.4
        val origin = "Dolariya"
        val destination = "Seoni Malwa"


        GoogleApiCreation().getDirectionApi().getDirections(
            "driving", "less_driving",
            origin, destination,
            resources.getString(R.string.google_api_key)
        )?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : SingleObserver<Response?> {
                override fun onSubscribe(d: Disposable) {}
                override fun onSuccess(result: Response) {
                    val routeList: List<Route> = result.routes
                    for (route in routeList) {
                        val polyLine: String = route.overviewPolyline.points
                        polyLineLatLongList = decodePoly(polyLine)
                        drawPolyLineAndAnimateCar()
                    }
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
    }

    private fun decodePoly(encoded: String): List<LatLng> {
        val poly: MutableList<LatLng> = ArrayList()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0
        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat
            shift = 0
            result = 0
            do {
                b = encoded[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng
            val p = LatLng(
                lat.toDouble() / 1E5,
                lng.toDouble() / 1E5
            )
            poly.add(p)
        }
        return poly
    }

    private fun newMarker(googleMap: GoogleMap) {
        // Add a marker in Home and move the camera
         sydney = LatLng(22.1, 77.4)

        mMap?.run {

         // addMarker(MarkerOptions().position(sydney).title())
          addMarker(createNewMarker("Marker in Home",sydney,R.mipmap.ic_car))

          moveCamera(CameraUpdateFactory.newLatLng(sydney))
          moveCamera(
              CameraUpdateFactory.newCameraPosition(
                  cameraSetting(googleMap).build()
              )
          )
        }
    }

    private fun cameraSetting(googleMap: GoogleMap): CameraPosition.Builder {

        return CameraPosition.Builder().apply {
            target(googleMap.cameraPosition.target)
            zoom(17f)
            bearing(30f)
            tilt(45f)
        }

    }

    private fun mapSetting(){

        mMap?.run {
         mapType = GoogleMap.MAP_TYPE_NORMAL
         isTrafficEnabled = false
         isIndoorEnabled = false
         isBuildingsEnabled = false
         uiSettings.isZoomControlsEnabled = true
         uiSettings.setAllGesturesEnabled(true)
         uiSettings.isZoomGesturesEnabled = true
        }
    }


    private fun adjustingBound(){
        val builder = LatLngBounds.Builder()
        for (latLng in   polyLineLatLongList) {
            builder.include(latLng)
        }
        val bounds = builder.build()
        val mCameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 2)

        mMap.animateCamera(mCameraUpdate)
    }

    private fun polyLineOptionsSetting(color: Int): PolylineOptions {
       val polylineOptions = PolylineOptions()
        polylineOptions.run {
            color(color)
            width(5f)
            startCap(SquareCap())
            endCap(SquareCap())
            jointType(JointType.ROUND)

        }
        return polylineOptions
    }

    private fun addPolylineOnGoogleMap(polylineOptions: PolylineOptions): Polyline {
        return mMap.addPolyline(polylineOptions)
    }
    // poly line
    private fun drawPolyLineAndAnimateCar() {
        //Adjusting bounds focus on lang and lat
        adjustingBound()

        val grayPolylineOptions = polyLineOptionsSetting(Color.GRAY).addAll(polyLineLatLongList)

        val greyPolyLine = addPolylineOnGoogleMap(grayPolylineOptions)

        val  blackPolylineOptions = polyLineOptionsSetting(Color.BLACK)
        val  blackPolyline =  addPolylineOnGoogleMap(blackPolylineOptions)

       /*val  blackPolylineOptions = polyLineOptionsSetting(Color.BLACK)

       *//* blackPolylineOptions?.run {
            width(5f)
            color(Color.BLACK)
            startCap(SquareCap())
            endCap(SquareCap())
           jointType(JointType.ROUND)
        }*//*

        blackPolyline =  mMap.addPolyline(blackPolylineOptions)*/

        // destination
         mMap.addMarker(
            MarkerOptions()
                .position(polyLineLatLongList[polyLineLatLongList.size - 1])
        )
        val polylineAnimator = ValueAnimator.ofInt(0, 100)
        polylineAnimator.duration = 2000
        polylineAnimator.interpolator = LinearInterpolator()
        polylineAnimator.addUpdateListener { valueAnimator ->
            val points: List<LatLng> = greyPolyLine.points
            val percentValue = valueAnimator.animatedValue as Int
            val size = points.size
            val newPoints = (size * (percentValue / 100.0f)).toInt()
            val p = points.subList(0, newPoints)
            blackPolyline.points = p
        }
        polylineAnimator.start()

        // origin
        mCarmarker =  mMap.addMarker(
            MarkerOptions().position(sydney)
                .flat(true)
                .icon(getBitmapFromDrawable(R.mipmap.ic_car))

        )
        handler = Handler()
        index = -1
        next = 1
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (index <   polyLineLatLongList.size - 1) {
                    index++
                    next = index + 1
                }
                if (index <   polyLineLatLongList.size - 1) {
                    startPosition =   polyLineLatLongList[index]
                    endPosition =   polyLineLatLongList[next]
                }
                if (index == 0) {
                    /*val beginJourneyEvent = BeginJourneyEvent()
                    beginJourneyEvent.setBeginLatLng(startPosition)
                    JourneyEventBus.getInstance().setOnJourneyBegin(beginJourneyEvent)*/

                    AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, "Start journey")
                }
                if (index ==   polyLineLatLongList.size - 1) {
                    /* val endJourneyEvent = EndJourneyEvent()
                    endJourneyEvent.setEndJourneyLatLng(
                        LatLng(
                              polyLineList[index].latitude,
                              polyLineList[index].longitude
                        )
                    )
                    JourneyEventBus.getInstance().setOnJourneyEnd(endJourneyEvent)*/

                    AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, "end journey")
                }
                val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
                valueAnimator.duration = 3000
                valueAnimator.interpolator = LinearInterpolator()
                valueAnimator.addUpdateListener { valueAnimator ->
                    v = valueAnimator.animatedFraction
                    lng = v * endPosition.longitude + (1 - v) * startPosition.longitude
                    lat = v * endPosition.latitude + (1 - v) * startPosition.latitude

                    val newPos = LatLng(lat, lng)
                    /*val currentJourneyEvent = CurrentJourneyEvent()
                    currentJourneyEvent.setCurrentLatLng(newPos)
                    JourneyEventBus.getInstance().setOnJourneyUpdate(currentJourneyEvent)*/
                    AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, "Current journey")

                    mCarmarker.position = newPos
                    mCarmarker.setAnchor(0.5f, 0.5f)
                    mCarmarker.setRotation(getBearing(startPosition, newPos))
                   /*  mMap.animateCamera(
                        CameraUpdateFactory.newCameraPosition(
                            CameraPosition.Builder().target(newPos)
                                .zoom(15.5f).build()
                        )
                    )*/
                }
                valueAnimator.start()
                if (index !=   polyLineLatLongList.size - 1) {
                    handler.postDelayed(this, 100)
                }
            }
        }, 100)
    }

    private fun getBearing(begin: LatLng, end: LatLng): Float {
        val lat = Math.abs(begin.latitude - end.latitude)
        val lng = Math.abs(begin.longitude - end.longitude)
        if (begin.latitude < end.latitude && begin.longitude < end.longitude) return Math.toDegrees(
            Math.atan(lng / lat)
        )
            .toFloat() else if (begin.latitude >= end.latitude && begin.longitude < end.longitude) return (90 - Math.toDegrees(
            Math.atan(lng / lat)
        ) + 90).toFloat() else if (begin.latitude >= end.latitude && begin.longitude >= end.longitude) return (Math.toDegrees(
            Math.atan(lng / lat)
        ) + 180).toFloat() else if (begin.latitude < end.latitude && begin.longitude >= end.longitude) return (90 - Math.toDegrees(
            Math.atan(lng / lat)
        ) + 270).toFloat()
        return (-1).toFloat()
    }

    private fun createNewMarker(title: String, location: LatLng,icon: Int): MarkerOptions {

        return MarkerOptions().apply{
            setTitle(title)
            icon(getBitmapFromDrawable(icon))
            position(location)
        }
    }

    fun getBitmapFromDrawable(icon: Int): BitmapDescriptor? {

        return BitmapDescriptorFactory.fromResource(icon)
    }


    private fun setMarker(location: Location) {

        mMap?.run {

            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, "$mTAG setMarker")

            mMarkerLocationUpdate?.run {
                remove()
            }
            mMarkerLocationUpdate = this.addMarker(
                createNewMarker(
                    resources.getString(R.string.select_location), LatLng(
                        location.latitude,
                        location.longitude
                    ),R.mipmap.ic_car
                )
            )
            // bounds marker
            val builder = LatLngBounds.builder()

            builder.include(mMarkerLocationUpdate!!.position)

            // animate map
            this.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), ZOOM_LEVEL))
        }
    }

}