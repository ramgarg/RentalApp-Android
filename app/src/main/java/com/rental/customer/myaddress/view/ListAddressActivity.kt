package com.rental.customer.myaddress.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rental.R
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.common.view.BaseActivity
import com.rental.customer.myaddress.model.modelclass.AddressListResModel
import com.rental.customer.myaddress.view.adapter.MyAddressAdapter
import com.rental.customer.myaddress.viewmodel.AddressListViewModel
import com.rental.customer.utils.*
import kotlinx.android.synthetic.main.activity_my_address.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus

open class ListAddressActivity :BaseActivity() {

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_my_address)

      initView()
    }

    private fun initView(){

        getAddressList()

        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,
            toolbar_title,getString(R.string.my_address))
        btn_add_new_address.setOnClickListener {
            EventBus.getDefault().postSticky("AddNew")
//            RxBus.publish(EventModel("AddNew"))

            MoveToAnotherComponent.moveToAddNewAddressActivity(this)

        }

    }

    private fun getAddressList() {
        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<AddressListViewModel>(this)
                    .getAddressList()
                , this, this
            )

        }
    }

    override fun <T> onSuccessApiResult(data: T) {
        val adressList = data as AddressListResModel
        rec_my_address.adapter=MyAddressAdapter(adressList,this)
    }

}