package com.rental.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rental.login.model.modelclass.ForgotPasswordRequest
import com.rental.login.model.modelclass.ForgotPasswordResponse
import com.rental.login.model.repositry.ForgotPasswordRespository

class ForgotViewModel :ViewModel() {

    var forgotPasswordRespository: ForgotPasswordRespository
    private var responseMutableLiveData: MutableLiveData<ForgotPasswordResponse>? = null

    init {
        forgotPasswordRespository=
            ForgotPasswordRespository()
    }
    fun getForgotPasswordResponse(): MutableLiveData<ForgotPasswordResponse>? {
        if (responseMutableLiveData == null) {
            responseMutableLiveData = MutableLiveData<ForgotPasswordResponse>()
        }
        return responseMutableLiveData
    }

    fun forgotPasswordAPI(email:String){
        val forgotPasswordRequest =
            ForgotPasswordRequest(email)
            forgotPasswordRespository.forgotPasswordAPI(forgotPasswordRequest)
    }


}