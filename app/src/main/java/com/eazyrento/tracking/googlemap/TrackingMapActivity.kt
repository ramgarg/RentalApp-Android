package com.eazyrento.tracking.googlemap


import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.observe
import com.eazyrento.Constant
import com.eazyrento.EazyRantoApplication
import com.eazyrento.R
import com.eazyrento.Session
import com.eazyrento.appbiz.AppBizCustomBitmapDes
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.common.view.UserInfoAPP
import com.eazyrento.common.viewmodel.OrderTrackingViewModel
import com.eazyrento.customer.dashboard.model.modelclass.OrderDetailsResModel
import com.eazyrento.customer.dashboard.model.modelclass.OrderTrackingList
import com.eazyrento.customer.dashboard.model.modelclass.OrderTrackingListItem
import com.eazyrento.customer.dashboard.viewmodel.CustomerOrderTrackingViewModel
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.Common.Companion.setCurrentAddressOnTopInMap
import com.eazyrento.tracking.data.model.Route
import com.eazyrento.tracking.googlemap.viewmodel.TrackingMapViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_agent_add_note.*
import kotlinx.android.synthetic.main.map_marker_custom_view.view.*
import kotlinx.android.synthetic.main.template_cancle_call_map.*
import kotlinx.android.synthetic.main.view_map_top_location_card.*


class TrackingMapActivity : BaseActivity(), OnMapReadyCallback {

    private val mHasMapMarker = HashMap<Int, Marker>()

    private lateinit var mMapSetup: MapSetup

    // private  lateinit var orderID:String

    private  var mMap: GoogleMap?=null

    private lateinit var trackingMapViewModel: TrackingMapViewModel

    private  var mCustomerOrderTrackingList:OrderTrackingList? = null

    private  var mOrderTrackingListItem:OrderTrackingListItem? = null



    private val mTAG = "TrackingMapActivity:-"

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    companion object {
        const val MODE = "driving"
        const val ROUTING_PREFERENCE = "less_driving"
        val ORIGIN = "Dolariya"
        val DESTINATION = "Seoni Malwa"

    }

    /* on create */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_tracking_maps)

        trackingMapViewModel = TrackingMapViewModel()

        topBarWithBackIconAndTitle(resources.getString(R.string.tracking))

        mapInit()

     //   val orderID = intent.getStringExtra(Constant.KEY_ORDER_ORDER_ID)!!

       val orderResponse  =  intent.getParcelableExtra<OrderDetailsResModel>(Constant.DRIVER_ASSIGN_ORDER_KEY)

        callOrderTrackingApi(orderResponse)

    }

    /* init map*/
    private fun mapInit() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        setMarkerClickLisetener()

        mMapSetup = MapSetup(this, mMap!!)

        mMapSetup.mapSetting()

        // draw polyline and car animation
        //drawPolyline()
    }

    // draw polyline and car animation
    private fun drawPolyline(){
        val liveData = trackingMapViewModel.getDirectionApiResponse(
            MODE,
            ROUTING_PREFERENCE,
            ORIGIN,
            DESTINATION,
            resources.getString(R.string.google_api_key)
        )

        liveData.observe(this) {

            val routeList: List<Route> = it.routes

            for (route in routeList) {
                val polyLine: String = route.overviewPolyline.points
                val polyLineLatLongList = mMapSetup.decodePoly(polyLine)
                mMapSetup.drawPolyLineAndAnimateCar(polyLineLatLongList)
            }

        }
    }

    private fun callOrderTrackingApi(orderResponse: OrderDetailsResModel?) {
        if (Session.getInstance(EazyRantoApplication.context)?.getUserRole().equals(UserInfoAPP.CUSTOMER)){
        orderResponse?.run {
        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<CustomerOrderTrackingViewModel>(this@TrackingMapActivity)
                    .getCustomerOrderTrackingRepo(order_id,id), this@TrackingMapActivity, this@TrackingMapActivity
            )
        }
        }
    }else{
            orderResponse?.run {
                callAPI()?.let {
                    it.observeApiResult(
                        it.callAPIActivity<OrderTrackingViewModel>(this@TrackingMapActivity)
                            .getOrderTrackingRepo(order_id,id), this@TrackingMapActivity, this@TrackingMapActivity
                    )
                }
            }
        }
    }

    override fun <T> onSuccessApiResult(data: T) {
        super.onSuccessApiResult(data)
        if (data is OrderTrackingList) {
            mCustomerOrderTrackingList = data

            setDriverContactData(data[0])
            setBitMapOnMarker(data)

        }else if (data is OrderTrackingListItem){
            mOrderTrackingListItem = data

            setDriverContactData(data)
            setSingleBitMapMarker(data)
        }
    }

    override fun <T> statusCodeOfApi(data: T) {
        super.statusCodeOfApi(data)

        val data  = data as DataWrapper<T>

        // not found
        if (data.statusCode ==404 ) {
            showToast(R.string.NO_DATA_FOUND)
            finish()
        }

    }

    private fun setMarkerClickLisetener() {


        mMap?.setOnMarkerClickListener {

//            val driver = mHasMapMarker[it.tag]

            mCustomerOrderTrackingList?.run {

                val index = it.tag as Int
                val driver = mCustomerOrderTrackingList!![index]
                setDriverContactData(driver)

                //assign_diver_from_map.visibility = View.VISIBLE
                //driverPopUp(driver)

                true
            }
            false
        }
    }


    private fun setDriverContactData(customerOrderTrackingListItem: OrderTrackingListItem) {
        customerOrderTrackingListItem.driverInfo.run {

            tv_name_map.text = fullName
            tv_user_type.text = resources.getString(R.string.driver)

            cancle_trip_map.visibility = View.GONE

            tv_address_line_map.setCurrentAddressOnTopInMap(this@TrackingMapActivity, latitude, longitude)

            btn_call_map.setOnClickListener {
                Common.phoneCallWithNumber(mobileNumber, this@TrackingMapActivity)

            }
            cancle_trip_map.setOnClickListener {
                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, mTAG.plus("cancle button clicked"))
            }
        }
    }

    private fun setSingleBitMapMarker(data: OrderTrackingListItem) {
        data.run {

            val driver = driverInfo
            val marker = setMarker(driver.latitude,driver.longitude)!!

            mHasMapMarker[id] = marker
            customMarkerDrivers(marker)

        val builder = LatLngBounds.builder()
        for (value in mHasMapMarker.values) {
            builder.include(value.position)
        }


        val cu = CameraUpdateFactory.newLatLngBounds(builder.build(), 0)

        mMap?.moveCamera(cu)
    }
    }
    // custom driver markers

    private fun setBitMapOnMarker(orderTrakingList: OrderTrackingList) {


            var index = 0
            while( index < orderTrakingList.size) {
                val driver = orderTrakingList[index].driverInfo
                val marker = setMarker(driver.latitude,driver.longitude)!!
                marker.tag = index
                mHasMapMarker[orderTrakingList[index].id] = marker
                customMarkerDrivers(marker)
                index++
            }

            val builder = LatLngBounds.builder()
            for (value in mHasMapMarker.values) {
                builder.include(value.position)
            }


            val cu = CameraUpdateFactory.newLatLngBounds(builder.build(), 0)

            mMap?.moveCamera(cu)

    }

    private fun setMarker(lati:Double , longi:Double): Marker? {

        return mMap?.run {

            val markerLocationUpdate = this.addMarker(
                createNewMarker(
                    resources.getString(R.string.select_location),
                    LatLng(lati, longi)
                )
            )

            markerLocationUpdate
        }
    }

    private fun createNewMarker(title: String, location: LatLng): MarkerOptions {

        return MarkerOptions().apply {
            position(location)

        }
    }

    private fun customMarkerDrivers(marker: Marker,distance:Double=0.0){
        val view = layoutInflater.inflate(R.layout.map_marker_custom_view, null)

        // marker update title
        //val disInDouble = updateDistanceOnMarker(marker)

        view.km_map.visibility = View.GONE

       // view.km_map.text = " ".plus((distance * 10.0).roundToInt() / 10.0).plus("km")

        val w = resources.getDimension(R.dimen._60sdp).toInt()
        val h = resources.getDimension(R.dimen._60sdp).toInt()

        val bitmap = AppBizCustomBitmapDes.loadBitmapFromView(view, w, h)

        Handler().postDelayed({

            marker.setIcon(getBitmapDescriptorFactoryFromBitmap(bitmap!!))

        }, 500)
    }

    fun getBitmapDescriptorFactoryFromBitmap(bitmap: Bitmap): BitmapDescriptor? {
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

}
