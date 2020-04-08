package com.rental.merchant.view.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rental.R
import com.rental.common.view.BaseActivity
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.RecyclerViewItemClick
import com.rental.customer.utils.ViewVisibility
import com.rental.merchant.view.adapter.MerchantAddressAdapter
import com.rental.merchant.viewModel.MerchantAddressViewModel
import kotlinx.android.synthetic.main.activity_merchant_address.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus

class MerchantAddressActivity : BaseActivity(), RecyclerViewItemClick {

    private lateinit var merchantAddressViewModel: MerchantAddressViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_merchant_address)

        initView()
    }

    private fun initView(){

        merchantAddressViewModel= ViewModelProviders.of(this).get(MerchantAddressViewModel::class.java)
        merchantAddressViewModel.getMyAddressResponse().observe(this, Observer {
            rec_merchant_address.adapter= MerchantAddressAdapter(it.data,this,this)
        })




        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,
            toolbar_title,getString(R.string.my_address))
        btn_add_merchant_new_address.setOnClickListener {
            EventBus.getDefault().postSticky("AddNew")
//            RxBus.publish(EventModel("AddNew"))

            MoveToAnotherComponent.moveToAddNewAddressActivity(this)

        }

    }

    override fun onItemClick(item: Data) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.



    }
}