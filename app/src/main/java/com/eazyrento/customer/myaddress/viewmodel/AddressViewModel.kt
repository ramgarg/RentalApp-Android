package com.eazyrento.customer.myaddress.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.customer.myaddress.model.modelclass.AddressCreateReqModel
import com.eazyrento.customer.myaddress.model.modelclass.AddressCreateReqModelItem
import com.eazyrento.customer.myaddress.model.modelclass.AddressDetailsResModel
import com.eazyrento.customer.myaddress.model.modelclass.AddressListResModel
import com.eazyrento.customer.myaddress.model.repostory.*
import com.google.gson.JsonElement

class AddressListViewModel : ViewModel() {

        fun getAddressList(): LiveData<DataWrapper<AddressListResModel>> {
            return AddressListRepo()
                .getAddressList()
        }
    }

class AddressCreateViewModel : ViewModel() {

    fun createAddress(addressCreateReqModel: AddressCreateReqModel): LiveData<DataWrapper<JsonElement>> {
        return AddressCreateRepo()
            .addressCreate(addressCreateReqModel)
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
    fun updateAddress(addressCreateReqModelItem: AddressCreateReqModelItem,id: Int): LiveData<DataWrapper<JsonElement>> {
        return AddressUpdateRepo()
            .addressUpdate(addressCreateReqModelItem,id)
    }
}


