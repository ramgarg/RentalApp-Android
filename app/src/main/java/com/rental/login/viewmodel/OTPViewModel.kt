package com.rental.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rental.customer.forgotpassword.model.modelClass.OTPRequest
import com.rental.customer.forgotpassword.model.modelClass.OTPResponse
import com.rental.login.model.repositry.OTPRepository

class OTPViewModel :ViewModel() {

    var otpRepository: OTPRepository
    private var responseMutableLiveData: MutableLiveData<OTPResponse>? = null
    init {
        otpRepository= OTPRepository()
    }

    fun getOTPResponse(): MutableLiveData<OTPResponse>? {
        if (responseMutableLiveData == null) {
            responseMutableLiveData = MutableLiveData<OTPResponse>()
        }
        return responseMutableLiveData
    }

    fun otpAPI(email:String){
        val otpRequest = OTPRequest(email)
        otpRepository.otpVerifyAPI(otpRequest)
    }
}