package com.rental.login.viewmodel

import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rental.login.model.modelclass.LoginUserRequest
import com.rental.login.model.repositry.LoginUserRepo


class LoginViewModel :ViewModel(){

     var loginUserRepo:LoginUserRepo
    private var requestMutableLiveData: MutableLiveData<LoginUserRequest>? = null

    init {
        loginUserRepo= LoginUserRepo()

    }

    fun getLogin(): MutableLiveData<LoginUserRequest>? {
        if (requestMutableLiveData == null) {
            requestMutableLiveData = MutableLiveData<LoginUserRequest>()
        }
        return requestMutableLiveData
    }

    fun onClick(email:EditText,password:EditText) {
        val loginUser = LoginUserRequest(
            email.text.toString(),
            password.text.toString()
        )
        loginUserRepo.loginAPI(loginUser)

    }
}