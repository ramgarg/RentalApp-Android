package com.rental.webservice

import com.rental.common.model.modelclass.OrderListing
import com.rental.customer.dashboard.model.modelclass.HomeResponse
import com.rental.customer.dashboard.model.modelclass.OrderSummaryResponseModel
import com.rental.login.model.modelclass.ForgotPasswordRequest
import com.rental.login.model.modelclass.ForgotPasswordResponse
import com.rental.customer.forgotpassword.model.modelClass.OTPRequest
import com.rental.customer.forgotpassword.model.modelClass.OTPResponse
import com.rental.login.model.modelclass.LoginRequest
import com.rental.customer.notification.model.NotificationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface APIServices {

    @POST(Constant.LOGIN)
    fun login(loginRequest: LoginRequest): Call<LoginRequest>

    @POST(Constant.FORGOTPASSOWRD)
    fun forgotPassword(forgotPasswordRequest: ForgotPasswordRequest): Call<ForgotPasswordResponse>

    @POST(Constant.SENDOTP)
    fun otp(otpRequest: OTPRequest): Call<OTPResponse>

    @GET(Constant.DASHBOARD)
    fun getData():Call<HomeResponse>

    @GET(Constant.DASHBOARD)
    fun getOrderSummary():Call<OrderSummaryResponseModel>


    @GET(Constant.NOTIFICATION)
    fun getNotification(): Call<NotificationResponse>

    // Common API

    // Master categories
    @GET(Constant.MasterCategory)
    fun getMasterCategory(): Call<NotificationResponse>

    // categories list by category name
    @GET(Constant.Product)
    fun getProductCategory(@Url url: String): Call<LoginRequest>

    // sub categories lsit by product name
    @GET(Constant.SubCategory)
    fun getSubCategory(@Url url: String): Call<LoginRequest>

    // sub categories lsit by product name
    @GET(Constant.ProductDetail)
    fun getProductDetails(@Url url: String): Call<LoginRequest>

    // Customer API
    /*
    * create booking
    * */
    @POST(Constant.CreateBooking)
    fun createBooking(loginRequest: LoginRequest): Call<LoginRequest>

    /*
       * Add to wish list
       * */
    @POST(Constant.AddToWishlist)
    fun addToWishList(loginRequest: LoginRequest): Call<LoginRequest>

    /*
       *Get wish list
       * */
    @POST(Constant.GetWishlist)
    fun getWishList(loginRequest: LoginRequest): Call<LoginRequest>

    /*
       *Delete wish list
       * */
    @POST(Constant.DeleteWishlist)
    fun deleteWishList(loginRequest: LoginRequest): Call<LoginRequest>

    /*
      *customer orders
      * */
    @POST(Constant.CustomnerOrders)
    fun customerOrders(loginRequest: LoginRequest): Call<LoginRequest>

    /*
      *customer orders details
      * */
    @POST(Constant.CustomnerOrderDetail)
    fun customerOrderDetail(loginRequest: LoginRequest): Call<LoginRequest>

    /*
      *customer product un available
      * */
    @POST(Constant.CustomerProductUNavailable)
    fun customerProductUnAvailable(loginRequest: LoginRequest): Call<LoginRequest>

    /*
      *Make payment
      * */
    @POST(Constant.MakePayment)
    fun makePayment(loginRequest: LoginRequest): Call<LoginRequest>

    /*
      *Make payment
      * */
    @POST(Constant.PaymentList)
    fun paymentList(loginRequest: LoginRequest): Call<LoginRequest>

    /*
      *Customer feedback
      * */
    @POST(Constant.CustomerFeedback)
    fun customerFeedback(loginRequest: LoginRequest): Call<LoginRequest>


    // agent order lsiting
    @GET(Constant.ORDER_LISTING)
    fun getOrderListing(): Call<OrderListing>
}