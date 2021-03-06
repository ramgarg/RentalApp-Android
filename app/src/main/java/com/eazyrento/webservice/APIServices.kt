package com.eazyrento.webservice

import com.eazyrento.customer.dashboard.model.modelclass.HomeResponse
import com.eazyrento.customer.dashboard.model.modelclass.OrderSummaryResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface APIServices {

    /*will be coomenting*/
    @GET(PathURL.DASHBOARD)
    fun getData():Call<HomeResponse>

    @GET(PathURL.DASHBOARD)
    fun getOrderSummary():Call<OrderSummaryResponseModel>
//



    /*// Customer API
    *//*
    * create booking
    * *//*
    @POST(PathURL.CreateBooking)
    fun createBooking(loginReqModel: LoginRequest): Call<LoginRequest>

    *//*
       * Add to wish list
       * *//*
    @POST(PathURL.AddToWishlist)
    fun addToWishList(loginReqModel: LoginRequest): Call<LoginRequest>

    *//*
       *Get wish list
       * *//*
    @POST(PathURL.GetWishlist)
    fun getWishList(loginReqModel: LoginRequest): Call<LoginRequest>

    *//*
       *Delete wish list
       * *//*
    @POST(PathURL.DeleteWishlist)
    fun deleteWishList(loginReqModel: LoginRequest): Call<LoginRequest>

    *//*
      *customer orders
      * *//*
    @POST(PathURL.CustomnerOrders)
    fun customerOrders(loginReqModel: LoginRequest): Call<LoginRequest>

    *//*
      *customer orders details
      * *//*
    @POST(PathURL.CustomnerOrderDetail)
    fun customerOrderDetail(loginReqModel: LoginRequest): Call<LoginRequest>

    *//*
      *customer product un available
      * *//*
    @POST(PathURL.CustomerProductUnavailable)
    fun customerProductUnAvailable(loginReqModel: LoginRequest): Call<LoginRequest>

    *//*
      *Make payment
      * *//*
    @POST(PathURL.MakePayment)
    fun makePayment(loginReqModel: LoginRequest): Call<LoginRequest>

    *//*
      *Make payment
      * *//*
    @POST(PathURL.PaymentList)
    fun paymentList(loginReqModel: LoginRequest): Call<LoginRequest>

    *//*
      *Customer feedback
      * *//*
    @POST(PathURL.CustomerFeedback)
    fun customerFeedback(loginReqModel: LoginRequest): Call<LoginRequest>*/


    // agent order lsiting
    /*@GET(PathURL.ORDER_LISTING)
    fun getOrderListing(): Call<OrderListing>*/
}