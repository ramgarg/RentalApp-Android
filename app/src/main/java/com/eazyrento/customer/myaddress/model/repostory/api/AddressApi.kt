package com.eazyrento.customer.myaddress.model.repostory.api

import com.eazyrento.agent.model.modelclass.AgentAddNoteReqModel
import com.eazyrento.customer.myaddress.model.modelclass.AddressCreateReqModel
import com.eazyrento.customer.myaddress.model.modelclass.AddressCreateReqModelItem
import com.eazyrento.customer.myaddress.model.modelclass.AddressDetailsResModel
import com.eazyrento.customer.myaddress.model.modelclass.AddressListResModel
import com.eazyrento.merchant.model.modelclass.MerchantAddProductReqModel
import com.eazyrento.merchant.model.modelclass.MerchantProductDetailsResModel
import com.eazyrento.webservice.PathURL
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.*

interface AddressApi {

    @GET(PathURL.ADDRESS_LIST)
    fun getAddressList(): Call<AddressListResModel>

    @POST(PathURL.ADDRESS_CREATE)
    fun createAddress(@Body addressCreateReqModel: AddressCreateReqModel): Call<JsonElement>

    @PUT(PathURL.ADDRESS_UPDATE)
    fun updateAddress(@Path("id") id: Int, @Body addressCreateReqModelItem: AddressCreateReqModelItem): Call<JsonElement>

    @DELETE(PathURL.ADDRESS_DELETE)
    fun deleteAddress(@Path("id") id: Int): Call<JsonElement>

    @GET(PathURL.ADDRESS_DETAIL)
    fun getAddressDetails(@Path("id") id: Int): Call<AddressDetailsResModel>
}