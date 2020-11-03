package com.eazyrento.customer.myaddress.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import com.eazyrento.AddressFilter
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.appbiz.retrofitapi.DataWrapper
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
import com.google.android.gms.maps.model.*
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

    private var mIsMapCameraMoveStated:Boolean = true

    private var addressInfo :AddressInfo?=null
//    private var isFlagAddressUpdateFromProfile:Int =0
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

        val tempAdd:AddressInfo?  = intent.getParcelableExtra<AddressInfo>(Constant.INTENT_ADDRESS_EDIT)

//        isFlagAddressUpdateFromProfile = intent.getIntExtra(Constant.KEY_FROM_PROFILE,0)

        if (tempAdd != null) {
            addressInfo = tempAdd
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
            else -> showToast(R.string.PLAY_SERVICE_APK_NOT_FOUND)
        }
    }

    fun setAddressOnUI(){
//        tv_current_address.text = addressModelItem.appartment.plus(addressModelItem.city).plus(addressModelItem.country).plus(addressModelItem.state)
        addressInfo?.let {
        tv_current_address.text = it.address_line
        ed_address.setText(it.address_line)
        chk_box_defalut_address.isChecked =it.is_default
        }
    }
    fun enableUpdateDeleteFunctionalty(){

        btn_save.visibility = View.GONE

        btn_delete.setOnClickListener {
            onDeleteClick()
        }
        btn_update.setOnClickListener{
            onUpdateClick()
        }

        Common.showGroupViews(btn_delete, btn_update)
//        Common.showGroupViews(btn_update)
        setUserAddressTypeEditMode()
        setAddressOnUI()

//        moveCamraAtLocation(addressInfo.latitude,addressInfo.longitude)
    }
    fun setUserAddressTypeEditMode(){
        when(addressInfo?.address_type){
            resources.getString(R.string.Address_Work) -> onWorkTypeClick(null)
            resources.getString(R.string.Address_Home) ->onHomeTypeClick(null)
            resources.getString(R.string.Address_Other) ->onOtherTypeClick(null)
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
//        Places.initialize(applicationContext, "AIzaSyAHaI3BcisJ6vIsiOBaQ41SgG1Rp1yaD0I")
        Places.initialize(applicationContext, resources.getString(R.string.google_credential_key))

// Create a new Places client instance

// Create a new Places client instance
        val placesClient: PlacesClient = Places.createClient(this)

        // Initialize the AutocompleteSupportFragment.
        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment?
        autocompleteFragment?.setCountries(resources.getStringArray(R.array.place_restriction_country_code).asList())

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

                    moveCamraAtLocation(it.latitude,it.longitude)
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

        mMap.setOnCameraIdleListener {

                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,
                    "setOnCameraIdleListener value--$mIsMapCameraMoveStated"
                )

                val latLng = mMap.cameraPosition.target

                setData(latLng.latitude, latLng.longitude)
        }

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


    }

    private fun enbledLocationOnMap(){
        mMap.isMyLocationEnabled = true
        mMap.uiSettings?.isMyLocationButtonEnabled = true
    }

    fun setData(lat: Double,lng: Double){

        AddressFilter(this,addressInfo).getAddressByLocation(lat,lng,1)
        setAddressOnUI()
    }
    private fun getLocation() {

        if (mLocationPermissionGranted) {
            fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->

                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, "" + location)

                location?.let {
                    enbledLocationOnMap()
                    placeMarkerOnMap(LatLng(location.latitude,  location.longitude))
                    moveCamraAtLocation(it.latitude,it.longitude)
                    setData(location.latitude, location.longitude)
                    return@addOnSuccessListener
                }
                showToast(R.string.CURRENT_LOCATION_NOT_GETTING)
            }
        }
    }



    private fun isValidationCorrect(): Boolean {
        if (ed_address.text.toString().isEmpty() ){
            showToast(R.string.VALID_ADDRESS)
            return false
        }
        if (btn_home_active.visibility != View.VISIBLE && btn_other_active.visibility != View.VISIBLE && btn_work_active.visibility != View.VISIBLE){
            showToast(R.string.VALID_ADDRESS_TYPE)
            return false
        }
        return true

    }


    fun onSaveAddressClick(view: View) {

        if (isValidationCorrect().not() || isAddressFromViewCorrect().not())
            return

        if (intent.getIntExtra(Constant.KEY_FROM_PROFILE,0)==Constant.FIRST_TIME_USER_LOGIN){
            sendIntentToCallerAct(Constant.KEY_FROM_PROFILE,Constant.REQUEST_CODE_PROFILE_UPDATE,false)
            return
        }


        callSaveAddressAPI()
    }
    private fun isAddressFromViewCorrect():Boolean{

        addressInfo?.let {
            it.address_line = ed_address.text.toString()
            it.is_default = chk_box_defalut_address.isChecked

            // when user type munaly the address
            if (it.city.isNullOrBlank() || it.state.isNullOrBlank() || it.country.isNullOrBlank()) {
                try {
                    val list = ed_address.text.toString().split(",")
                    it.appartment = list[0]
                    it.city = list[1]
                    it.state =list[2]
                    it.country= list[3]

                }catch (e:Exception){
                    showToast(R.string.ADRESS_SEPREATED_BYCOMMA)
                    e.printStackTrace()
                    return false
                }
            }
            return true
        }
        return false

    }

    fun onDeleteClick(){

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<AddressDeleteViewModel>(this)
                    .deleteAddress(addressInfo?.id!!)
                , this, this
            )
        }
        isDeletingAddress = true
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,addressInfo.toString())
    }
    fun onUpdateClick()
    {
        if (isValidationCorrect().not() || isAddressFromViewCorrect().not())
            return

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<UpdateAddressViewModel>(this)
                    .updateAddress(addressInfo!!,addressInfo!!.id!!)
                , this, this
            )
        }

    }


    fun onHomeTypeClick(view: View?){
            addressInfo?.address_type = resources.getString(R.string.Address_Home)
            Common.hideGroupViews(btn_work_active, btn_other_active, btn_home_inactive)
            Common.showGroupViews(btn_home_active, btn_other_inactive, btn_work_inactive)
    }

    fun onWorkTypeClick(view: View?){
            addressInfo?.address_type = resources.getString(R.string.Address_Work)
            Common.hideGroupViews(btn_home_active, btn_other_active, btn_work_inactive)
            Common.showGroupViews(btn_work_active, btn_other_inactive, btn_home_inactive)

    }

    fun onOtherTypeClick(view: View?){
            addressInfo?.address_type = resources.getString(R.string.Address_Other)
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

    override fun <T> statusCodeOfApi(data: T) {

        val dataWrapper = data as DataWrapper<T>

        if(dataWrapper.statusCode ==204 && isDeletingAddress) {

            isDeletingAddress =false
            sendIntentToCallerAct(Constant.KEY_UPDATE_DELETE_CREATE_REQUEST,Constant.INTENT_UPDATE_DELETE_CREATE_REQUEST,true)
        }
    }
    override fun <T> onSuccessApiResult(data: T) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        if (isDeletingAddress) {
            return
        }
        if (data is AddressInfo){
            addressInfo = data
        }

        data?.let {

            showToast(R.string.ADDRESS_ADDED)
            sendIntentToCallerAct(Constant.KEY_UPDATE_DELETE_CREATE_REQUEST,Constant.INTENT_UPDATE_DELETE_CREATE_REQUEST,false)
        }
    }

    private fun sendIntentToCallerAct(key:String,requesCode:Int,isDelete:Boolean){
        val intent= Intent()
        intent.putExtra(key,addressInfo)
        intent.putExtra("delete",isDelete)
        finishCurrentActivityWithResult(requesCode,intent)
    }

    private fun placeMarkerOnMap(location: LatLng) {
        val markerOptions = MarkerOptions().position(location)
        mMap.addMarker(markerOptions)


    }

    /*private fun getAddressByLocation(lat:Double,lng:Double) {

            val geocoder = Geocoder(this)

            val listOfAdress = geocoder.getFromLocation(lat, lng, 1)
        addressInfo?.let {
            try {

            it.latitude = lat
            it.longitude = lng


            val address = listOfAdress[0].getAddressLine(0)

                 it.address_line = address

                splitAddress( listOfAdress[0])

            }catch (ex:Exception){
                ex.printStackTrace()
            }

        }

        }

   private fun splitAddress(address: Address) {
       try {

       addressInfo?.let {
       //it.appartment = address.subThoroughfare.plus(address.thoroughfare).plus(address.featureName)
       //it.city = address.subLocality.plus(address.locality)
      // it.state = address.subAdminArea.plus(address.adminArea)

           it.city = address.locality
           it.state = address.adminArea
           it.country = address.countryName
           it.appartment = address.featureName


       }
       }catch (ex:Exception){
           ex.printStackTrace()
       }
    }*/
}
