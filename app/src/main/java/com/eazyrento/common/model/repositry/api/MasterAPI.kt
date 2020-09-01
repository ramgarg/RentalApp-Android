package com.eazyrento.common.model.repositry.api

import com.eazyrento.common.model.modelclass.*
import com.eazyrento.login.model.modelclass.LoginRequest
import com.eazyrento.webservice.PathURL
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface MasterAPI{
    // Common API

    // Master categories
    @GET(PathURL.MasterCategory)
    fun getMasterCategory(): Call<MasterResModel>

    // categories list by category name
    //ProductCategoriesResModel

    @GET(PathURL.ProductCategory)
    fun getProductCategory(@Path("master_name") master_name: String): Call<JsonElement>

    // sub categories lsit by product name
    @GET(PathURL.ProductSubCategory)
    fun getProductSubCategory(@Path("category_name") cat_name: String): Call<ProductSubCategoriesResModel>

    //product name
    // sub categories lsit by product name
    @GET(PathURL.Product)
    fun getProductListBySubCat(@Path("sub_category_name") cat_name: String): Call<ProductListBySubCategoriesResModel>

    // sub categories lsit by product name
    @GET(PathURL.ProductDetail)
    fun getProductDetails(@Path("id") id: Int): Call<JsonElement>


    @POST(PathURL.LANGUAGE_CHANGE)
    fun langaugeChange(@Body langaugeChangeReqModel: LangaugeChangeReqModel): Call<JsonElement>




}