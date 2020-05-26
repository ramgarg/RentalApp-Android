package com.eazyrento.login.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.Validator
import com.eazyrento.login.model.modelclass.ForgotPasswordRequest
import com.eazyrento.login.viewmodel.ForgotPasswordViewModel
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity :BaseActivity() {

//    var forgotPasswordRequest = ForgotPasswordRequest()

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

//        initialize()

        topBarWithBackIconAndTitle(resources.getString(R.string.title_forgot_pass))

    }
    fun onSendButtonClick(view:View){
        val email =ed_email.text.toString()

        if(isCheckValidationFailed(email))
            return

//        forgotPasswordRequest.username = email

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<ForgotPasswordViewModel>(this)
                    .forgotPassword(ForgotPasswordRequest(email))
                , this, this
            )
        }


    }


    private fun isCheckValidationFailed(email: String): Boolean {
        if (email.isEmpty() || !Validator.isEmailValid(email)) {
            Toast.makeText(this,ValidationMessage.VALID_EMAIL_ID, Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }

    override fun <T> onSuccessApiResult(data: T) {

        Toast.makeText(this,ValidationMessage.FORGOT_PASSWORD,Toast.LENGTH_SHORT).show()

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        if (data is JsonElement){

            MoveToAnotherComponent.moveToActivityWithIntentValue<LoginUserActivity>(this,
                Constant.INTENT_RESET_SUCCESSFULY,1)

           // showToast(ValidationMessage.REQUEST_SUCCESSED)
        }

    }

}