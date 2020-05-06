package com.eazyrento.customer.dashboard.model.repositry.api

import com.google.gson.JsonElement
import com.eazyrento.common.model.modelclass.BookingListResModel
import com.eazyrento.common.model.modelclass.ProductID
import com.eazyrento.customer.dashboard.model.modelclass.CustomerCreateBookingReqModel
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderDetailsResModel
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderListResModel
import com.eazyrento.customer.dashboard.model.modelclass.CustomerWishListResModel
import com.eazyrento.login.model.modelclass.LoginRequest
import com.eazyrento.webservice.PathURL
import retrofit2.Call
import retrofit2.http.*

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
    fun addToWishList(@Body productID: ProductID): Call<JsonElement>

    /*
       *Get wish list
       * */
    @GET(PathURL.GetWishlist)
    fun getWishList(): Call<CustomerWishListResModel>

    /*
       *Delete wish list
       * */
    @DELETE(PathURL.DeleteWishlist)
    fun deleteWishList(@Path ("id") id:Int): Call<JsonElement>

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

    /*Customer booking list*/

    @GET(PathURL.CUSTOMER_MY_BOOKINGS)
    fun getCustomerBookingList(): Call<BookingListResModel>
}