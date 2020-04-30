package com.eazyrento.customer.myaddress.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.customer.dashboard.model.modelclass.CustomerCreateBookingReqModel
import com.eazyrento.customer.dashboard.model.repositry.CustomerCreateBookingRepo
import com.eazyrento.customer.myaddress.model.modelclass.AddressCreateReqModel
import com.eazyrento.customer.myaddress.model.modelclass.AddressCreateReqModelItem
import com.eazyrento.customer.myaddress.model.modelclass.AddressDetailsResModel
import com.eazyrento.customer.myaddress.model.modelclass.AddressListResModel
import com.eazyrento.customer.myaddress.model.repostory.AddressCreateRepo
import com.eazyrento.customer.myaddress.model.repostory.AddressDeleteRepo
import com.eazyrento.customer.myaddress.model.repostory.AddressDetailsRepo
import com.eazyrento.customer.myaddress.model.repostory.AddressListRepo
import com.eazyrento.merchant.model.modelclass.MerchantAddProductReqModel
import com.eazyrento.merchant.model.modelclass.MerchantProductDetailsResModel
import com.eazyrento.merchant.model.repository.MerchantAddProductRepo
import com.eazyrento.merchant.model.repository.MerchantDeleteProductRepo
import com.eazyrento.merchant.model.repository.MerchantProductDetailsRepo
import com.google.gson.JsonElement

class AddressListViewModel : ViewModel() {

        fun getAddressList(): LiveData<DataWrapper<AddressListResModel>> {
            return AddressListRepo()
                .getAddressList()
        }
    }

class AddressCreateViewModel : ViewModel() {

    fun createAddress(addressCreateReqModelItem: AddressCreateReqModelItem): LiveData<DataWrapper<JsonElement>> {
        return AddressCreateRepo()
            .addressCreate(addressCreateReqModelItem)
    }
}

class AddressDeleteViewModel:ViewModel() {
    fun deleteAddress(id: Int): LiveData<DataWrapper<JsonElement>> {
        return AddressDeleteRepo()
            .deleteAddress(id)
    }
}

    class AddressDetailsViewModel:ViewModel(){
        fun addressdetailsAPI(id:Int): LiveData<DataWrapper<AddressDetailsResModel>> {
            return AddressDetailsRepo()
                .addressDetails(id)
        }

}

