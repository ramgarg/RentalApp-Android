package com.eazyrento.merchant.model.repository.api

import com.eazyrento.common.model.modelclass.ProductCategoriesResModel
import com.eazyrento.common.model.modelclass.ProductDetailsResModel
import com.eazyrento.common.model.modelclass.ProductSubCategoriesResModel
import com.eazyrento.common.model.modelclass.BookingDashboardResModel
import com.eazyrento.webservice.PathURL
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MerchantAPI {

    @GET(PathURL.MerchantDashboard)
    fun getMerchantDashboradData(): Call<BookingDashboardResModel>

    // categories list by category name
    @GET(PathURL.MerchantProductCategory)
    fun getProductCategory(): Call<JsonElement>

    // sub categories lsit by product name
    @GET(PathURL.ProductSubCategory)
    fun getProductSubCategory(@Path("category_name") cat_name: String): Call<ProductSubCategoriesResModel>

    // sub categories lsit by product name
    @GET(PathURL.ProductDetail)
    fun getProductDetails(@Path("id") id: Int): Call<ProductDetailsResModel>
}