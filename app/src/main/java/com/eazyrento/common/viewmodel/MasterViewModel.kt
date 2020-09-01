package com.eazyrento.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.common.model.modelclass.*
import com.eazyrento.common.model.repositry.*
import com.google.gson.JsonElement

class MasterDataViewModel : ViewModel() {

    fun getMasterData(): LiveData<DataWrapper<MasterResModel>> {
        return MasterRepo().getMasterData()
    }
}

class ProductCategoriesViewModel : ViewModel() {

    fun getProductCateg(name:String): LiveData<DataWrapper<JsonElement>> {
        return ProductCategoriesRepo().getProductCateg(name)
    }

}

class ProductSubCategoriesViewModel : ViewModel() {

    fun getProductSubCate(name: String): LiveData<DataWrapper<ProductSubCategoriesResModel>> {
        return ProductSubCategoriesRepo().getProductSubCateg(name)
    }
}

class ProductListBySubCategViewModel : ViewModel() {

    fun getProductListBySubCate(name: String): LiveData<DataWrapper<ProductListBySubCategoriesResModel>> {
        return ProductListByCatRepo().getProductListBySubCateg(name)
    }
}


class ProductDetailsViewModel : ViewModel() {

    fun getProductDetails(id: Int): LiveData<DataWrapper<JsonElement>> {
        return ProductDetailsRepo().getProductDeatils(id)
    }
}

class BookingDashboardViewModel : ViewModel() {

    fun getDashboard(value: Int): LiveData<DataWrapper<BookingDashboardResModel>> {
        return BookingDashboardRepo().getDeshboardData(value)
    }
}

class MyBookingViewModel : ViewModel() {

    fun getBookingList(value: Int): LiveData<DataWrapper<BookingListResModel>> {
        return MyBookingListRepo().getBookingListData(value)
    }
}

class LanguageChangeViewModel : ViewModel() {

    fun setLanguage(langaugeChangeReqModel: LangaugeChangeReqModel): LiveData<DataWrapper<JsonElement>> {
        return LanguagChangeRepo().setLanguage(langaugeChangeReqModel)
    }
}

