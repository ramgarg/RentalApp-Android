package com.rental.common.view

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.rental.appbiz.retrofitapi.ApiObserver
import com.rental.appbiz.retrofitapi.ChangedListener
import com.rental.appbiz.retrofitapi.DataWrapper

open abstract class LiveDataClass(val apiResult: ApiResult){
    /*
* Api results
* */
    fun <T,O:LifecycleOwner,C:Context> observeApiResult(liveData: LiveData<DataWrapper<T>>, owner :O, context:C) {
        liveData.observe(owner,
            ApiObserver<T>(context,object :
                ChangedListener<T> {
                override fun onSuccess(data: T) {
                    apiResult.onSuccessApiResult(data)

                }
            })
        )
    }
}

class LiveDataFragmentClass(apiResult: ApiResult):LiveDataClass(apiResult){
    /*
    *  live data creater
    * */
     inline fun <reified T: ViewModel,K:Fragment> callAPIFragment(type:K):T{

        return ViewModelProviders.of(type).get(T::class.java)
    }
}

class LiveDataActivityClass( apiResultActivity: ApiResult):LiveDataClass(apiResultActivity){
    /*
    *  live data creater
    * */
    inline fun <reified T: ViewModel,K:FragmentActivity> callAPIActivity(type:K):T{

        return ViewModelProviders.of(type).get(T::class.java)
    }
}


interface ApiResult{
    fun <T>onSuccessApiResult(data:T)
}
