package com.rental

import android.content.Context
import android.content.SharedPreferences

class Session private constructor(context: Context?){

    private  var sSharedPreferences: SharedPreferences?=null

    init {
        if (sSharedPreferences == null)
            sSharedPreferences = context?.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    }

    companion object {
        private var session: Session? = null

        fun getInstance(context: Context?): Session? {
            if (session == null) {
                session = Session(context)
            }
            return session
        }
    }
    /*save user role*/
    fun saveUserRole(value:String?){
        val edit = sSharedPreferences?.edit()
        edit?.putString(PrefKey.USER_ROLE, value)
        edit?.apply()
    }
    /*get user role*/
    fun getUserRole():String?{
        return sSharedPreferences?.getString(PrefKey.USER_ROLE,null)
    }

    /*save user id*/
    fun saveUserID(value:Int){
        val edit = sSharedPreferences?.edit()
        edit?.putInt(PrefKey.USER_ID, value)
        edit?.apply()
    }
    /*get user id*/
    fun getUserID():Int?{
        return sSharedPreferences?.getInt(PrefKey.USER_ID,-1)
    }
}