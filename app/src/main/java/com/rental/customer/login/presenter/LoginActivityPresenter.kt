package com.rental.customer.login.presenter

import android.widget.EditText
import com.rental.customer.login.model.repositry.LoginResponse
import com.rental.customer.presenter.LoginInterface
import com.rental.customer.utils.Validator

class LoginActivityPresenter(loginInterface: LoginInterface) {

    var loginInterface: LoginInterface

     var loginResponse:LoginResponse

    init {
        this.loginInterface = loginInterface
        loginResponse= LoginResponse(loginInterface)
    }

    fun login(email: EditText, password: EditText) {
        if (checkValidation(email, password)) {
            loginResponse.loginAPI()
            }
    }


    private fun checkValidation(email: EditText, password: EditText): Boolean {
        if (email.text.toString().isEmpty() && password.text.toString().isEmpty()) {
            loginInterface.showToast("Please Enter Email/Phone Number and Password")
        } else if (!Validator.isEmailValid(email.text.toString())) {
            loginInterface.showToast("Please Enter Valid Email")
            email.requestFocus()
        } else if (password.text.toString().isEmpty()) {
            password.requestFocus()
            loginInterface.showToast("Please Enter Valid Password")
        } else if (!Validator.isPasswordValid(password.text.toString())) {
            loginInterface.showToast("Invalid Password! minimum length 8")
        }else{
            return true
        }
        return false
    }

}