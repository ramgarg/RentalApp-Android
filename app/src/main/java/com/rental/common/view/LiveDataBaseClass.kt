package com.rental.common.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.rental.appbiz.retrofitapi.ApiObserver
import com.rental.appbiz.retrofitapi.ChangedListener
import com.rental.appbiz.retrofitapi.DataWrapper

open abstract class LiveDataBaseClass:BaseActivity() {

    abstract fun <T>moveOnSelecetedItem(type:T)
    abstract fun <T>onSuccessApiResult(data:T)

    /*
* Api results
* */
    protected fun <T> observeApiResult(liveData: LiveData<DataWrapper<T>>) {
        liveData.observe(this,
            ApiObserver<T>(this,object :
                ChangedListener<T> {
                override fun onSuccess(data: T) {
                    onSuccessApiResult(data)

                }
            })
        )
    }
    /*
    *  live data creater
    * */
    protected inline fun <reified T: ViewModel> callAPI():T{

        return ViewModelProviders.of(this).get(T::class.java)
    }
}