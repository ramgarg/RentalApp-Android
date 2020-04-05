package com.rental.appbiz.retrofitapi

import com.rental.appbiz.AppBizLogger
import retrofit2.Call
import retrofit2.Response


open abstract class AppbizAPICallback<T> :retrofit2.Callback<T> {

    protected abstract fun handleResponseData(data: T)

    protected abstract fun handleError(response: Response<T>)

    protected abstract fun handleException(t: Throwable?)



    override fun onResponse(call: Call<T>, response: Response<T>) {

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,response?.toString())

        if (response.body() != null) {
            handleResponseData(response.body()!!)
        } else {
            handleError(response)
        }

    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,t?.toString())
        handleException(t)
    }

}