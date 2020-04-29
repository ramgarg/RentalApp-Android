package com.eazyrento.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.customer.forgotpassword.model.modelClass.OTPRequest
import com.eazyrento.customer.forgotpassword.model.modelClass.OTPResponse
import com.eazyrento.login.model.modelclass.LoginUserReqModel
import com.eazyrento.login.model.modelclass.LoginUserResModel
import com.eazyrento.login.model.repositry.LoginOTPRepo
import com.eazyrento.login.model.repositry.LoginUserRepo


class LoginUserViewModel :ViewModel() {

    fun loginUser(LoginUserReqModel: LoginUserReqModel): LiveData<DataWrapper<LoginUserResModel>> {
        return LoginUserRepo().loginAPI(LoginUserReqModel)
    }
}

class LoginOTPViewModel :ViewModel() {

    fun OTPAPI(otp: OTPRequest): LiveData<DataWrapper<OTPResponse>> {
        return LoginOTPRepo().OTP_API(otp)
    }
}

