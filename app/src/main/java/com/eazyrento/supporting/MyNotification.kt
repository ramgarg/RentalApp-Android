package com.eazyrento.supporting

import android.content.Intent
import com.eazyrento.Constant
import com.eazyrento.appbiz.AppBizLogger
import java.lang.Exception

interface  DeeplinkEvents {

    companion object{

        var mapPayLoadDataDeeplink:Map<String,String>?=null
        const val KEY_DEEPLINK = "deep_link"
        const val KEY_ORDER_ID = "order_id"


        const val PAYMENT       = "payment"
        const val ORDER_SUMMARY  = "order_summary"
        const val ORDER_LISTING  = "order_listing"
        const val BOOKINGS      = "bookings"
        const val none =""
    }

}
interface onDeeplinking{
    fun onEventWith(event:String)
}
 fun isDeeplinkingFromNotification(intent:Intent?):Boolean{
    try {

        if (intent==null)
            return false

        var isDeeplink=false

         intent.extras?.let {
             isDeeplink = it.getBoolean(Constant.DEEPLINK_VALUE,false)
         }

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"is Deeplinking:$isDeeplink")
        return isDeeplink
    }
    catch (e: Exception){
        e.printStackTrace()
    }
    return false
}

