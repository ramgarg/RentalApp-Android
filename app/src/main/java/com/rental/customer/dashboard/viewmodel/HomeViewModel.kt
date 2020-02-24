package com.rental.customer.dashboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rental.customer.dashboard.model.modelclass.HomeResponse
import com.rental.customer.dashboard.model.repositry.HomeRepository


class HomeViewModel : ViewModel() {
    var homeRepository: HomeRepository
    var homeResponseLiveData: LiveData<HomeResponse>

    init {
        homeRepository = HomeRepository()
        homeResponseLiveData = homeRepository.getVeichleList()
    }

    fun getArticleResponseLiveData(): LiveData<HomeResponse> {
        return homeResponseLiveData
    }
}