package com.eazyrento.customer.myaddress.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.customer.myaddress.model.modelclass.AddressListResModel
import com.eazyrento.customer.myaddress.model.repostory.AddressListRepo

class AddressListViewModel : ViewModel() {

        fun getAddressList(): LiveData<DataWrapper<AddressListResModel>> {
            return AddressListRepo()
                .getAddressList()
        }
    }
