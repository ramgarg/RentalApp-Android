package com.rental.appbiz.retrofitapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rental.appbiz.AppBizLogger
import retrofit2.Call
import retrofit2.Response

/*
* GenericRequestHandler
* */
abstract  class GenericRequestHandler<R> {

    abstract fun makeRequest(): Call<R>

    val mutableLiveData: MutableLiveData<DataWrapper<R>> = MutableLiveData<DataWrapper<R>>()

    public fun doRequest(): LiveData<DataWrapper<R>> {
        val call = makeRequest()

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,call?.request().toString())

        makeRequest().enqueue(object : AppbizAPICallback<R>() {

            override fun handleResponseData(data: R) {

                mutableLiveData.value = DataWrapper(data, null, null)

            }

            override fun handleError(response: Response<R>) {
                mutableLiveData.value = DataWrapper(null, response, null)
            }

            override fun handleException(t: Throwable?) {
                mutableLiveData.value = DataWrapper(null, null, t)
            }

        })
      return mutableLiveData
    }
}
