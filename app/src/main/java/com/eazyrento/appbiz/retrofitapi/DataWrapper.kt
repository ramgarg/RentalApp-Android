package com.eazyrento.appbiz.retrofitapi

import retrofit2.Response

data class DataWrapper<T>(val data:T?, val error: Response<T>?, val apiException: Throwable?,val statusCode:Int)