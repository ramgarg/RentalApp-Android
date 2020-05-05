package com.eazyrento.merchant.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.common.model.modelclass.ProductCategoriesResModel
import com.eazyrento.customer.myaddress.model.modelclass.AddressCreateReqModelItem
import com.eazyrento.customer.myaddress.model.repostory.AddressCreateRepo
import com.eazyrento.merchant.model.modelclass.MerchantAddProductReqModel
import com.eazyrento.merchant.model.modelclass.MerchantFeedbackReqModel
import com.eazyrento.merchant.model.modelclass.MerchantNotifyAdminReqModelItem
import com.eazyrento.merchant.model.modelclass.MerchantProductDetailsResModel
import com.eazyrento.merchant.model.repository.*
import com.google.gson.JsonElement


class MerchantProductCategoriesViewModel:ViewModel() {
    fun getProductCateg(): LiveData<DataWrapper<JsonElement>> {
        return MerchantProductCategoriesRepo()
            .getProductCateg()
    }
}
    class MerchantAddProductViewModel:ViewModel(){
        fun addProductAPI(merchantAddProductReqModel: MerchantAddProductReqModel,boolean_add: Boolean,id: Int): LiveData<DataWrapper<JsonElement>> {
            return MerchantAddProductRepo()
                .addMerchantProduct(merchantAddProductReqModel,boolean_add,id)
        }

}

class MerchantDeleteProductViewModel:ViewModel(){
    fun deletePoductAPI(id:Int): LiveData<DataWrapper<JsonElement>> {
        return MerchantDeleteProductRepo()
            .deleteProduct(id)
    }

}

class MerchantProductDetailViewModel:ViewModel(){
    fun detailsPoductAPI(id:Int): LiveData<DataWrapper<MerchantProductDetailsResModel>> {
        return MerchantProductDetailsRepo()
            .detailsProduct(id)
    }

}

class MerchantNotifyAdminViewModel:ViewModel(){
    fun notifyAdmin(merchantNotifyAdminReqModelItem: MerchantNotifyAdminReqModelItem): LiveData<DataWrapper<JsonElement>> {
        return MerchantNotifyAdminRepo()
            .notifyAdmin(merchantNotifyAdminReqModelItem)
    }

}

class MerchantFeedbackViewModel:ViewModel(){
    fun merchantFeedback(merchantFeedbackReqModel: MerchantFeedbackReqModel): LiveData<DataWrapper<JsonElement>> {
        return MerchantFeedbackRepo()
            .merchantFeedback(merchantFeedbackReqModel)
    }

}