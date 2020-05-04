package com.eazyrento.login.view

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
import com.eazyrento.webservice.ServiceGenrator
import kotlinx.android.synthetic.main.activity_login.*


class LoginUserActivity : AppBizLogin() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
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

        login(UserInfoAPP.BY_NORMAL)

    }
    fun onSkipLoginClick(view: View){
        Common.showDialog("UserType","",this,R.layout.rental_dialog)
    }

    // by user login by email, gamail , fb
    private fun login(byUser: String) {

            UserInfoAPP.REGISTRATIONS_SOURCE = byUser

            val loginUserReqModel = createLoginUserReqModel()

            callAPI()?.let {
                it.observeApiResult(
                    it.callAPIActivity<LoginUserViewModel>(this)
                        .loginUser(loginUserReqModel)
                    , this, this
                )
            }


    }

    private fun sendUserReleventPanel(userRole: String) {

        when(userRole){
            UserInfoAPP.AGENT-> MoveToAnotherComponent.moveToAgentHomeActivity(this)
            UserInfoAPP.CUSTOMER-> MoveToAnotherComponent.moveToHomeActivity(this)
            UserInfoAPP.MERCHANT-> MoveToAnotherComponent.moveToMerchantMainActivity(this)
        }

    }

    private fun createLoginUserReqModel(): LoginUserReqModel {

        val deviceInfo = DeviceInfo(
            Settings.Secure.getString(
                this.contentResolver,
                Settings.Secure.ANDROID_ID
            )
        )
        return LoginUserReqModel(
            deviceInfo,
            ed_password.text.toString(),
            UserInfoAPP.REGISTRATIONS_SOURCE!!,
            UserInfoAPP.SOURCE,
            ed_email.text.toString()
        )

    }

    override fun <T> onSuccessApiResult(data: T) {

        if (data is LoginUserResModel) {

            EazyRantoApplication.onLoginUpdateSession(data.user_info)

            /*// one time set null b/c from now we will add header in retrofit app APIs
            ServiceGenrator.retrofit =
                null

            Session.getInstance(this@LoginUserActivity)
                ?.saveUserRole(data.user_info.user_role)
            Session.getInstance(this@LoginUserActivity)
                ?.saveUserID(data.user_info.user_id)
            Session.getInstance(this@LoginUserActivity)
                ?.saveAccessToken(data.user_info.access_token)

            UserInfoAPP.user_role = data.user_info.user_role*/

            sendUserReleventPanel(data.user_info.user_role)


        }
    }
}