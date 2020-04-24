package com.eazyrento.merchant.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.common.model.modelclass.ProductCategoriesResModel
import com.eazyrento.merchant.model.repository.MerchantProductCategoriesRepo
import com.google.gson.JsonElement


class MerchantProductCategoriesViewModel:ViewModel(){
    fun getProductCateg(): LiveData<DataWrapper<JsonElement>> {
        return MerchantProductCategoriesRepo()
            .getProductCateg()
    }
}