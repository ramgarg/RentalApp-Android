package com.eazyrento.login.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.forgotpassword.model.modelClass.OTPRequest
import com.eazyrento.customer.forgotpassword.model.modelClass.OTPResponse
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.login.model.modelclass.ResendOTPRequest
import com.eazyrento.login.viewmodel.LoginOTPViewModel
import com.eazyrento.login.viewmodel.ResendOTPViewModel
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_otp.*
import java.lang.NumberFormatException

class OTPActivity :BaseActivity(){


//    var otpRequest = OTPRequest()

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        topBarWithBackIconAndTitle(resources.getString(R.string.title_OTP))

    }

   fun onResendOTPClick(view:View){
        val userID=intent.getIntExtra(Constant.INTENT_OTP_USER_ID,-1)

       callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<ResendOTPViewModel>(this)
                    .resendOTPAPI(ResendOTPRequest(userID))
                , this, this
            )
        }

    }

    fun onContinueClick(view:View){

        val userID = intent.getIntExtra(Constant.INTENT_OTP_USER_ID,-1)
        var passcode:Int
        try {
             passcode =ed_otp.text.toString().toInt()
        }
        catch (e:NumberFormatException){
            showToast(ValidationMessage.OTP_VALID_NUMBER)
            return
        }



        if (checkValidationFailed(userID,passcode)){
            return
        }

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<LoginOTPViewModel>(this)
                    .OTPAPI(OTPRequest(userID,passcode))
                , this, this
            )
        }
    }

    private fun checkValidationFailed(userID: Int, passcode: Int):Boolean{

        if(userID==-1 || passcode<=0){
            Toast.makeText(this,ValidationMessage.VALID_OTD,Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }

    override fun <T> onSuccessApiResult(data: T) {

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        if (data is OTPResponse){
//             {"status":200}
            MoveToAnotherComponent.moveToActivityWithIntentValue<LoginUserActivity>(this,Constant.INTENT_OTP_TO_LOGIN,1)

        }

//        MoveToAnotherComponent.moveToHomeActivity(this)
    }



}