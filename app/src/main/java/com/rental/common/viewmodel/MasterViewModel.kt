package com.rental.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.common.model.modelclass.MasterResModel
import com.rental.common.model.modelclass.ProductCategoriesResModel
import com.rental.common.model.modelclass.ProductSubCategoriesResModel
import com.rental.common.model.repositry.MasterRepo
import com.rental.common.model.repositry.ProductCategoriesRepo
import com.rental.common.model.repositry.ProductSubCategoriesRepo

class MasterDataViewModel : ViewModel() {

    fun getMasterData(): LiveData<DataWrapper<MasterResModel>> {
        return MasterRepo().getMasterData()
    }
}

class ProductCategoriesViewModel : ViewModel() {

    fun getProductCateg(name:String): LiveData<DataWrapper<ProductCategoriesResModel>> {
        return ProductCategoriesRepo().getProductCateg(name)
    }
}

class ProductSubCategoriesViewModel : ViewModel() {

    fun getProductSubCate(name: String): LiveData<DataWrapper<ProductSubCategoriesResModel>> {
        return ProductSubCategoriesRepo().getProductSubCateg(name)
    }
}

