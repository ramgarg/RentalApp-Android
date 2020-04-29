package com.eazyrento.login.view

import android.os.Bundle
import android.widget.Toast
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.Validator
import com.eazyrento.login.model.modelclass.ForgotPasswordRequest
import com.eazyrento.login.viewmodel.ForgotPasswordViewModel
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity :BaseActivity() {

    var forgotPasswordRequest = ForgotPasswordRequest()

    override fun <T> moveOnSelecetedItem(type: T) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password);

        initialize()
//        forgotViewModel=ViewModelProviders.of(this).get(ForgotViewModel::class.java)
//        forgotViewModel.getForgotPasswordResponse()?.observe(this, Observer {


//        })
    }

    private fun initialize(){
        btn_send.setOnClickListener { checkValidation(ed_email.text.toString())
        img_back.setOnClickListener { finish() }}

    }
    private fun checkValidation(email: String): Boolean {
        if (email.isEmpty()) {
            Toast.makeText(this,ValidationMessage.VALID_EMAIL_ID, Toast.LENGTH_SHORT).show()
//            forgotPasswordInterface.showToast("Please Enter Email/Phone Number")
        } else if (!Validator.isEmailValid(email)) {
            Toast.makeText(this,ValidationMessage.VALID_EMAIL_ID,Toast.LENGTH_SHORT).show()
        }else{
            forgotPasswordRequest.username = email
            callAPI()?.let {
                it.observeApiResult(
                    it.callAPIActivity<ForgotPasswordViewModel>(this)
                        .forgotPassword(forgotPasswordRequest)
                    , this, this
                )
            }
            Toast.makeText(this,ValidationMessage.OTP_SENT,Toast.LENGTH_SHORT).show()

        }
        return false
    }
    override fun <T> onSuccessApiResult(data: T) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())
        MoveToAnotherComponent.moveToLoginUserActivity(this)
    }

}