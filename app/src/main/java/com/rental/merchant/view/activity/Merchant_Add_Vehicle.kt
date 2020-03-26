package com.rental.merchant.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rental.R
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.dashboard.view.adapter.CategoryAdapter
import com.rental.customer.dashboard.viewmodel.HomeViewModel
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.RecyclerViewItemClick
import com.rental.customer.utils.ViewVisibility
import com.rental.merchant.viewModel.MerchantHomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_merchant_home.*
import kotlinx.android.synthetic.main.merchant_add_vehicle.*
import kotlinx.android.synthetic.main.toolbar.*

class Merchant_Add_Vehicle : AppCompatActivity(), RecyclerViewItemClick {

    private lateinit var merchantViewModel: HomeViewModel
    private lateinit var arrayList:ArrayList<Data>
    var arrayListSort:ArrayList<Data> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.merchant_add_vehicle)

        ViewVisibility.isVisibleOrNot(
            this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.vehicle))

        merchantViewModel= ViewModelProviders.of(this).get(HomeViewModel::class.java)
        merchantViewModel.getHomeResponse().observe(this, Observer {
            rec_add_veichle.adapter= CategoryAdapter(it.data,this,this)
            arrayList= it.data as ArrayList<Data>

            //search(arrayList)
        })
    }

    override fun onItemClick(item: Data) {
        MoveToAnotherComponent.moveToCategoryActivity(this)
    }
}