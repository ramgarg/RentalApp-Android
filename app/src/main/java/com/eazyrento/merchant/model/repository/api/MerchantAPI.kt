package com.eazyrento.merchant.model.repository.api

import com.eazyrento.common.model.modelclass.ProductDetailsResModel
import com.eazyrento.common.model.modelclass.ProductSubCategoriesResModel
import com.eazyrento.common.model.modelclass.BookingDashboardResModel
import com.eazyrento.merchant.model.modelclass.MerchantAddProductReqModel
import com.eazyrento.webservice.PathURL
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.DELETE

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


    // delete product
    @DELETE(PathURL.MerchantDeleteProduct)
    fun deleteProduct(@Path("id") id: Int): Call<JsonElement>

    // sub categories lsit by product name
    @GET(PathURL.MerchantProductDetail)
    fun getProductDetails(@Path("id") id: Int): Call<JsonElement>
}