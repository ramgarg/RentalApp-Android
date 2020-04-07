package com.rental.appbiz.retrofitapi

import android.content.Context
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.rental.Session
import com.rental.appbiz.AppBizLogger
import com.rental.appbiz.ErrorHandling
import com.rental.common.view.BaseActivity
import retrofit2.Response

class ApiObserver<T>(val context: Context?,private val changedListener: ChangedListener<T>):Observer<DataWrapper<T>> {

    override fun onChanged(t: DataWrapper<T>) {

       context?.let { if (it is BaseActivity){ it.hideProgress()} }

        if(t.data==null)
        {
            // error In API
            ErrorHandling<T>(context).onError(t)
        }
        else{
            AppBizLogger.log(AppBizLogger.LoggingType.INFO,Gson().toJson(t.data))
            changedListener.onSuccess(t.data)
        }
    }
}
interface ChangedListener<T>{
    fun onSuccess(dataWrapper: T)
//    fun onError(dataWrapper: DataWrapper<T>)

}