package com.eazyrento

import android.app.Application
import android.content.Context
import com.eazyrento.login.model.modelclass.UserInfo
import com.eazyrento.login.model.modelclass.UserProfile
import com.eazyrento.supporting.LocalManager
import com.eazyrento.webservice.ServiceGenrator
import com.facebook.AccessToken
import com.facebook.login.LoginManager
import io.paperdb.Paper


class EazyRantoApplication : Application() {

    override fun attachBaseContext(base: Context?) {

        val language =  Session.getInstance(context)?.getLocalLanguage()

        if (language!=null)
          super.attachBaseContext(LocalManager.updateResources(base!!,language))
        else
            super.attachBaseContext(base)
    }

    companion object{
        var context:Context?=null
         var defaultUserID:Int=-1
        // profile data in session
        var profileData: UserProfile?=null

        fun saveAccesesToken(userInfo: UserInfo){
            ServiceGenrator.retrofit =null
            Session.getInstance(context)
                ?.saveAccessToken(userInfo.access_token)

        }
        fun onLoginUpdateSession(userInfo: UserInfo){
            // one time set null b/c from now we will add header in retrofit app APIs
            //ServiceGenrator.retrofit =null

            Session.getInstance(context)
                ?.saveUserRole(userInfo.user_role)
            Session.getInstance(context)
                ?.saveUserID(userInfo.user_id)

            /*Session.getInstance(context)
                ?.saveAccessToken(userInfo.access_token)*/

        }

        fun onLogoutUpdateSession(){

            ServiceGenrator.retrofit =null

            Session.getInstance(context)
                ?.saveUserRole(null)
            Session.getInstance(context)
                ?.saveUserID(defaultUserID)
            Session.getInstance(context)
                ?.saveAccessToken(null)

            if(isUserLoginWithFB()){
                LoginManager.getInstance().logOut()
            }

        }

// check whether user is login or not
        fun isUserLogin():Boolean{

           val session=  Session.getInstance(context)
           session?.let {
               return !(session.getUserRole()==null || session.getUserID()==defaultUserID || session.getAccessToken()==null)
           }
            return false
        }
        fun isUserLoginWithFB():Boolean{
            val accessToken = AccessToken.getCurrentAccessToken()
            val isLoggedIn = accessToken != null && !accessToken.isExpired
            return isLoggedIn
        }

    }
    override fun onCreate() {
        super.onCreate()
        context = this
        Paper.init(applicationContext)
    }



}