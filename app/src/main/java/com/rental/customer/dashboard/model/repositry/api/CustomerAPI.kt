package com.rental.customer.dashboard.model.repositry.api

import com.google.gson.JsonElement
import com.rental.customer.dashboard.model.modelclass.CustomerCreateBookingReqModel
import com.rental.customer.dashboard.model.modelclass.CustomerOrderDetailsResModel
import com.rental.customer.dashboard.model.modelclass.CustomerOrderListResModel
import com.rental.customer.dashboard.model.modelclass.CustomerWishListResModel
import com.rental.login.model.modelclass.LoginRequest
import com.rental.webservice.PathURL
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CustomerAPI {
    // Customer API
    /*
    * create booking
    * */
    @POST(PathURL.CreateBooking)
    fun createBooking(@Body customerCreateBookingReqModel: CustomerCreateBookingReqModel): Call<JsonElement>

    /*
       * Add to wish list
       * */
    @POST(PathURL.AddToWishlist)
    fun addToWishList(loginReqModel: LoginRequest): Call<LoginRequest>

    /*
       *Get wish list
       * */
    @GET(PathURL.GetWishlist)
    fun getWishList(): Call<CustomerWishListResModel>

    /*
       *Delete wish list
       * */
    @POST(PathURL.DeleteWishlist)
    fun deleteWishList(loginReqModel: LoginRequest): Call<LoginRequest>

    /*
      *customer orders
      * */
    @GET(PathURL.CustomnerOrders)
    fun getCustomerOrdersList(@Path ("value") value:Int): Call<CustomerOrderListResModel>

    /*
      *customer orders details
      * */
    @GET(PathURL.CustomnerOrderDetail)
    fun getCustomerOrderDetail(@Path("id") id: Int): Call<CustomerOrderDetailsResModel>

    /*
      *customer product un available
      * */
    @POST(PathURL.CustomerProductUnavailable)
    fun customerProductUnAvailable(loginReqModel: LoginRequest): Call<LoginRequest>

    /*
      *Make payment
      * */
    @POST(PathURL.MakePayment)
    fun makePayment(loginReqModel: LoginRequest): Call<LoginRequest>

    /*
      *Make payment
      * */
    @POST(PathURL.PaymentList)
    fun paymentList(loginReqModel: LoginRequest): Call<LoginRequest>

    /*
      *Customer feedback
      * */
    @POST(PathURL.CustomerFeedback)
    fun customerFeedback(loginReqModel: LoginRequest): Call<LoginRequest>
}