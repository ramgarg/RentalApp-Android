package com.eazyrento.appbiz

import android.content.Context
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.customer.utils.Common
import retrofit2.Response

class ErrorHandling<T>(val context: Context?){

    fun onError(dataWrapper: DataWrapper<T>) {

        if(dataWrapper.error!=null){
            context?.let { Common.showToastString(it,dataWrapper.error.message()+"-"+dataWrapper.error.errorBody()?.string()) }
        }
        else if(dataWrapper.apiException!=null){
            context?.let { Common.showToastString(it,dataWrapper.apiException.toString()) }
        }
    }

}