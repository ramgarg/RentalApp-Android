package com.rental.customer.myaddress.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rental.customer.dashboard.model.modelclass.HomeResponse
import com.rental.customer.myaddress.model.MyAddressRepository

class MyAddressViewModel :ViewModel() {

    var myAddressRepository:MyAddressRepository
    var myAddressResponseLiveData: LiveData<HomeResponse>

    init {
        myAddressRepository= MyAddressRepository()
        myAddressResponseLiveData=myAddressRepository.getMyAddress()
    }

    fun getMyAddressResponse():LiveData<HomeResponse>{
        return myAddressResponseLiveData
    }
}