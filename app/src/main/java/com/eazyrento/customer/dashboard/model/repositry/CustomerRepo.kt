package com.eazyrento.customer.dashboard.model.repositry

import androidx.lifecycle.LiveData
import com.eazyrento.EazyRantoApplication
import com.eazyrento.Session
import com.eazyrento.agent.model.repositry.api.AgentAPI
import com.google.gson.JsonElement
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.appbiz.retrofitapi.GenericRequestHandler
import com.eazyrento.common.model.modelclass.ApplyPromoCodeReqModel
import com.eazyrento.common.model.modelclass.ApplyPromoCodeResModel
import com.eazyrento.common.model.modelclass.ProductID
import com.eazyrento.common.model.repositry.api.MasterAPI
import com.eazyrento.common.view.UserInfoAPP
import com.eazyrento.customer.dashboard.model.modelclass.*
import com.eazyrento.customer.dashboard.model.repositry.api.CustomerAPI
import com.eazyrento.merchant.model.repository.api.MerchantAPI
import com.eazyrento.webservice.ServiceGenrator
import retrofit2.Call

class CustomerCreateBookingRepo :
    GenericRequestHandler<JsonElement>(){

    fun createBooking(customerCreateBookingReqModel: CustomerCreateBookingReqModel): LiveData<DataWrapper<JsonElement>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            CustomerAPI::class.java).createBooking(customerCreateBookingReqModel)
        return doRequest(call)
    }


}

class ApplyPromoCodeRepo :
    GenericRequestHandler<ApplyPromoCodeResModel>(){

    fun applyPromo(applyPromoCodeReqModel: ApplyPromoCodeReqModel): LiveData<DataWrapper<ApplyPromoCodeResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            CustomerAPI::class.java).applyPromo(applyPromoCodeReqModel)
        return doRequest(call)
    }


}

class CustomerOrderBookingOrderListRepo :
    GenericRequestHandler<CustomerOrderListResModel>(){
    lateinit var call: Call<CustomerOrderListResModel>

    fun getBookingOrdersList(value: Int): LiveData<DataWrapper<CustomerOrderListResModel>> {
        when{
            Session.getInstance(EazyRantoApplication.context)?.getUserRole().equals(UserInfoAPP.CUSTOMER)-> {
                val classType = CustomerAPI::class.java
                 call = ServiceGenrator.client.create(
                    classType).getCustomerOrdersList(value)

            }
            Session.getInstance(EazyRantoApplication.context)?.getUserRole().equals(UserInfoAPP.AGENT)-> {
                val classType = AgentAPI::class.java
                call = ServiceGenrator.client.create(
                    classType).getCustomerOrdersList(value)
            }
            Session.getInstance(EazyRantoApplication.context)?.getUserRole().equals(UserInfoAPP.MERCHANT)-> {
                val classType = MerchantAPI::class.java
                call = ServiceGenrator.client.create(
                    classType).getCustomerOrdersList(value)
            }
        }

          return doRequest(call)
        }


}

class CustomerOrderDetailsRepo :
    GenericRequestHandler<OrderDetailsResModel>(){

    fun getCustomerOrderDetail(value: Int): LiveData<DataWrapper<OrderDetailsResModel>> {

        lateinit var call: Call<OrderDetailsResModel>

        when{
            Session.getInstance(EazyRantoApplication.context)?.getUserRole().equals(UserInfoAPP.CUSTOMER)-> {
                val classType = CustomerAPI::class.java
                call = ServiceGenrator.client.create(
                    classType).getCustomerOrderDetail(value)

            }
            Session.getInstance(EazyRantoApplication.context)?.getUserRole().equals(UserInfoAPP.AGENT)-> {
                val classType = AgentAPI::class.java
                call = ServiceGenrator.client.create(
                    classType).getCustomerOrderDetail(value)
            }
            Session.getInstance(EazyRantoApplication.context)?.getUserRole().equals(UserInfoAPP.MERCHANT)-> {
                val classType = MerchantAPI::class.java
                call = ServiceGenrator.client.create(
                    classType).getCustomerOrderDetail(value)
            }
        }


        /*val call = ServiceGenrator.client.create(
            CustomerAPI::class.java).getCustomerOrderDetail(value)*/
        return doRequest(call)
    }

}

//getCustomerOrderTracking

class CustomerOrderTrackingRepo :
    GenericRequestHandler<OrderTrackingList>() {

    fun getCustomerOrderTracking(orderID: String,subOrderID:Int): LiveData<DataWrapper<OrderTrackingList>> {

        lateinit var call: Call<OrderTrackingList>


                val classType = CustomerAPI::class.java
                call = ServiceGenrator.client.create(
                    classType).getCustomerOrderTracking(orderID)

        return doRequest(call)
        }

}

class CustomerWishListRepo :
    GenericRequestHandler<CustomerWishListResModel>(){

    fun getCustomerWishList(): LiveData<DataWrapper<CustomerWishListResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            CustomerAPI::class.java).getWishList()
        return doRequest(call)
    }

}

class CustomerAddWishRepo :
    GenericRequestHandler<JsonElement>(){

    fun customerWishAdd(productID: ProductID): LiveData<DataWrapper<JsonElement>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            CustomerAPI::class.java).addToWishList(productID)
        return doRequest(call)
    }

}

class CustomerWishDeleteRepo :
    GenericRequestHandler<JsonElement>(){

    fun customerWishDelete(id:Int): LiveData<DataWrapper<JsonElement>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            CustomerAPI::class.java).deleteWishList(id)
        return doRequest(call)
    }

}

class CustomerFeedbackRepo :
    GenericRequestHandler<JsonElement>(){

    fun customerFeedback(customerFeedbackRequestmodel: CustomerFeedbackRequestModel): LiveData<DataWrapper<JsonElement>> {

        val call = ServiceGenrator.client.create(
            CustomerAPI::class.java).customerFeedback(customerFeedbackRequestmodel)
        return doRequest(call)
    }

}

class NotifyAdminProdUnavailableRepo :
    GenericRequestHandler<JsonElement>(){

    fun notifyAdmin(notifyAdminProductUnavailble: NotifyAdminProductUnavailble): LiveData<DataWrapper<JsonElement>> {

        val call = ServiceGenrator.client.create(
            CustomerAPI::class.java).notifyAdminProdUnavail(notifyAdminProductUnavailble)
        return doRequest(call)
    }

}


