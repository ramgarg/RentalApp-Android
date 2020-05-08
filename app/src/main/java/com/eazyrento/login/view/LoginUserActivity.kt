package com.eazyrento.login.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import com.eazyrento.EazyRantoApplication
import com.eazyrento.R
import com.eazyrento.Session
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.appbiz.AppBizLogin
import com.eazyrento.common.view.UserInfoAPP
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.login.model.modelclass.DeviceInfo
import com.eazyrento.login.model.modelclass.LoginUserReqModel
import com.eazyrento.login.model.modelclass.LoginUserResModel
import com.eazyrento.login.viewmodel.LoginUserViewModel
import com.eazyrento.supporting.MyJsonParser
import com.eazyrento.webservice.ServiceGenrator
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject


class LoginUserActivity : AppBizLogin() {
    //fb permittion
    private companion object {
        const val EMAIL = "email"
        const val PROFILE = "public_profile"
    }
    private var mCallbackManager:CallbackManager?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        // vallidation for if user is already login
        if(EazyRantoApplication.isUserLogin())
            sendUserReleventPanel(Session.getInstance(EazyRantoApplication.context)?.getUserRole())

//        /*setProfileDataFromFP()*/

    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"onNewIntent")
    }

    // lisetner
    fun onRegisterClick(view:View){
        MoveToAnotherComponent.moveToActivityNormal<RegistrationUserActivity>(this)
    }

    fun onForgotPasswordClick(view: View){
        MoveToAnotherComponent.moveToActivityNormal<ForgotPasswordActivity>(this)
    }

    fun onLoginClick(view: View){

        if(failCheckValdationLoginCredintitial(ed_email,ed_password)){
            return
        }


        login(createLoginUserReqModel(ed_password.text.toString(),ed_email.text.toString(),UserInfoAPP.BY_NORMAL))

    }
    //fb login
    fun onFBClick(view: View){
        return

        mCallbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().logInWithReadPermissions(this, listOf(EMAIL, PROFILE))

        LoginManager.getInstance().registerCallback(mCallbackManager,object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,result.toString())
                setProfileDataFromFP()
            }

            override fun onCancel() {
                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"onCancel")
            }

            override fun onError(error: FacebookException?) {
                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,error.toString())
            }

        })

    }

    fun getProfileDataFromFB(loginUserReqModel: LoginUserReqModel){
            GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken()
            ) { `object`, response ->
                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,`object`.toString())
                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,response.toString())

                MyJsonParser.parseFBData(`object`,loginUserReqModel)
            }
    }

    fun setProfileDataFromFP(){
        val obj = createLoginUserReqModel("dummy_password","EMAIL",UserInfoAPP.BY_FACEBOOK)
        obj.user_role =  UserInfoAPP.user_role
        getProfileDataFromFB(obj)

        //login(obj)

    }
    fun onGmailClick(view: View){

    }
    fun onSkipLoginClick(view: View){
        Common.showDialog("UserType","",this,R.layout.rental_dialog)
    }

    // by user login by email, gamail , fb
    private fun login(loginUserReqModel:LoginUserReqModel) {

            callAPI()?.let {
                it.observeApiResult(
                    it.callAPIActivity<LoginUserViewModel>(this)
                        .loginUser(loginUserReqModel)
                    , this, this
                )
            }


    }

    private fun sendUserReleventPanel(userRole: String?) {

        when(userRole){
            UserInfoAPP.AGENT-> MoveToAnotherComponent.moveToAgentHomeActivity(this)
            UserInfoAPP.CUSTOMER-> MoveToAnotherComponent.moveToHomeActivity(this)
            UserInfoAPP.MERCHANT-> MoveToAnotherComponent.moveToMerchantMainActivity(this)
        }

    }

    private fun createLoginUserReqModel(password:String,email:String,byUser:String): LoginUserReqModel {

        val deviceInfo = DeviceInfo(
            Settings.Secure.getString(
                this.contentResolver,
                Settings.Secure.ANDROID_ID
            )
        )
        return LoginUserReqModel(
            deviceInfo,
            password,
            byUser,
            UserInfoAPP.SOURCE,
            email
        )

    }

    override fun <T> onSuccessApiResult(data: T) {

        if (data is LoginUserResModel) {

            EazyRantoApplication.onLoginUpdateSession(data.user_info)

            sendUserReleventPanel(data.user_info.user_role)


        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        AppBizLogger.log(
            AppBizLogger.LoggingType.DEBUG,
            "" + requestCode + "--" + resultCode + "---" + data.toString()
        )

        if (resultCode == Activity.RESULT_OK && mCallbackManager != null)
        {
            mCallbackManager!!.onActivityResult(requestCode, resultCode, data)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}