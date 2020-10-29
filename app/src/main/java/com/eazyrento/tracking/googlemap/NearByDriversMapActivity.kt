package com.eazyrento.tracking.googlemap


import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.appbiz.location.LocationUtils.Companion.isGPSEnabled
import com.eazyrento.Constant

import com.eazyrento.R
import com.eazyrento.Session
import com.eazyrento.agent.model.modelclass.AssignMerchantsReqModel
import com.eazyrento.agent.viewmodel.AgentAssignMerchantViewModel
import com.eazyrento.appbiz.AppBizCustomBitmapDes
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.appbiz.CalculatingDistance
import com.eazyrento.common.model.modelclass.BookingListItem

import com.eazyrento.common.view.BaseActivity
import com.eazyrento.common.view.UserInfoAPP
import com.eazyrento.customer.dashboard.view.activity.CustomerMainActivity
import com.eazyrento.customer.utils.Common
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
import kotlinx.android.synthetic.main.activity_nearby_drivers_maps.*
import kotlinx.android.synthetic.main.dialog_assign_user_marker.*
import kotlinx.android.synthetic.main.map_marker_custom_view.view.*
import kotlinx.android.synthetic.main.phone_view.*
import kotlinx.android.synthetic.main.view_map_top_location_card.*
import kotlin.math.roundToInt


class NearByDriversMapActivity : BaseActivity(), OnMapReadyCallback {

    private var appBizLocationProvider: AppBizLocationProvider? = null
    private var  bookingITem: BookingListItem?=null

    private var mMap: GoogleMap? = null
    private var mHashMapDriversLocation: HashMap<String, LatLong> = hashMapOf(
        "d1" to LatLong(22.1, 77.4),
        "d2" to LatLong(23.1, 78.4), "d3" to LatLong(24.1, 79.4), "d4" to LatLong(25.1, 86.4)
    )
    private val mHasMapMarker = HashMap<String, Marker>()

    private var mCurrentLatLng: LatLong? = null

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

        setContentView(R.layout.activity_nearby_drivers_maps)

        topBarWithBackIconAndTitle(resources.getString(R.string.near_by_driver))

        mapInit()

        bookingITem = intent.getParcelableExtra<BookingListItem>(Constant.BOOKING_SUMMERY_KEY)

    }

    /* init map*/
    private fun mapInit() {
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
    private fun requestForLocation() {
        //location

        appBizLocationProvider = AppBizLocationProvider(this)

        appBizLocationProvider?.setLocationCallback(object : AppBizLocationCallback {
            override fun canRequestLocation(canRequest: Boolean) {
                if (canRequest)
                    appBizLocationProvider?.requestLocationUpdate(this@NearByDriversMapActivity)
                else
                    Toast.makeText(
                        this@NearByDriversMapActivity,
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

                    tv_address_line_map.setCurrentAddressOnTopInMap(
                        this@NearByDriversMapActivity,
                        latitude,
                        longitude
                    )

                    mCurrentLatLng = LatLong(latitude, longitude)

                    setBitMapOnMarker()

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

        setMarkerClickLisetener()

        requestForLocation()

        // mMap?.setMaxZoomPreference(ZOOM_PREFERENCE)

        for (key in mHashMapDriversLocation.keys) {
            val marker = setMarker(mHashMapDriversLocation[key]!!)!!
            marker.tag = key
            mHasMapMarker[key] = marker
        }

        val builder = LatLngBounds.builder()
        for (value in mHasMapMarker.values) {
            builder.include(value.position)
        }

        val cu = CameraUpdateFactory.newLatLngBounds(builder.build(), 0)
        mMap?.moveCamera(cu)

    }

    private fun setMarkerClickLisetener() {

        val userRole = Session.getInstance(this)?.getUserRole()

        if (userRole.equals(UserInfoAPP.CUSTOMER)){
            return
        }

        mMap?.setOnMarkerClickListener {

            val driver = mHashMapDriversLocation[it.tag]

            driver?.run {

                assign_diver_from_map.visibility = View.VISIBLE

                phone_view.setOnClickListener{
                    Common.phoneCallWithNumber("",this@NearByDriversMapActivity)
                }
                img_close_on_map.setOnClickListener{
                    assign_diver_from_map.visibility = View.GONE
                }

                btn_assign_driver.setOnClickListener {
                    assignTheDriver(driver)
                }
                true
            }
            false
        }
    }

    private fun createNewMarker(title: String, location: LatLng): MarkerOptions {

        return MarkerOptions().apply {
            position(location)

        }
    }

    fun getBitmapFromDrawable(icon: Int): BitmapDescriptor? {

        return BitmapDescriptorFactory.fromResource(icon)
    }

    fun getBitmapDescriptorFactoryFromBitmap(bitmap: Bitmap): BitmapDescriptor? {
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    private fun setBitMapOnMarker() {

        for (marker in mHasMapMarker.values) {
            val view = layoutInflater.inflate(R.layout.map_marker_custom_view, null)

            // marker update title
            val disInDouble = updateDistanceOnMarker(marker)

            view.km_map.text = " ".plus((disInDouble * 10.0).roundToInt() / 10.0).plus("km")

            val w = resources.getDimension(R.dimen._60sdp).toInt()
            val h = resources.getDimension(R.dimen._60sdp).toInt()

            val bitmap = AppBizCustomBitmapDes.loadBitmapFromView(view, w, h)

            Handler().postDelayed({

                marker.setIcon(getBitmapDescriptorFactoryFromBitmap(bitmap!!))

            }, 500)
        }

    }

    private fun setMarker(location: LatLong): Marker? {

        return mMap?.run {

            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, "$mTAG setMarker-$location")

            val markerLocationUpdate = this.addMarker(
                createNewMarker(
                    resources.getString(R.string.select_location),
                    LatLng(location.latitude, location.longitude)
                )
            )

            markerLocationUpdate
        }
    }

    fun bookingButtonClick(view: View) {
        MoveToAnotherComponent.moveToActivityWithIntentValue<CustomerMainActivity>(
            this,
            Constant.KEY_INTENT_NEAR_BY_DRIVER, Constant.VALUE_INTENT_NEAR_BY_DRIVER
        )
    }

    private fun assignTheDriver(driver: LatLong) {

       val  assignMerchantsReqModel = AssignMerchantsReqModel()

        assignMerchantsReqModel.booking_id = 1
        assignMerchantsReqModel.order_id  = ""

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<AgentAssignMerchantViewModel>(this)
                    .assignMerchants(assignMerchantsReqModel)
                , this, this
            )
        }
    }

}