package com.eazyrento.merchant.model.repository

import androidx.lifecycle.LiveData
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.appbiz.retrofitapi.GenericRequestHandler
import com.eazyrento.common.model.modelclass.ProductCategoriesResModel
import com.eazyrento.common.model.modelclass.BookingDashboardResModel
import com.eazyrento.customer.myaddress.model.modelclass.AddressCreateReqModelItem
import com.eazyrento.customer.myaddress.model.repostory.api.AddressApi
import com.eazyrento.merchant.model.modelclass.MerchantAddProductReqModel
import com.eazyrento.merchant.model.modelclass.MerchantFeedbackReqModel
import com.eazyrento.merchant.model.modelclass.MerchantNotifyAdminReqModelItem
import com.eazyrento.merchant.model.modelclass.MerchantProductDetailsResModel
import com.eazyrento.merchant.model.repository.api.MerchantAPI
import com.eazyrento.webservice.ServiceGenrator
import com.google.gson.JsonElement


class MerchantProductCategoriesRepo : GenericRequestHandler<JsonElement>(){

    fun getProductCateg( ): LiveData<DataWrapper<JsonElement>> {
        val call = ServiceGenrator.client.create(
            MerchantAPI::class.java).getProductCategory()
        return doRequest(call)
    }
}

class MerchantAddProductRepo : GenericRequestHandler<JsonElement>(){

    fun addMerchantProduct(merchantAddProductReqModel: MerchantAddProductReqModel,boolean_add: Boolean,id:Int): LiveData<DataWrapper<JsonElement>> {

        if (boolean_add) {
            return doRequest( ServiceGenrator.client.create(
                MerchantAPI::class.java
            ).addProduct(merchantAddProductReqModel))
        }else {
            return doRequest(ServiceGenrator.client.create(
                MerchantAPI::class.java
            ).updateProductDetails(id,merchantAddProductReqModel))
        }

    }
}

class MerchantDeleteProductRepo : GenericRequestHandler<JsonElement>(){

    fun deleteProduct(id: Int): LiveData<DataWrapper<JsonElement>> {
        val call = ServiceGenrator.client.create(
            MerchantAPI::class.java).deleteProduct(id)
        return doRequest(call)
    }
}

class MerchantProductDetailsRepo : GenericRequestHandler<MerchantProductDetailsResModel>(){

    fun detailsProduct(id: Int): LiveData<DataWrapper<MerchantProductDetailsResModel>> {
        val call = ServiceGenrator.client.create(
            MerchantAPI::class.java).getProductDetails(id)
        return doRequest(call)
    }
}

class MerchantNotifyAdminRepo :
    GenericRequestHandler<JsonElement>(){

    fun notifyAdmin(merchantNotifyAdminReqModelItem: MerchantNotifyAdminReqModelItem): LiveData<DataWrapper<JsonElement>> {

        val call = ServiceGenrator.client.create(
            MerchantAPI::class.java).notifyAdmin(merchantNotifyAdminReqModelItem)
        return doRequest(call)
    }

}

class MerchantFeedbackRepo :
    GenericRequestHandler<JsonElement>(){

    fun merchantFeedback(merchantFeedbackReqmodel: MerchantFeedbackReqModel): LiveData<DataWrapper<JsonElement>> {

        val call = ServiceGenrator.client.create(
            MerchantAPI::class.java).merchantFeedback(merchantFeedbackReqmodel)
        return doRequest(call)
    }

}


/*class MerchantProductCategories :GenericRequestHandler<ProductCategoriesResModel>(){

    fun getBookingOrdersList(value: Int): LiveData<DataWrapper<ProductCategoriesResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(MerchantAPI::class.java).getProductCategory()
        return doRequest(call)
    }

}*/

/*class CustomerOrderDetailsRepo :GenericRequestHandler<CustomerOrderDetailsResModel>(){

    fun getCustomerOrderDetail(value: Int): LiveData<DataWrapper<CustomerOrderDetailsResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(MerchantAPI::class.java).getCustomerOrderDetail(value)
        return doRequest(call)
    }

}*/


