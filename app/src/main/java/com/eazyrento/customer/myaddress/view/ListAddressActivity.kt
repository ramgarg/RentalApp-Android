package com.eazyrento.customer.myaddress.view

import android.os.Bundle
import com.eazyrento.R
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.myaddress.model.modelclass.AddressListResModel
import com.eazyrento.customer.myaddress.view.adapter.MyAddressAdapter
import com.eazyrento.customer.myaddress.viewmodel.AddressListViewModel
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_my_address.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus

open class ListAddressActivity : BaseActivity() {

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
        rec_my_address.adapter=
            MyAddressAdapter(
                adressList,
                this
            )
    }

}