package com.rental.merchant.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rental.customer.dashboard.model.modelclass.HomeResponse
import com.rental.merchant.model.MerchantHomeRepository

class MerchantHomeViewModel : ViewModel() {
    var merchantHomeRepository: MerchantHomeRepository
    var merchant_homeResponseLiveData: LiveData<HomeResponse>

    init {
        merchantHomeRepository =
            MerchantHomeRepository()
        merchant_homeResponseLiveData = merchantHomeRepository.getVeichleList()
    }



    fun getmerchant_HomeResponse(): LiveData<HomeResponse> {
        return merchant_homeResponseLiveData
    }
}