package com.rental.appbiz

import android.util.Log

interface AppBizLogger {

    companion object{

        private const val TAG = "AppBizLogger"
        private const val IS_LOGGING = true

          fun log(type:LoggingType,msg:String){
             if(IS_LOGGING){

                 when(type){
                     LoggingType.INFO ->Log.i(TAG,msg)
                     LoggingType.ERROR ->Log.e(TAG,msg)
                     LoggingType.VERBOSE ->Log.v(TAG,msg)
                     LoggingType.DEBUG ->Log.d(TAG,msg)
                 }

             }
          }
        }

    enum class LoggingType{
        INFO,
        ERROR,
        VERBOSE,
        DEBUG

    }


}