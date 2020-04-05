package com.rental.login.model.repositry.api

import com.rental.customer.forgotpassword.model.modelClass.OTPRequest
import com.rental.customer.forgotpassword.model.modelClass.OTPResponse
import com.rental.login.model.modelclass.*
import com.rental.webservice.Constant
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {
    /*
    * Login API
    * */
    @POST(Constant.LOGIN)
    fun login(loginRequest: LoginUserRequest): Call<LoginUserRequest>

    /*Register user*/
    @POST(Constant.REGISTER)
    fun register(@Body registerRequest: RegisterUserReqModel): Call<RegisterUserResModel>

    @POST(Constant.FORGOT_PASSWORD)
    fun forgotPassword(forgotPasswordRequest: ForgotPasswordRequest): Call<ForgotPasswordResponse>

    @POST(Constant.RESEND_OTP)
    fun otp(otpRequest: OTPRequest): Call<OTPResponse>
}