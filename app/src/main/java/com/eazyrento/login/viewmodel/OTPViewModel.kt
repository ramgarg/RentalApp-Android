package com.eazyrento.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.customer.forgotpassword.model.modelClass.OTPRequest
import com.eazyrento.customer.forgotpassword.model.modelClass.OTPResponse
import com.eazyrento.login.model.repositry.OTPRepository

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

    fun otpAPI(user_id:Int, passcode:Int){
        val otpRequest = OTPRequest(user_id,passcode)
        otpRepository.otpVerifyAPI(otpRequest)
    }
}