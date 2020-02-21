package com.rental.customer.otp.presenter

import android.widget.EditText
import android.widget.TextView
import com.rental.R
import com.rental.customer.otp.model.repositry.OTPResponse

class OTPActivityPresenter(otpView: OTPView) {
    private  var otpView:OTPView

    var otpResponse:OTPResponse
    init {
        this.otpView=otpView
        otpResponse= OTPResponse(otpView)
    }

    fun setMessage(message:String,textViewMessage: TextView){
        textViewMessage.text=message;
    }

    fun verifyOTP(editTextOTP: EditText){

        if(checkValidation(editTextOTP)){
            otpResponse.otpVerifyAPI()
        }
    }

    private fun checkValidation(editTextOTP: EditText):Boolean{

        if(editTextOTP.text.toString().isEmpty()){
            otpView.showToast("Please Enter Valid OTP")
        }else
            return true

        return false
    }
}