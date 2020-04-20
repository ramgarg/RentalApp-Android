package com.rental.merchant.model.repository.api

import com.rental.common.model.modelclass.ProductCategoriesResModel
import com.rental.common.model.modelclass.ProductDetailsResModel
import com.rental.common.model.modelclass.ProductSubCategoriesResModel
import com.rental.common.model.modelclass.MerchantDashboardResModel
import com.rental.webservice.PathURL
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MerchantAPI {

    @GET(PathURL.MerchantDashboard)
    fun getMerchantDashboradData(): Call<MerchantDashboardResModel>

    // categories list by category name
    @GET(PathURL.MerchantProductCategory)
    fun getProductCategory(): Call<ProductCategoriesResModel>

    // sub categories lsit by product name
    @GET(PathURL.ProductSubCategory)
    fun getProductSubCategory(@Path("category_name") cat_name: String): Call<ProductSubCategoriesResModel>

    // sub categories lsit by product name
    @GET(PathURL.ProductDetail)
    fun getProductDetails(@Path("id") id: Int): Call<ProductDetailsResModel>
}