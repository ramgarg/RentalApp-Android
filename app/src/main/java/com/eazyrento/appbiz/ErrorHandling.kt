package com.eazyrento.appbiz

import android.content.Context
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.customer.utils.Common
import org.json.JSONObject
import java.lang.Exception

class ErrorHandling<T>(val context: Context?){

    fun onError(dataWrapper: DataWrapper<T>) {

        if(dataWrapper.error!=null){
//            context?.let { Common.showToastString(it,dataWrapper.error.message()+"-"+dataWrapper.error.errorBody()?.string()) }

            var detailsMessage = context?.getString(R.string.defualt_error_message)
            try {
                val json = dataWrapper.error.errorBody()?.string()
                val jSONObject = JSONObject(json)

                detailsMessage =  jSONObject.getString(Constant.key_error_message)

            }catch (e:Exception){
                e.printStackTrace()
            }

            context?.let { Common.showToastString(it,""+detailsMessage)}
        }
        else if(dataWrapper.apiException!=null){
            context?.let { Common.showToastString(it,dataWrapper.apiException.toString()) }
        }
    }

}