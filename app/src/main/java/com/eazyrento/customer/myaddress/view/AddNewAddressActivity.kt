package com.eazyrento.customer.myaddress.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.myaddress.viewmodel.AddressCreateViewModel
import com.eazyrento.customer.myaddress.viewmodel.AddressDeleteViewModel
import com.eazyrento.customer.myaddress.viewmodel.UpdateAddressViewModel
import com.eazyrento.customer.utils.Common
import com.eazyrento.login.model.modelclass.AddressInfo
import com.eazyrento.supporting.LocationPermissionUser
import com.eazyrento.supporting.VerifyUserPermission
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
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
import java.lang.Exception
import java.util.*


class AddNewAddressActivity : BaseActivity(), OnMapReadyCallback {

    private val locationPermissionUser = LocationPermissionUser(this)

    private var addressInfo :AddressInfo?=null
    private var isFlagAddressUpdateFromProfile:Int =0
    private var isDeletingAddress:Boolean =false

    private var mLocationPermissionGranted: Boolean = false
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    private val verifying = object : VerifyUserPermission {
        override fun onSuccess() {
            mLocationPermissionGranted = true
            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"Permistion already")

            // getLocation()

        }

        override fun onFailler() {
            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"Permistion not  granted requesting.........")
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

        addressInfo = intent.getParcelableExtra(Constant.INTENT_ADDRESS_EDIT)
        isFlagAddressUpdateFromProfile = intent.getIntExtra(Constant.KEY_FROM_PROFILE,0)

        if (addressInfo != null) {
            //enanled edit and delete functionlaty....
            enableUpdateDeleteFunctionalty()
        }else{
            //For adding new functionlaty......
            createNewAddressFunctionalty()
        }

        googleMapInitilizing()
        googlePlaceAPIinitlizing()


    }

    private fun createNewAddressFunctionalty() {
        // new address is initititing........
        addressInfo = AddressInfo("","","","","",null,false,0.0,0.0,"")

        //fatching location....
        fatchingLocation()
    }
    /*
    * fatching last location ........
    * */
    private fun fatchingLocation(){
        when(GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)){
            ConnectionResult.SUCCESS-> {
                fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
                // current locatioon varifying....
                locationPermissionUser.verifingPermission(verifying)
            }
            else -> showToast(ValidationMessage.PLAY_SERVICE_APK_NOT_FOUND)
        }
    }

    fun setAddressOnUI(){
//        tv_current_address.text = addressModelItem.appartment.plus(addressModelItem.city).plus(addressModelItem.country).plus(addressModelItem.state)
        addressInfo?.let {
        tv_current_address.text = it.address_line
        ed_address.setText(it.address_line)
        }
    }
    fun enableUpdateDeleteFunctionalty(){

        btn_save.visibility = View.GONE
        Common.showGroupViews(btn_delete, btn_update)
//        Common.showGroupViews(btn_update)
        setUserAddressTypeEditMode()
        setAddressOnUI()

//        moveCamraAtLocation(addressInfo.latitude,addressInfo.longitude)
    }
    fun setUserAddressTypeEditMode(){
        when(addressInfo?.address_type){
            Constant.Address_Work -> onWorkTypeClick(null)
            Constant.Address_Home ->onHomeTypeClick(null)
            Constant.Address_Other ->onOtherTypeClick(null)
        }
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

                    AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"Permistion granted........")

                   // getLocation()

                }
            }
        }
    }
    private fun googlePlaceAPIinitlizing(){
// Initialize the SDK
        // Initialize the SDK
        Places.initialize(applicationContext, "AIzaSyAHaI3BcisJ6vIsiOBaQ41SgG1Rp1yaD0I")

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
        autocompleteFragment?.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.ADDRESS,Place.Field.LAT_LNG))

// Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment?.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, place.toString())
                //place.address

                place.latLng?.let {
                    setData(it.latitude, it.longitude)
                        return
                }
                addressInfo?.address_line = place.address as String

            }

            override fun onError(staus: Status) {
                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, staus.toString())
            }
        })

    }

    private fun googleMapInitilizing() {

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

        getLocation()

    }

    private fun moveCamraAtLocation(lat: Double,lng: Double){
        val cameraPosition = CameraPosition.Builder()
            .target(
                LatLng(
                    lat,
                    lng
                )
            ) // Sets the center of the it to location user
            .zoom(17f) // Sets the zoom
            .bearing(90f) // Sets the orientation of the camera to east
            .tilt(40f) // Sets the tilt of the camera to 30 degrees
            .build() // Creates a CameraPosition from the builder

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

        placeMarkerOnMap(LatLng(lat, lng))
    }

    private fun enbledLocationOnMap(){
        mMap.isMyLocationEnabled = true
        mMap.uiSettings?.isMyLocationButtonEnabled = true
    }

    fun setData(lat: Double,lng: Double){

        moveCamraAtLocation(lat,lng)
        getAddressByLocation(lat,lng)
        setAddressOnUI()
    }
    private fun getLocation() {

        if (mLocationPermissionGranted) {
            fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->

                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, "" + location)

                location?.let {
                    enbledLocationOnMap()
                    setData(location.latitude, location.longitude)
                    return@addOnSuccessListener
                }
                showToast(ValidationMessage.CURRENT_LOCATION_NOT_GETTING)
            }
        }
    }



    private fun checkValidation(): Boolean {
        if (ed_address.text.toString().isEmpty() ){
            showToast(ValidationMessage.VALID_ADDRESS)
            return true
        }
        if (btn_home_active.visibility != View.VISIBLE && btn_other_active.visibility != View.VISIBLE && btn_work_active.visibility != View.VISIBLE){
            showToast(ValidationMessage.VALID_ADDRESS_TYPE)
            return true
        }
        return false

    }


    fun onSaveAddressClick(view: View) {

        if (checkValidation())
            return
        if (isFlagAddressUpdateFromProfile==1){
            sendIntentToCallerAct(Constant.KEY_FROM_PROFILE,Constant.REQUEST_CODE_PROFILE_UPDATE)
            return
        }

        addressInfo?.address_line = ed_address.text.toString()
        callSaveAddressAPI()
    }

    fun onDeleteClick(view: View){

        isDeletingAddress = true

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<AddressDeleteViewModel>(this)
                    .deleteAddress(addressInfo?.id!!)
                , this, this
            )
        }
    }
    fun onUpdateClick(view: View)
    {
        if (checkValidation())
            return

        addressInfo?.address_line = ed_address.text.toString()

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<UpdateAddressViewModel>(this)
                    .updateAddress(addressInfo!!,addressInfo!!.id!!)
                , this, this
            )
        }

    }


    /*private fun addressObjectBuilder() {

        if (btn_home_active.visibility == View.VISIBLE) {
            addressModelItem.address_type = Constant.Address_Home
        } else if (btn_work_active.visibility == View.VISIBLE) {
            addressModelItem.address_type = Constant.Address_Work
        } else if (btn_other_active.visibility == View.VISIBLE) {
            addressModelItem.address_type = Constant.Address_Other
        }



    }*/

    fun onHomeTypeClick(view: View?){
            addressInfo?.address_type = Constant.Address_Home
            Common.hideGroupViews(btn_work_active, btn_other_active, btn_home_inactive)
            Common.showGroupViews(btn_home_active, btn_other_inactive, btn_work_inactive)
    }

    fun onWorkTypeClick(view: View?){
            addressInfo?.address_type = Constant.Address_Work
            Common.hideGroupViews(btn_home_active, btn_other_active, btn_work_inactive)
            Common.showGroupViews(btn_work_active, btn_other_inactive, btn_home_inactive)

    }

    fun onOtherTypeClick(view: View?){
            addressInfo?.address_type = Constant.Address_Other
            Common.hideGroupViews(btn_home_active, btn_work_active, btn_other_inactive)
            Common.showGroupViews(btn_other_active, btn_work_inactive, btn_home_inactive)
    }

    fun callSaveAddressAPI(){
        //setAddressItem()
        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<AddressCreateViewModel>(this)
                    .createAddress(addressInfo!!)
                , this, this
            )
        }
    }

    override fun <T> onSuccessApiResult(data: T) {


        data?.let {

            showToast(ValidationMessage.ADDRESS_ADDED)

           /* val intent= Intent()
            intent.putExtra(Constant.KEY_UPDATE_DELETE_CREATE_REQUEST,addressInfo)
            finishCurrentActivityWithResult(Constant.INTENT_UPDATE_DELETE_CREATE_REQUEST,intent)*/
            if (isDeletingAddress){
                addressInfo = null
                isDeletingAddress =false
            }

            sendIntentToCallerAct(Constant.KEY_UPDATE_DELETE_CREATE_REQUEST,Constant.INTENT_UPDATE_DELETE_CREATE_REQUEST)
        }
    }

    private fun sendIntentToCallerAct(key:String,requesCode:Int){
        val intent= Intent()
        intent.putExtra(key,addressInfo)
        finishCurrentActivityWithResult(requesCode,intent)
    }

    private fun placeMarkerOnMap(location: LatLng) {
        val markerOptions = MarkerOptions().position(location)
        mMap.addMarker(markerOptions)
    }

    private fun getAddressByLocation(lat:Double,lng:Double) {

            val geocoder = Geocoder(this)

            val listOfAdress = geocoder.getFromLocation(lat, lng, 1)
        addressInfo?.let {
            try {

            it.latitude = lat
            it.longitude = lng


            val address = listOfAdress[0].getAddressLine(0)

                 it.address_line = address

                splitAddress(address)

            }catch (ex:Exception){
                ex.printStackTrace()
            }

        }

        }

   private fun splitAddress(address: String) {
       try {

       val listOfAdress = address.split(",")

       addressInfo?.let {
       it.appartment = listOfAdress.component1()
       it.city = listOfAdress.component2()
       it.country = listOfAdress.component3()
       it.state = listOfAdress.component4()
       }
       }catch (ex:Exception){
           ex.printStackTrace()
       }
    }
}
