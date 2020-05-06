package com.eazyrento.login.model.repositry.api

import com.eazyrento.login.model.modelclass.*
import com.google.gson.JsonElement
import com.eazyrento.customer.forgotpassword.model.modelClass.OTPRequest
import com.eazyrento.customer.forgotpassword.model.modelClass.OTPResponse
import com.eazyrento.webservice.PathURL
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

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
    fun forgotPassword(@Body forgotPasswordRequest: ForgotPasswordRequest): Call<JsonElement>

    @POST(PathURL.VERIFY_PASSCODE)
    fun otp(@Body otpRequest: OTPRequest): Call<OTPResponse>

    @POST(PathURL.RESEND_OTP)
    fun resendOTP(@Body otpRequest: OTPRequest): Call<OTPResponse>

    /*
    * get user profile
    * */
    @GET(PathURL.USER_PROFILE)
    fun getUserProfile(): Call<ProfileModelReqRes>

    /*
    * update user profile
    * */
    @PUT(PathURL.USER_PROFILE)
    fun updateUserProfile(@Body profileModelReqRes: UserProfile): Call<JsonElement>

}