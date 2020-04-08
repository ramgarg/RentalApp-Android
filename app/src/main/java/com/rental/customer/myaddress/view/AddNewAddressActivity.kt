package com.rental.customer.myaddress.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.rental.Constant.Companion.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
import com.rental.R
import com.rental.common.view.BaseActivity
import com.rental.customer.utils.Common
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.add_new_address_activity.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class AddNewAddressActivity : BaseActivity(), OnMapReadyCallback {

    private var mLocationPermissionGranted: Boolean = false
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.add_new_address_activity)

        initView()

        getLocationPermission();

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun messageReceive(customEvent: String?) {
        if (customEvent.equals("AddNew")) {
            toolbar_title.text = "Add New Address"
            btn_save.visibility = View.VISIBLE
            Common.hideGroupViews(btn_delete, btn_update)
        } else {
            toolbar_title.text = "MyAddress"
            btn_save.visibility = View.GONE
            Common.showGroupViews(btn_delete, btn_update)
        }

    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onPause() {
        super.onPause()
        EventBus.getDefault().unregister(this)
    }


    override fun onMapReady(map: GoogleMap?) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        if (map != null) {
            mMap = map
        }
        val mapStyleOptions = MapStyleOptions.loadRawResourceStyle(this, R.raw.style)
        mMap.setMapStyle(mapStyleOptions)

        updateLocationUI()

        moveCameraToCurrentLocation(mMap)

        saveAddressAs()
    }

    @SuppressLint("MissingPermission")
    private fun moveCameraToCurrentLocation(map: GoogleMap?) {
        map?.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            // Got last known location. In some rare situations this can be null.
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)

                val cameraPosition = CameraPosition.Builder()
                .target(
                    LatLng(
                        location.latitude,
                        location.longitude
                    )
                ) // Sets the center of the map to location user
                .zoom(17f) // Sets the zoom
                .bearing(90f) // Sets the orientation of the camera to east
                .tilt(40f) // Sets the tilt of the camera to 30 degrees
                .build() // Creates a CameraPosition from the builder
            map?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
                placeMarkerOnMap(currentLatLng)
            }
        }
    }

    private fun mapInit() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    private fun initView() {
        ViewVisibility.isVisibleOrNot(
            this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.add_new_address)
        )
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    private fun getLocationPermission() { /*
     * Request location permission, so that we can get the location of the
     * device. The result of the permission request is handled by a callback,
     * onRequestPermissionsResult.
     */
        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            mLocationPermissionGranted = true
            mapInit()
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        mLocationPermissionGranted = false
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    mLocationPermissionGranted = true

                }
            }
        }
    }

    private fun updateLocationUI() {
        if (mMap == null) {
            return
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.isMyLocationEnabled = true
                mMap.uiSettings.isMyLocationButtonEnabled = true
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message)
        }
    }

    private fun saveAddressAs() {
        btn_home_inactive.setOnClickListener {
            Common.hideGroupViews(btn_work_active, btn_other_active, btn_home_inactive)
            Common.showGroupViews(btn_home_active, btn_other_inactive, btn_work_inactive)

        }

        btn_work_inactive.setOnClickListener {
            Common.hideGroupViews(btn_home_active, btn_other_active, btn_work_inactive)
            Common.showGroupViews(btn_work_active, btn_other_inactive, btn_home_inactive)

        }

        btn_other_inactive.setOnClickListener {
            Common.hideGroupViews(btn_home_active, btn_work_active, btn_other_inactive)
            Common.showGroupViews(btn_other_active, btn_work_inactive, btn_home_inactive)
        }

    }
    private fun placeMarkerOnMap(location: LatLng) {
        val markerOptions = MarkerOptions().position(location)
        mMap.addMarker(markerOptions)
    }

}