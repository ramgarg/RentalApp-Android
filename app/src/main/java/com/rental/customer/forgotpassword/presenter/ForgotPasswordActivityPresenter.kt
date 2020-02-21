package com.rental.customer.forgotpassword.presenter

import com.rental.customer.forgotpassword.model.repositry.ForgotPasswordResponse
import com.rental.customer.login.model.repositry.LoginResponse
import com.rental.customer.presenter.ForgotPasswordInterface
import com.rental.customer.utils.Validator

class ForgotPasswordActivityPresenter(forgotPasswordInterface: ForgotPasswordInterface) {

    private var forgotPasswordInterface: ForgotPasswordInterface
    var forgotPasswordResponse: ForgotPasswordResponse
    init {
        this.forgotPasswordInterface=forgotPasswordInterface
        forgotPasswordResponse= ForgotPasswordResponse(forgotPasswordInterface)
    }

    fun forgotPasswordAPI(email:String){
        if(checkValidation(email)){
            forgotPasswordResponse.forgotPasswordAPI(this)
        }

    }

    fun onSuccess(){
        forgotPasswordInterface.onSuccess()
    }

    fun onFail(){
        forgotPasswordInterface.onFailed()
    }

    private fun checkValidation(email: String): Boolean {
        if (email.isEmpty()) {
            forgotPasswordInterface.showToast("Please Enter Email/Phone Number")
        } else if (!Validator.isEmailValid(email)) {
            forgotPasswordInterface.showToast("Please Enter Valid Email/Phone Number")
        }else{
            return true
        }
        return false
    }
}