package com.eazyrento.merchant.model.repository.api

import com.eazyrento.common.model.modelclass.AcceptanceDeclineReqModel
import com.eazyrento.common.model.modelclass.ProductDetailsResModel
import com.eazyrento.common.model.modelclass.ProductSubCategoriesResModel
import com.eazyrento.common.model.modelclass.BookingDashboardResModel
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderDetailsResModel
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderListResModel
import com.eazyrento.merchant.model.modelclass.MerchantAddProductReqModel
import com.eazyrento.merchant.model.modelclass.MerchantProductDetailsResModel
import com.eazyrento.webservice.PathURL
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.*

interface MerchantAPI {

    // Dashboard
    @GET(PathURL.MerchantDashboard)
    fun getMerchantDashboradData(): Call<BookingDashboardResModel>

    // categories list by category name
    @GET(PathURL.MerchantProductCategory)
    fun getProductCategory(): Call<JsonElement>

    // Add product name
    @POST(PathURL.MerchantAddProduct)
    fun addProduct(@Body merchantAddProductReqModel: MerchantAddProductReqModel): Call<JsonElement>

    @PUT(PathURL.MerchantUpdateProduct)
    fun updateProductDetails(@Path("id") id: Int,@Body merchantAddProductReqModel: MerchantAddProductReqModel): Call<JsonElement>

    // delete product
    @DELETE(PathURL.MerchantDeleteProduct)
    fun deleteProduct(@Path("id") id: Int): Call<JsonElement>

    // get product details
    @GET(PathURL.MerchantProductDetail)
    fun getProductDetails(@Path("id") id: Int): Call<MerchantProductDetailsResModel>

    //acceptnace delete
    @POST(PathURL.MERCHANT_ACCEPTANCE_DECLINE)
    fun acceptanceDelete(@Body acceptanceDeclineReqModel: AcceptanceDeclineReqModel): Call<JsonElement>

    @GET(PathURL.MerchantOrders)
    fun getCustomerOrdersList(@Path ("value") value:Int): Call<CustomerOrderListResModel>

    /*
     *customer orders details
     * */
    @GET(PathURL.MerchantOrderDetail)
    fun getCustomerOrderDetail(@Path("id") id: Int): Call<CustomerOrderDetailsResModel>


}