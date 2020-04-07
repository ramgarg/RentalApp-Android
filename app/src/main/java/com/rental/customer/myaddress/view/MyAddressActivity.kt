package com.rental.customer.myaddress.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rental.R
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.common.view.BaseActivity
import com.rental.customer.myaddress.view.adapter.MyAddressAdapter
import com.rental.customer.myaddress.viewmodel.MyAddressViewModel
import com.rental.customer.utils.*
import kotlinx.android.synthetic.main.activity_my_address.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus

class MyAddressActivity :BaseActivity(),RecyclerViewItemClick {

    private lateinit var myAddressViewModel:MyAddressViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_my_address)

      initView()
    }

    private fun initView(){

        myAddressViewModel=ViewModelProviders.of(this).get(MyAddressViewModel::class.java)
        myAddressViewModel.getMyAddressResponse().observe(this, Observer {
            rec_my_address.adapter=MyAddressAdapter(it.data,this,this)
        })




        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,
            toolbar_title,getString(R.string.my_address))
        btn_add_new_address.setOnClickListener {
            EventBus.getDefault().postSticky("AddNew")
//            RxBus.publish(EventModel("AddNew"))

            MoveToAnotherComponent.moveToAddNewAddressActivity(this)

        }

    }

    override fun onItemClick(item: Data) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.



    }



}