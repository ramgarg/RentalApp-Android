package com.eazyrento

import android.app.Application
import android.content.Context
import com.eazyrento.common.view.UserInfoAPP
import com.eazyrento.login.model.modelclass.ProfileModelReqRes
import com.eazyrento.login.model.modelclass.UserInfo
import com.eazyrento.login.model.modelclass.UserProfile
import com.eazyrento.webservice.ServiceGenrator

class EazyRantoApplication : Application() {

    companion object{
        var context:Context?=null
         var defaultUserID:Int=-1
        // profile data in session
        var profileData: UserProfile?=null

        fun onLoginUpdateSession(userInfo: UserInfo){
            // one time set null b/c from now we will add header in retrofit app APIs
            ServiceGenrator.retrofit =null

            Session.getInstance(context)
                ?.saveUserRole(userInfo.user_role)
            Session.getInstance(context)
                ?.saveUserID(userInfo.user_id)
            Session.getInstance(context)
                ?.saveAccessToken(userInfo.access_token)

//            UserInfoAPP.user_role = userInfo.user_role

        }

        fun onLogoutUpdateSession(){

            ServiceGenrator.retrofit =null

            Session.getInstance(context)
                ?.saveUserRole(null)
            Session.getInstance(context)
                ?.saveUserID(defaultUserID)
            Session.getInstance(context)
                ?.saveAccessToken(null)

//            UserInfoAPP.user_role = null
        }
// check whether user is login or not
        fun isUserLogin():Boolean{

           val session=  Session.getInstance(context)
           session?.let {
               return !(session.getUserRole()==null || session.getUserID()==defaultUserID || session.getAccessToken()==null)
           }
            return false
        }

    }
    override fun onCreate() {
        super.onCreate()
        context = this
    }



}