package com.eazyrento.tracking.googlemap


import android.os.Bundle
import androidx.lifecycle.observe
import com.eazyrento.AddressFilter
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.login.model.modelclass.AddressInfo
import com.eazyrento.tracking.data.model.Route
import com.eazyrento.tracking.googlemap.viewmodel.TrackingMapViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.view_map_top_location_card.*


class TrackingMapActivity : BaseActivity(), OnMapReadyCallback {

    private lateinit var mMapSetup: MapSetup


    private lateinit var mMap: GoogleMap

    private lateinit var trackingMapViewModel: TrackingMapViewModel

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

        setCurrentAddressOnTopInMap()

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

        mMapSetup = MapSetup(this, mMap)

        mMapSetup.mapSetting()

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

    private fun setCurrentAddressOnTopInMap(){
        val addressInfo = AddressInfo("","","","","",
            -1,false,0.0,0.0,"")

        val addressFilter = AddressFilter(this,addressInfo)
        addressFilter.getAddressByLocation(22.4,77.4,1)

        tv_address_line_map.text = addressInfo.address_line
    }
}