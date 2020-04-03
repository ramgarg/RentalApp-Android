package com.rental.login.viewmodel

import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rental.login.model.modelclass.LoginRequest
import com.rental.login.model.repositry.LoginRespository


class LoginViewModel :ViewModel(){

     var loginRespository:LoginRespository
    private var requestMutableLiveData: MutableLiveData<LoginRequest>? = null

    init {
        loginRespository= LoginRespository()

    }

    fun getLogin(): MutableLiveData<LoginRequest>? {
        if (requestMutableLiveData == null) {
            requestMutableLiveData = MutableLiveData<LoginRequest>()
        }
        return requestMutableLiveData
    }

    fun onClick(email:EditText,password:EditText) {
        val loginUser = LoginRequest(
            email.text.toString(),
            password.text.toString()
        )
        loginRespository.loginAPI(loginUser)

    }
}