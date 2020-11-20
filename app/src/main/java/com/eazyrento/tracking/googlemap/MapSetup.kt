package com.eazyrento.tracking.googlemap

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.view.animation.LinearInterpolator
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import java.util.ArrayList

class MapSetup(private val mContext: Context,private val mMap:GoogleMap) {

    private  var originLatLang:LatLng = LatLng(22.1, 77.4)
    private lateinit var startPosition: LatLng
    private lateinit var endPosition:LatLng
    /*
    * Google Map setting
    * */
    fun mapSetting(){

        mMap.run {
            mapType = GoogleMap.MAP_TYPE_NORMAL
            isTrafficEnabled = false
            isIndoorEnabled = false
            isBuildingsEnabled = false
            uiSettings.isZoomControlsEnabled = true
            uiSettings.setAllGesturesEnabled(true)
            uiSettings.isZoomGesturesEnabled = true
        }
    }

     fun newMarker(lat:Double , lang:Double) {
        // Add a marker in Home and move the camera
        val latlang = LatLng(lat, lang)

        mMap.run {

            // addMarker(MarkerOptions().position(sydney).title())
            addMarker(createNewMarker("Marker in Home",latlang, R.mipmap.ic_car))

            moveCamera(CameraUpdateFactory.newLatLng(latlang))
            moveCamera(
                CameraUpdateFactory.newCameraPosition(
                    cameraSetting().build()
                )
            )
        }
    }

     fun createNewMarker(title: String, location: LatLng,icon: Int): MarkerOptions {

        return MarkerOptions().apply{
            icon(getBitmapFromDrawable(icon))
            position(location)
            title(title)
        }
    }

    fun getBitmapFromDrawable(icon: Int): BitmapDescriptor? {

        return BitmapDescriptorFactory.fromResource(icon)
    }

     fun cameraSetting(): CameraPosition.Builder {

        return CameraPosition.Builder().apply {
            target(mMap.cameraPosition.target)
            zoom(17f)
            bearing(30f)
            tilt(45f)
        }

    }


    fun drawPolyLine(polyLineLatLongList: List<LatLng>){
        //Adjusting bounds focus on lang and lat
        adjustingBound(polyLineLatLongList)

        val grayPolylineOptions = polyLineOptionsSetting(Color.GRAY).addAll(polyLineLatLongList)

        val greyPolyLine = addPolylineOnGoogleMap(grayPolylineOptions)

        val  blackPolylineOptions = polyLineOptionsSetting(Color.BLACK)
        val  blackPolyline =  addPolylineOnGoogleMap(blackPolylineOptions)


        // destination
        mMap.addMarker(
            createNewMarker("Destination" ,polyLineLatLongList[polyLineLatLongList.size - 1],R.mipmap.mover_map_icon_)
        )
    }

    /*animate car*/
    // poly line
     fun drawPolyLineAndAnimateCar(polyLineLatLongList: List<LatLng>) {
        //Adjusting bounds focus on lang and lat
        adjustingBound(polyLineLatLongList)

        val grayPolylineOptions = polyLineOptionsSetting(Color.GRAY).addAll(polyLineLatLongList)

        val greyPolyLine = addPolylineOnGoogleMap(grayPolylineOptions)

        val  blackPolylineOptions = polyLineOptionsSetting(Color.BLACK)
        val  blackPolyline =  addPolylineOnGoogleMap(blackPolylineOptions)


        // destination
        mMap.addMarker(
            createNewMarker("Destination" ,polyLineLatLongList[polyLineLatLongList.size - 1],R.mipmap.mover_map_icon_)
        )

        // car animation

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

        val mCarmarker =  mMap.addMarker(
            createNewMarker("Origin",originLatLang,R.mipmap.ic_car).flat(true)
        )

        val handler = Handler()
        var index = -1
        var  next = 1
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
                    val v = valueAnimator.animatedFraction
                    val lng = v * endPosition.longitude + (1 - v) * startPosition.longitude
                    val lat = v * endPosition.latitude + (1 - v) * startPosition.latitude

                    val newPos = LatLng(lat, lng)
                    /*val currentJourneyEvent = CurrentJourneyEvent()
                    currentJourneyEvent.setCurrentLatLng(newPos)
                    JourneyEventBus.getInstance().setOnJourneyUpdate(currentJourneyEvent)*/
                    AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, "Current journey")

                    mCarmarker.position = newPos
                    mCarmarker.setAnchor(0.5f, 0.5f)
                    mCarmarker.rotation = getBearing(startPosition, newPos)
                    /*  mMap.animateCamera(
                         CameraUpdateFactory.newCameraPosition(
                             CameraPosition.Builder().target(newPos)
                                 .zoom(15.5f).build()
                         )
                     )*/
                }
                valueAnimator.start()
                if (index !=   polyLineLatLongList.size - 1) {
                    handler.postDelayed(this, 1000)
                }
            }
        }, 1000)
    }

    private fun adjustingBound(polyLineLatLongList: List<LatLng>) {
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
            width(15f)
            startCap(SquareCap())
            endCap(SquareCap())
            jointType(JointType.ROUND)

        }
        return polylineOptions
    }

    private fun addPolylineOnGoogleMap(polylineOptions: PolylineOptions): Polyline {
        return mMap.addPolyline(polylineOptions)
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


    fun decodePoly(encoded: String): List<LatLng> {
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
}

