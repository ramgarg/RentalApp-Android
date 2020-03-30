package com.rental.webservice

import com.rental.common.model.modelclass.OrderListing
import com.rental.customer.dashboard.model.modelclass.HomeResponse
import com.rental.customer.dashboard.model.modelclass.OrderSummaryResponseModel
import com.rental.customer.login.model.modelclass.ForgotPasswordRequest
import com.rental.customer.login.model.modelclass.ForgotPasswordResponse
import com.rental.customer.forgotpassword.model.modelClass.OTPRequest
import com.rental.customer.forgotpassword.model.modelClass.OTPResponse
import com.rental.customer.login.model.modelclass.LoginRequest
import com.rental.customer.notification.model.NotificationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface APIServices {

    @POST(Constant.LOGIN)
    fun login(loginRequest: LoginRequest):Call<LoginRequest>

    @POST(Constant.FORGOTPASSOWRD)
    fun forgotPassword(forgotPasswordRequest: ForgotPasswordRequest):Call<ForgotPasswordResponse>

    @POST(Constant.SENDOTP)
    fun otp(otpRequest: OTPRequest):Call<OTPResponse>

    @GET(Constant.DASHBOARD)
    fun getData():Call<HomeResponse>

    @GET(Constant.DASHBOARD)
    fun getOrderSummary():Call<OrderSummaryResponseModel>


    @GET(Constant.NOTIFICATION)
    fun getNotification():Call<NotificationResponse>

    //
    @GET(Constant.ORDER_LISTING)
    fun getOrderListing():Call<OrderListing>
}