package com.rental.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.common.model.modelclass.*
import com.rental.common.model.repositry.*

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

class ProductDetailsViewModel : ViewModel() {

    fun getProductDetails(id: Int): LiveData<DataWrapper<ProductDetailsResModel>> {
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

