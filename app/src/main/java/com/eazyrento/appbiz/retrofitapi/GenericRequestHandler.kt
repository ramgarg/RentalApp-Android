package com.eazyrento.appbiz.retrofitapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.eazyrento.appbiz.AppBizLogger
import retrofit2.Call
import retrofit2.Response

/*
* GenericRequestHandler
* */
abstract  class GenericRequestHandler<R> {

    val mutableLiveData: MutableLiveData<DataWrapper<R>> = MutableLiveData<DataWrapper<R>>()

     fun doRequest(call: Call<R>): LiveData<DataWrapper<R>> {

         AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,call?.request().toString())

         call.enqueue(object : AppbizAPICallback<R>() {

            override fun handleResponseData(data: R,statusCode:Int) {

                mutableLiveData.value =
                    DataWrapper(
                        data,
                        null,
                        null,
                        statusCode
                    )

            }

            override fun handleError(response: Response<R>,statusCode:Int) {
                mutableLiveData.value =
                    DataWrapper(
                        null,
                        response,
                        null,
                        statusCode
                    )
            }

            override fun handleException(t: Throwable?) {
                mutableLiveData.value =
                    DataWrapper(null, null, t,0)
            }

        })
      return mutableLiveData
    }
}
