package com.rental.login.model.repositry.api

import com.rental.customer.forgotpassword.model.modelClass.OTPRequest
import com.rental.customer.forgotpassword.model.modelClass.OTPResponse
import com.rental.login.model.modelclass.*
import com.rental.webservice.PathURL
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {
    /*
    * Login API
    * */
    @POST(PathURL.LOGIN)
    fun login(@Body loginReqModel: LoginUserReqModel): Call<LoginUserResModel>

    /*Register user*/
    @POST(PathURL.REGISTER)
    fun register(@Body registerRequest: RegisterUserReqModel): Call<RegisterUserResModel>

    @POST(PathURL.FORGOT_PASSWORD)
    fun forgotPassword(@Body forgotPasswordRequest: ForgotPasswordRequest): Call<ForgotPasswordResponse>

    @POST(PathURL.RESEND_OTP)
    fun otp(@Body otpRequest: OTPRequest): Call<OTPResponse>
}