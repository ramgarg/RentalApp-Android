package com.rental.merchant.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rental.customer.dashboard.model.modelclass.HomeResponse
import com.rental.merchant.model.repository.MerchantAddressRepository

class MerchantAddressViewModel :ViewModel() {
    var merchantAddressRepository: MerchantAddressRepository
    var merchantAddressResponseLiveData: LiveData<HomeResponse>

    init {
        merchantAddressRepository= MerchantAddressRepository()
        merchantAddressResponseLiveData=merchantAddressRepository.getMyAddress()
    }

    fun getMyAddressResponse(): LiveData<HomeResponse> {
        return merchantAddressResponseLiveData
    }
}