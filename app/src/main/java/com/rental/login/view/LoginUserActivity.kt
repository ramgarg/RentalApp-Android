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
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.common.view.BaseActivity
import com.rental.common.view.UserInfoAPP
import com.rental.customer.utils.Common
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.login.model.modelclass.DeviceInfo
import com.rental.login.model.modelclass.LoginUserReqModel
import com.rental.login.model.modelclass.LoginUserResModel
import com.rental.login.viewmodel.LoginUserViewModel
import kotlinx.android.synthetic.main.activity_login.*


class LoginUserActivity :BaseActivity(),AppBizLogin {

    override fun showToast(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }

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
                ApiObserver<LoginUserResModel>(object : ChangedListener<LoginUserResModel> {

                    override fun onSuccess(dataWrapper: LoginUserResModel) {
                        hideProgress()
                        Session.getInstance(this@LoginUserActivity)?.saveUserRole(dataWrapper.user_info.user_role)
                        Session.getInstance(this@LoginUserActivity)?.saveUserID(dataWrapper.user_info.user_id)

//                        moveToOtp()
                    }

                    override fun onError(dataWrapper: DataWrapper<LoginUserResModel>) {
                        hideProgress()
                        errorHandle(dataWrapper.error,dataWrapper.apiException)
                    }

                })
            )

        }
    }

    private fun createLoginUserReqModel(): LoginUserReqModel {

        val deviceInfo = DeviceInfo(Settings.Secure.getString(this.contentResolver,Settings.Secure.ANDROID_ID))
        return LoginUserReqModel(deviceInfo,ed_password.text.toString(),UserInfoAPP.REGISTRATIONS_SOURCE!!,
            UserInfoAPP.SOURCE,ed_email.text.toString())


    }

}