package com.eazyrento.merchant.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.common.model.modelclass.ProductCategoriesResModel
import com.eazyrento.merchant.model.modelclass.MerchantAddProductReqModel
import com.eazyrento.merchant.model.repository.MerchantAddProductRepo
import com.eazyrento.merchant.model.repository.MerchantDeleteProductRepo
import com.eazyrento.merchant.model.repository.MerchantProductCategoriesRepo
import com.eazyrento.merchant.model.repository.MerchantProductDetailsRepo
import com.google.gson.JsonElement


class MerchantProductCategoriesViewModel:ViewModel() {
    fun getProductCateg(): LiveData<DataWrapper<JsonElement>> {
        return MerchantProductCategoriesRepo()
            .getProductCateg()
    }
}
    class MerchantAddProductViewModel:ViewModel(){
        fun addProductAPI(merchantAddProductReqModel: MerchantAddProductReqModel): LiveData<DataWrapper<JsonElement>> {
            return MerchantAddProductRepo()
                .addMerchantProduct(merchantAddProductReqModel)
        }

}

class MerchantDeleteProductViewModel:ViewModel(){
    fun deletePoductAPI(id:Int): LiveData<DataWrapper<JsonElement>> {
        return MerchantDeleteProductRepo()
            .deleteProduct(id)
    }

}

class MerchantProductDetailViewModel:ViewModel(){
    fun detailsPoductAPI(id:Int): LiveData<DataWrapper<JsonElement>> {
        return MerchantProductDetailsRepo()
            .detailsProduct(id)
    }

}