package com.eazyrento.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.common.model.modelclass.*
import com.eazyrento.common.model.repositry.*
import com.eazyrento.customer.dashboard.model.modelclass.OrderTrackingList
import com.eazyrento.customer.dashboard.model.modelclass.OrderTrackingListItem
import com.eazyrento.customer.dashboard.model.repositry.CustomerOrderTrackingRepo
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

// for dynamic key value
class ProductDetailsViewModel : ViewModel() {

    fun getProductDetails(id: Int): LiveData<DataWrapper<JsonElement>> {
        return ProductDetailsRepo().getProductDeatils(id)
    }
}

// for
class ProductDetailsForMerchantViewModel : ViewModel() {

    fun getProductDetailsForMarchant(id: Int): LiveData<DataWrapper<ProductDetailsResModel>> {
        return ProductDetailsMerchantRepo().getProductDeatils(id)
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

class OrderTrackingViewModel : ViewModel() {

    fun getOrderTrackingRepo(orderID: String, suborder_id: Int): LiveData<DataWrapper<OrderTrackingListItem>> {
        return OrderTrackingRepo()
            .getOrderTracking(orderID,suborder_id)
    }
}

