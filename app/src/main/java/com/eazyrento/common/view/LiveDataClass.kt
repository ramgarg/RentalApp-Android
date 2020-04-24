package com.eazyrento.common.view

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.eazyrento.appbiz.retrofitapi.ApiObserver
import com.eazyrento.appbiz.retrofitapi.ChangedListener
import com.eazyrento.appbiz.retrofitapi.DataWrapper

open abstract class LiveDataClass(val apiResult: ApiResult){
    /*
* Api results
* */
    fun <T> observeApiResult(liveData: LiveData<DataWrapper<T>>, owner :LifecycleOwner, context:Context) {
        liveData.observe(owner,
            ApiObserver(context, object :
                ChangedListener<T> {
                override fun onSuccess(data: T) {
                    apiResult.onSuccessApiResult(data)

                }
            })
        )
    }
}

class LiveDataFragmentClass(apiResult: ApiResult):
    LiveDataClass(apiResult){
    /*
    *  live data creater
    * */
     inline fun <reified T: ViewModel> callAPIFragment(type:Fragment):T{

        return ViewModelProviders.of(type).get(T::class.java)
    }
}

class LiveDataActivityClass( apiResultActivity: ApiResult):
    LiveDataClass(apiResultActivity){

    /*
    *  live data creater
    * */
    inline fun <reified T: ViewModel> callAPIActivity(type:FragmentActivity):T{

        return ViewModelProviders.of(type).get(T::class.java)
    }
}


interface ApiResult{
    fun <T>onSuccessApiResult(data:T)
}
