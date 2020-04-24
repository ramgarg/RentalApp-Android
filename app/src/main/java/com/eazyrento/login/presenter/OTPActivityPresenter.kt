package com.eazyrento.login.presenter

//package com.rental.customer.otp.presenter
//
//import android.widget.EditText
//import android.widget.TextView
//import com.rental.login.model.repositry.OTPRepository
//
//class OTPActivityPresenter(otpView: OTPView) {
//    private  var otpView:OTPView
//
//    var otpRepository:OTPRepository
//    init {
//        this.otpView=otpView
//        otpRepository= OTPRepository(otpView)
//    }
//
//    fun setMessage(message:String,textViewMessage: TextView){
//        textViewMessage.text=message;
//    }
//
//    fun verifyOTP(editTextOTP: EditText){
//
//        if(checkValidation(editTextOTP)){
//            otpRepository.otpVerifyAPI()
//        }
//    }
//
//    private fun checkValidation(editTextOTP: EditText):Boolean{
//
//        if(editTextOTP.text.toString().isEmpty()){
//            otpView.showToast("Please Enter Valid OTP")
//        }else
//            return true
//
//        return false
//    }
//}