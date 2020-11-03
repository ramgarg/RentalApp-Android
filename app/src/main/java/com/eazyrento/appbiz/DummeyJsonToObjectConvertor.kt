package com.eazyrento.appbiz

import com.google.gson.Gson

object DummeyJsonToObjectConvertor {


    inline fun <reified T>convertJsonToClass(json:String): T {
        val data = Gson().fromJson(json,T::class.java)
        return data
    }

}