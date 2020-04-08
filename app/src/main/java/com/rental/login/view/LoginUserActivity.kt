package com.rental.login.view

import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.rental.R
import com.rental.Session
import com.rental.appbiz.AppBizLogin
import com.rental.appbiz.retrofitapi.ApiObserver
import com.rental.appbiz.retrofitapi.ChangedListener
import com.rental.common.view.BaseActivity
import com.rental.common.view.UserInfoAPP
import com.rental.customer.utils.Common
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.login.model.modelclass.DeviceInfo
import com.rental.login.model.modelclass.LoginUserReqModel
import com.rental.login.model.modelclass.LoginUserResModel
import com.rental.login.viewmodel.LoginUserViewModel
import com.rental.webservice.ServiceGenrator
import kotlinx.android.synthetic.main.activity_login.*


class LoginUserActivity :AppBizLogin() {

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

            val viewModel = ViewModelProviders.of(this).get(LoginUserViewModel::class.java)

            showProgress()

            viewModel.loginUser(loginUserReqModel).observe(this,
                ApiObserver(this@LoginUserActivity,object : ChangedListener<LoginUserResModel> {
                    override fun onSuccess(dataWrapper: LoginUserResModel) {
                        // ont time set null b/c from now we will add header in retrofit app APIs
                        ServiceGenrator.retrofit = null

                        Session.getInstance(this@LoginUserActivity)?.saveUserRole(dataWrapper.user_info.user_role)
                        Session.getInstance(this@LoginUserActivity)?.saveUserID(dataWrapper.user_info.user_id)
                        Session.getInstance(this@LoginUserActivity)?.saveAccessToken(dataWrapper.user_info.access_token)
                        sendToUserRole(dataWrapper.user_info.user_role)

                    }
                })
            )
        }
    }

    private fun sendToUserRole(userRole: String) {
        when(userRole){
            UserInfoAPP.AGENT->MoveToAnotherComponent.moveToAgentHomeActivity(this)
            UserInfoAPP.CUSTOMER->MoveToAnotherComponent.moveToHomeActivity(this)
            UserInfoAPP.MERCHANT->MoveToAnotherComponent.moveToMerchantActivity(this)
        }

    }

    private fun createLoginUserReqModel(): LoginUserReqModel {

        val deviceInfo = DeviceInfo(Settings.Secure.getString(this.contentResolver,Settings.Secure.ANDROID_ID))
        return LoginUserReqModel(deviceInfo,ed_password.text.toString(),UserInfoAPP.REGISTRATIONS_SOURCE!!,
            UserInfoAPP.SOURCE,ed_email.text.toString())

    }

}