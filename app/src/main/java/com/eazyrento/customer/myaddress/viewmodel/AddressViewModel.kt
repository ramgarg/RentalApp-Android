package com.eazyrento.customer.myaddress.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.customer.myaddress.model.modelclass.AddressDetailsResModel
import com.eazyrento.customer.myaddress.model.modelclass.AddressListResModel
import com.eazyrento.customer.myaddress.model.repostory.*
import com.eazyrento.login.model.modelclass.AddressInfo
import com.google.gson.JsonElement

class AddressListViewModel : ViewModel() {

        fun getAddressList(): LiveData<DataWrapper<AddressListResModel>> {
            return AddressListRepo()
                .getAddressList()
        }
    }

class AddressCreateViewModel : ViewModel() {

    fun createAddress(addressCreateReqModelItem: AddressInfo): LiveData<DataWrapper<AddressInfo>> {
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

class UpdateAddressViewModel:ViewModel() {
    fun updateAddress(addressCreateReqModelItem: AddressInfo,id: Int): LiveData<DataWrapper<JsonElement>> {
        return AddressUpdateRepo()
            .addressUpdate(addressCreateReqModelItem,id)
    }
}


