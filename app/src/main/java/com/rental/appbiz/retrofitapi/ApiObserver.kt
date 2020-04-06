package com.rental.appbiz.retrofitapi

import androidx.lifecycle.Observer
import com.rental.Session
import com.rental.appbiz.AppBizLogger
import retrofit2.Response

class ApiObserver<T>(private val changedListener: ChangedListener<T>):Observer<DataWrapper<T>> {

    override fun onChanged(t: DataWrapper<T>) {
        if(t.data==null)
        {
            // error In API
            changedListener.onError(t)
        }
        else{
            AppBizLogger.log(AppBizLogger.LoggingType.INFO,t.data.toString())
            changedListener.onSuccess(t.data)

           /* Session.getInstance(this)?.saveUserRole(user_role)
            Session.getInstance(this)?.saveUserID(it.data.user_id)
            moveToOtp()*/

        }
    }
}
interface ChangedListener<T>{
    fun onSuccess(dataWrapper: T)
    fun onError(dataWrapper: DataWrapper<T>)

}