package com.eazyrento.login.view

import android.os.Bundle
import android.provider.Settings
import androidx.lifecycle.ViewModelProviders
import com.eazyrento.R
import com.eazyrento.Session
import com.eazyrento.appbiz.AppBizLogin
import com.eazyrento.appbiz.retrofitapi.ApiObserver
import com.eazyrento.appbiz.retrofitapi.ChangedListener
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

        initialize()
    }

    private fun initialize(){

        tv_registration.setOnClickListener { MoveToAnotherComponent.moveToRegistrationActivity(this) }

        tv_forgot_password.setOnClickListener { MoveToAnotherComponent.moveToForgotPasswordActivity(this) }

        btn_login.setOnClickListener { login()/*checkValidation(ed_email,ed_password)*/ }

        tv_skip.setOnClickListener {

            Common.showDialog("UserType","",this,R.layout.rental_dialog)
        }

    }

    private fun login() {
        if(!failCheckValdationLoginCredintitial(ed_email,ed_password)){

            UserInfoAPP.REGISTRATIONS_SOURCE = UserInfoAPP.BY_NORMAL

            val loginUserReqModel = createLoginUserReqModel()

            callAPI()?.let {
                it.observeApiResult(
                    it.callAPIActivity<LoginUserViewModel>(this)
                        .loginUser(loginUserReqModel)
                    , this, this
                )
            }

            /* val viewModel = ViewModelProviders.of(this).get(LoginUserViewModel::class.java)

            showProgress()

           viewModel.loginUser(loginUserReqModel).observe(this,
                ApiObserver(this@LoginUserActivity,
                    object :
                        ChangedListener<LoginUserResModel> {
                        override fun onSuccess(dataWrapper: LoginUserResModel) {
                            // ont time set null b/c from now we will add header in retrofit app APIs
                            ServiceGenrator.retrofit =
                                null

                            Session.getInstance(this@LoginUserActivity)
                                ?.saveUserRole(dataWrapper.user_info.user_role)
                            Session.getInstance(this@LoginUserActivity)
                                ?.saveUserID(dataWrapper.user_info.user_id)
                            Session.getInstance(this@LoginUserActivity)
                                ?.saveAccessToken(dataWrapper.user_info.access_token)
                            sendToUserRole(dataWrapper.user_info.user_role)

                        }
                    })
            )*/
        }
    }

    private fun sendToUserRole(userRole: String) {
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

            // one time set null b/c from now we will add header in retrofit app APIs
            ServiceGenrator.retrofit =
                null

            Session.getInstance(this@LoginUserActivity)
                ?.saveUserRole(data.user_info.user_role)
            Session.getInstance(this@LoginUserActivity)
                ?.saveUserID(data.user_info.user_id)
            Session.getInstance(this@LoginUserActivity)
                ?.saveAccessToken(data.user_info.access_token)
            sendToUserRole(data.user_info.user_role)
        }
    }
}