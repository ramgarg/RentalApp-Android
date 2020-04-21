package com.rental.customer.myaddress.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.customer.myaddress.model.modelclass.AddressListResModel
import com.rental.customer.myaddress.model.repostory.AddressListRepo

class AddressListViewModel : ViewModel() {

        fun getAddressList(): LiveData<DataWrapper<AddressListResModel>> {
            return AddressListRepo().getAddressList()
        }
    }
