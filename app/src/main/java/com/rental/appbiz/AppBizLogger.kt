package com.rental.appbiz

import android.util.Log
import com.rental.Env

interface AppBizLogger {

    companion object{

        private const val TAG = "AppBizLogger"

          fun log(type:LoggingType,msg:String){
             if(Env.isLogging){

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