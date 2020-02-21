package com.rental.customer.login.presenter

import android.widget.EditText
import com.rental.customer.presenter.LoginInterface
import com.rental.customer.utils.APIServices
import com.rental.customer.utils.Validator

class LoginActivityPresenter(loginInterface: LoginInterface) {

    var loginInterface: LoginInterface
    private var apiclient: APIServices? = null

    init {
        this.loginInterface = loginInterface
//        apiclient = RetrofitInstance.client.create(APIServices::class.java)
    }

    fun login(email: EditText, password: EditText) {
        if (checkValidation(email, password)) {
            loginInterface.registration()


//             val call = apiclient?.login()
//             call?.enqueue(object : retrofit2.Callback<JsonElement> {
//                 override fun onFailure(call: Call<JsonElement>, t: Throwable) {
//                     TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                 }
//
//                 override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
//                     TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                 }
//
//             })
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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