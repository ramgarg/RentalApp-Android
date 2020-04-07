package com.rental.customer.dashboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rental.customer.dashboard.model.modelclass.HomeResponse
import com.rental.customer.dashboard.model.repositry.WishListRepository


class WishListViewModel : ViewModel() {
    var wishListRepository: WishListRepository
    var wishListResponseLiveData: LiveData<HomeResponse>

    init {
        wishListRepository = WishListRepository()
        wishListResponseLiveData = wishListRepository.getWishList()
    }



    fun getWishListResponse():LiveData<HomeResponse>{
        return wishListResponseLiveData
    }
}