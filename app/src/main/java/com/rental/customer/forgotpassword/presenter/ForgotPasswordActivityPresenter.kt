package com.rental.customer.forgotpassword.presenter

import com.rental.customer.presenter.ForgotPasswordInterface
import com.rental.customer.utils.Validator

class ForgotPasswordActivityPresenter(forgotPasswordInterface: ForgotPasswordInterface) {

    private var forgotPasswordInterface: ForgotPasswordInterface
    init {
        this.forgotPasswordInterface=forgotPasswordInterface
    }


    fun forgotPasswordAPI(email:String){
        if(checkValidation(email)){
            forgotPasswordInterface.showToast("Password reset link have been sent your email/phone number.")
        }

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