package com.eazyrento.customer.myaddress.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.eazyrento.Constant
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.eazyrento.Constant.Companion.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.dashboard.model.modelclass.CustomerCreateBookingReqModel
import com.eazyrento.customer.dashboard.model.modelclass.CustomerCreateBookingReqModelItem
import com.eazyrento.customer.dashboard.view.activity.CustomerBookingSubmitReviewActivity
import com.eazyrento.customer.dashboard.view.activity.CustomerMainActivity
import com.eazyrento.customer.myaddress.model.modelclass.AddressCreateReqModel
import com.eazyrento.customer.myaddress.model.modelclass.AddressCreateReqModelItem
import com.eazyrento.customer.myaddress.viewmodel.AddressCreateViewModel
import com.eazyrento.customer.myaddress.viewmodel.AddressDetailsViewModel
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import com.eazyrento.merchant.model.modelclass.MerchantAddProductReqModel
import com.eazyrento.merchant.viewModel.MerchantProductDetailViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_agent_write_note.*
import kotlinx.android.synthetic.main.activity_booking_details.*
import kotlinx.android.synthetic.main.add_new_address_activity.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class AddNewAddressActivity : BaseActivity(), OnMapReadyCallback {

    val listAddressModelItem = AddressCreateReqModelItem()

    companion object AddressList {
        val objListAddressModel = AddressCreateReqModel()

        fun setAddressItem( obj: AddressCreateReqModelItem){
            objListAddressModel.add(obj)
        }
    }
    private var defaultID:Int =-1
    private  val DEFUALT_VALUE = -1
    var edit_address_ID:Int =-1
    private var mLocationPermissionGranted: Boolean = false
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.add_new_address_activity)

        //edit_address_ID = intent.getIntExtra(Constant.INTENT_ADDRESS_EDIT,DEFUALT_VALUE)

        //
       // listAddressModelItem.id =
           // if (edit_address_ID!=DEFUALT_VALUE)
            //editing functioalty
                //editAddressFuntionalty(edit_address_ID)

            //else
            //Adding funtionalty
             //   intent.getIntExtra(Constant.INTENT_NEW_ADDRESS_ADD,DEFUALT_VALUE)

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
        btn_save.setOnClickListener { moveTOSave() }
    }
    private fun checkValidation():Boolean {
        when {
            ed_address.text.toString().isEmpty()->showToast(ValidationMessage.VALID_ADDRESS)
            btn_home_active.visibility != View.VISIBLE && btn_other_active.visibility != View.VISIBLE && btn_work_active.visibility != View.VISIBLE ->showToast(ValidationMessage.VALID_ADDRESS_TYPE)
            else-> {
                    return false
                }
        }
        return true
    }
    fun moveTOSave(){
        if (checkValidation())
            return
        setAddressListItem()
    }

    private fun setAddressListItem(){
            if(btn_home_active.visibility==View.VISIBLE){
                listAddressModelItem.address_type=Constant.Address_Home
            }
            else if(btn_work_active.visibility==View.VISIBLE){
                listAddressModelItem.address_type=Constant.Address_Work
            }
            else if(btn_other_active.visibility==View.VISIBLE){
                listAddressModelItem.address_type=Constant.Address_Other
            }
        listAddressModelItem.longitude=lastLocation.longitude
        listAddressModelItem.latitude=lastLocation.latitude
        listAddressModelItem.address_line=ed_address.text.toString()
        setAddressItem(listAddressModelItem)
              //setAddressItem()
            callAPI()?.let {
                it.observeApiResult(
                    it.callAPIActivity<AddressCreateViewModel>(this)
                        .createAddress(objListAddressModel)
                    , this, this
                )
        }

    }
    override fun <T> onSuccessApiResult(data: T) {
        data?.let {
            showToast(ValidationMessage.ADDRESS_ADDED)

            MoveToAnotherComponent.moveToListAddressActivity(this)
        }
    }

    private fun editAddressFuntionalty(editAddressID:Int): Int {

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<AddressDetailsViewModel>(this)
                    .addressdetailsAPI(editAddressID)
                , this, this
            )

        }

        return editAddressID
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