package com.rental.common.model.repositry.api

import com.rental.common.model.modelclass.MasterResModel
import com.rental.common.model.modelclass.ProductCategoriesResModel
import com.rental.common.model.modelclass.ProductDetailsResModel
import com.rental.common.model.modelclass.ProductSubCategoriesResModel
import com.rental.login.model.modelclass.LoginRequest
import com.rental.webservice.PathURL
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface MasterAPI{
    // Common API

    // Master categories
    @GET(PathURL.MasterCategory)
    fun getMasterCategory(): Call<MasterResModel>

    // categories list by category name
    @GET(PathURL.ProductCategory)
    fun getProductCategory(@Path("master_name") master_name: String): Call<ProductCategoriesResModel>

    // sub categories lsit by product name
    @GET(PathURL.ProductSubCategory)
    fun getProductSubCategory(@Path("category_name") cat_name: String): Call<ProductSubCategoriesResModel>

    // sub categories lsit by product name
    @GET(PathURL.ProductDetail)
    fun getProductDetails(@Path("id") id: Int): Call<ProductDetailsResModel>

}