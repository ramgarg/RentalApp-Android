package com.rental.webservice

import com.rental.common.model.modelclass.OrderListing
import com.rental.customer.dashboard.model.modelclass.HomeResponse
import com.rental.customer.dashboard.model.modelclass.OrderSummaryResponseModel
import com.rental.customer.forgotpassword.model.modelClass.OTPRequest
import com.rental.customer.forgotpassword.model.modelClass.OTPResponse
import com.rental.customer.notification.model.NotificationResponse
import com.rental.login.model.modelclass.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface APIServices {

    /*will be coomenting*/
    @GET(Constant.DASHBOARD)
    fun getData():Call<HomeResponse>

    @GET(Constant.DASHBOARD)
    fun getOrderSummary():Call<OrderSummaryResponseModel>
//

    @GET(Constant.NOTIFICATION)
    fun getNotification(): Call<NotificationResponse>

    // Common API

    // Master categories
    @GET(Constant.MasterCategory)
    fun getMasterCategory(): Call<NotificationResponse>

    // categories list by category name
    @GET(Constant.Product)
    fun getProductCategory(@Url url: String): Call<LoginUserRequest>

    // sub categories lsit by product name
    @GET(Constant.SubCategory)
    fun getSubCategory(@Url url: String): Call<LoginUserRequest>

    // sub categories lsit by product name
    @GET(Constant.ProductDetail)
    fun getProductDetails(@Url url: String): Call<LoginUserRequest>

    // Customer API
    /*
    * create booking
    * */
    @POST(Constant.CreateBooking)
    fun createBooking(loginRequest: LoginUserRequest): Call<LoginUserRequest>

    /*
       * Add to wish list
       * */
    @POST(Constant.AddToWishlist)
    fun addToWishList(loginRequest: LoginUserRequest): Call<LoginUserRequest>

    /*
       *Get wish list
       * */
    @POST(Constant.GetWishlist)
    fun getWishList(loginRequest: LoginUserRequest): Call<LoginUserRequest>

    /*
       *Delete wish list
       * */
    @POST(Constant.DeleteWishlist)
    fun deleteWishList(loginRequest: LoginUserRequest): Call<LoginUserRequest>

    /*
      *customer orders
      * */
    @POST(Constant.CustomnerOrders)
    fun customerOrders(loginRequest: LoginUserRequest): Call<LoginUserRequest>

    /*
      *customer orders details
      * */
    @POST(Constant.CustomnerOrderDetail)
    fun customerOrderDetail(loginRequest: LoginUserRequest): Call<LoginUserRequest>

    /*
      *customer product un available
      * */
    @POST(Constant.CustomerProductUNavailable)
    fun customerProductUnAvailable(loginRequest: LoginUserRequest): Call<LoginUserRequest>

    /*
      *Make payment
      * */
    @POST(Constant.MakePayment)
    fun makePayment(loginRequest: LoginUserRequest): Call<LoginUserRequest>

    /*
      *Make payment
      * */
    @POST(Constant.PaymentList)
    fun paymentList(loginRequest: LoginUserRequest): Call<LoginUserRequest>

    /*
      *Customer feedback
      * */
    @POST(Constant.CustomerFeedback)
    fun customerFeedback(loginRequest: LoginUserRequest): Call<LoginUserRequest>


    // agent order lsiting
    @GET(Constant.ORDER_LISTING)
    fun getOrderListing(): Call<OrderListing>
}