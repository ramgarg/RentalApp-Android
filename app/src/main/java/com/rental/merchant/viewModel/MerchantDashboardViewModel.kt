package com.rental.merchant.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.common.model.modelclass.ProductCategoriesResModel
import com.rental.merchant.model.repository.MerchantProductCategoriesRepo


class MerchantProductCategoriesViewModel:ViewModel(){
    fun getProductCateg(): LiveData<DataWrapper<ProductCategoriesResModel>> {
        return MerchantProductCategoriesRepo().getProductCateg()
    }
}