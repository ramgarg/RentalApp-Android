package com.eazyrento.customer.myaddress.view

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.myaddress.model.modelclass.AddressCreateReqModelItem
import com.eazyrento.customer.myaddress.viewmodel.AddressCreateViewModel
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.supporting.LocationGetter
import com.eazyrento.supporting.LocationPermissionUser
import com.eazyrento.supporting.VerifyUserPermission
import com.google.android.gms.common.api.Status
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
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import kotlinx.android.synthetic.main.add_new_address_activity.*
import java.util.*


class AddNewAddressActivity : BaseActivity(), OnMapReadyCallback {

    val locationPermissionUser = LocationPermissionUser(this)

    val addressModelItem = AddressCreateReqModelItem()

    private val DEFUALT_VALUE = -1
    var edit_address_ID: Int = -1

        private var mLocationPermissionGranted: Boolean = false
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
//    private  var mLocationOnMap: Location?=null


    private var cityName: String = ""
    private var stateName: String = ""
    private var countryName: String = ""

    private val verifying = object : VerifyUserPermission {
        override fun onSuccess() {
            mLocationPermissionGranted = true

             getLocation()

        }

        override fun onFailler() {
            ActivityCompat.requestPermissions(
                this@AddNewAddressActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                Constant.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            )
        }

    }

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.add_new_address_activity)

        topBarWithBackIconAndTitle(getString(R.string.add_new_address))

        edit_address_ID = intent.getIntExtra(Constant.INTENT_ADDRESS_EDIT, DEFUALT_VALUE)

        if (edit_address_ID != DEFUALT_VALUE) {
            //enanled edit and delete functionlaty
            enableUpdateDeleteFunctionalty()
        }
        /* else {
            btn_save.visibility = View.VISIBLE
            Common.hideGroupViews(btn_delete, btn_update)
        }
*/
        mapInit()
        //fatching location....
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        //update UI

        UIDataAsUserAddressType()

        // initlization google map


        // current locatioon varifying....
        locationPermissionUser.verifingPermission(verifying)


        // initilize places
        initPlaceAPI()

    }
    fun enableUpdateDeleteFunctionalty(){

        btn_save.visibility = View.GONE
        Common.showGroupViews(btn_delete, btn_update)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        locationPermissionUser.onRequestPermissionsResult(requestCode, permissions, grantResults)

        mLocationPermissionGranted = false
        when (requestCode) {
            Constant.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    mLocationPermissionGranted = true

                    getLocation()

                }
            }
        }
    }
    private fun initPlaceAPI(){
// Initialize the SDK
        // Initialize the SDK
        Places.initialize(applicationContext, "AIzaSyBpHL3PdTYMgPqXQzzc54IW3cd-SCMpNzg")

// Create a new Places client instance

// Create a new Places client instance
        val placesClient: PlacesClient = Places.createClient(this)

        // Initialize the AutocompleteSupportFragment.
        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment?

// Specify the types of place data to return.
        /*
        * Place{address=null, addressComponents=null, attributions=[], id=null
        * , latLng=null, name=null, openingHours=null, phoneNumber=null, photoMetadatas=null,
        * plusCode=null, priceLevel=null, rating=null, types=null, userRatingsTotal=null,
        * utcOffsetMinutes=null, viewport=null, websiteUri=null}

        * */
        autocompleteFragment?.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.ADDRESS,
            Place.Field.ADDRESS_COMPONENTS,Place.Field.LAT_LNG))

// Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment?.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, place.toString())
            }

            override fun onError(staus: Status) {
                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, staus.toString())
            }
        })

    }

    private fun mapInit() {

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        if (map != null) {
            this.mMap = map
        }
        val mapStyleOptions = MapStyleOptions.loadRawResourceStyle(this, R.raw.style)
        this.mMap.setMapStyle(mapStyleOptions)

    }

    private fun moveCamraAtLocation(location: Location){
        val cameraPosition = CameraPosition.Builder()
            .target(
                LatLng(
                    location.latitude,
                    location.longitude
                )
            ) // Sets the center of the it to location user
            .zoom(17f) // Sets the zoom
            .bearing(90f) // Sets the orientation of the camera to east
            .tilt(40f) // Sets the tilt of the camera to 30 degrees
            .build() // Creates a CameraPosition from the builder

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

        placeMarkerOnMap(LatLng(location.latitude, location.longitude))
    }

    private fun enbledLocationOnMap(){
        mMap.isMyLocationEnabled = true
        mMap.uiSettings?.isMyLocationButtonEnabled = true
    }

    fun setData(location: Location){
        moveCamraAtLocation(location)
        enbledLocationOnMap()
        getAddress(location)
    }
    private fun getLocation() {

        fusedLocationClient.lastLocation.addOnSuccessListener(this ){ location ->


            location?.let {
                setData(location)
                return@addOnSuccessListener
            }
            locationPermissionUser.locationCreater(fusedLocationClient,object:LocationGetter{
                override fun onLocation(location: Location) {
                   setData(location)

                }

            })

        }
    }



    private fun checkValidation(): Boolean {
        when {
            ed_address.text.toString().isEmpty() -> showToast(ValidationMessage.VALID_ADDRESS)
            btn_home_active.visibility != View.VISIBLE && btn_other_active.visibility != View.VISIBLE && btn_work_active.visibility != View.VISIBLE -> showToast(
                ValidationMessage.VALID_ADDRESS_TYPE
            )
            else -> {
                return false
            }
        }
        return true
    }


    fun onSaveAddressClick(view: View) {
        if (checkValidation())
            return
        addressObjectBuilder()
        callSaveAddressAPI()
    }

    private fun addressObjectBuilder() {

        if (btn_home_active.visibility == View.VISIBLE) {
            addressModelItem.address_type = Constant.Address_Home
        } else if (btn_work_active.visibility == View.VISIBLE) {
            addressModelItem.address_type = Constant.Address_Work
        } else if (btn_other_active.visibility == View.VISIBLE) {
            addressModelItem.address_type = Constant.Address_Other
        }

        addressModelItem.address_line = ed_address.text.toString()
        addressModelItem.appartment = ""

    }

    fun callSaveAddressAPI(){
        //setAddressItem()
        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<AddressCreateViewModel>(this)
                    .createAddress(addressModelItem)
                , this, this
            )
        }
    }

    override fun <T> onSuccessApiResult(data: T) {
        data?.let {
            showToast(ValidationMessage.ADDRESS_ADDED)

            MoveToAnotherComponent.moveToActivityNormal<MyAddressListActivity>(this)
        }
    }

    private fun UIDataAsUserAddressType() {
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

    private fun getAddress(location: Location) {

            val geocoder = Geocoder(this)

            val list = geocoder.getFromLocation(location.latitude, location.longitude, 1)

            addressModelItem.longitude = location.longitude
            addressModelItem.latitude = location.latitude

            val address = list[0].getAddressLine(0)
            addressModelItem.city = list.get(0).subLocality
            addressModelItem.country = list.get(0).countryName
            addressModelItem.state = list.get(0).locality

        }
}
