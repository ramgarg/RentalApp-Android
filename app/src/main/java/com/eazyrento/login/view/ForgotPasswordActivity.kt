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
import com.eazyrento.supporting.PhoneNumberFormat
import com.eazyrento.supporting.PhoneTextWatcher
import com.eazyrento.supporting.isValidPhoneNumber
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_forgot_password.ed_email
import kotlinx.android.synthetic.main.activity_login.*

class ForgotPasswordActivity :BaseActivity() {

//    var forgotPasswordRequest = ForgotPasswordRequest()

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        topBarWithBackIconAndTitle(resources.getString(R.string.title_forgot_pass))

        val phoneNumberFormat = PhoneNumberFormat(this)

        ed_email.addTextChangedListener(PhoneTextWatcher(phoneNumberFormat,ed_email))

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

        return when{
           email.isNotEmpty() && (Validator.isEmailValid(email) || isValidPhoneNumber(email,this)) -> false
           else->{Toast.makeText(this,ValidationMessage.VALID_EMAIL_PHONE, Toast.LENGTH_SHORT).show()
               true}
        }

      /*  if (email.isEmpty() || Validator.isEmailValid(email).not()) {
            if (isValidPhoneNumber(email).not())
                Toast.makeText(this,ValidationMessage.VALID_EMAIL_PHONE, Toast.LENGTH_SHORT).show()
            return true
        }
        return false*/
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