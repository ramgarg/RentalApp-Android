package com.eazyrento.tracking.googlemap.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.tracking.data.api.GoogleApiCreation
import com.eazyrento.tracking.data.model.Response
import com.eazyrento.tracking.data.model.Route
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TrackingMapViewModel : ViewModel() {
    val mutableLiveData =  MutableLiveData<Response>()

    fun getDirectionApiResponse(mode:String,routingPreference:String,origin:String,destination:String,apiKey:String): LiveData<Response> {
        googleDirectionApiCall(mode,routingPreference,origin, destination, apiKey)
        return mutableLiveData
    }

    private fun googleDirectionApiCall(mode:String,routingPreference:String,origin:String,destination:String,apiKey:String) {

        GoogleApiCreation().getDirectionApi().getDirections(
            mode, routingPreference,
            origin, destination,apiKey)?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : SingleObserver<Response?> {
                override fun onSubscribe(d: Disposable) {}
                override fun onSuccess(result: Response) {
                    mutableLiveData.value = result
                   /* val routeList: List<Route> = result.routes
                    for (route in routeList) {
                        val polyLine: String = route.overviewPolyline.points
                        val polyLineLatLongList = mMapSetup.decodePoly(polyLine)
                        mMapSetup.drawPolyLineAndAnimateCar(polyLineLatLongList)
                    }*/

                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
    }
}