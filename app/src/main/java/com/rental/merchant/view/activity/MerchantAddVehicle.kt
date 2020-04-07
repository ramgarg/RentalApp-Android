package com.rental.merchant.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rental.R
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.dashboard.viewmodel.CustomerHomeViewModel
import com.rental.customer.utils.Common
import com.rental.customer.utils.RecyclerViewItemClick
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.toolbar.*

class MerchantAddVehicle : AppCompatActivity(), RecyclerViewItemClick {

    private lateinit var merchantViewModelCustomer: CustomerHomeViewModel
    private lateinit var arrayList:ArrayList<Data>
    var arrayListSort:ArrayList<Data> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.merchant_add_vehicle)

        ViewVisibility.isVisibleOrNot(
            this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.vehicle))

       /* merchantViewModelCustomer= ViewModelProviders.of(this).get(CustomerHomeViewModel::class.java)
        merchantViewModelCustomer.getHomeResponse().observe(this, Observer {
            rec_add_veichle.adapter= CategoryAdapter(it.data,this,this)
            arrayList= it.data as ArrayList<Data>

            //search(arrayList)
        })*/
    }

    override fun onItemClick(item: Data) {
        Common.showDialog("UserDay","",this,R.layout.add_vehicle_dialog)
    }
}