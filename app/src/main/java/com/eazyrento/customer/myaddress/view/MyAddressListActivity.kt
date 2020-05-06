package com.eazyrento.customer.myaddress.view

import android.content.Intent
import android.os.Bundle
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.myaddress.model.modelclass.AddressListResModel
import com.eazyrento.customer.myaddress.model.modelclass.AddressListResModelItem
import com.eazyrento.customer.myaddress.view.adapter.MyAddressAdapter
import com.eazyrento.customer.myaddress.viewmodel.AddressListViewModel
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_my_address.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus

open class MyAddressListActivity : BaseActivity() {
    var defaultProductID:Int = -1

    override fun <T> moveOnSelecetedItem(type: T) {

        if (defaultProductID!=-1) {

            val intent = Intent()
            intent.putExtra(Constant.KEY_ADDRESS, type as AddressListResModelItem)
            finishCurrentActivityWithResult(Constant.ADDRESS_REQUECT_CODE, intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_my_address)

        defaultProductID = intent.getIntExtra(Constant.INTENT_ADDR_LIST,-1)

        initView()
    }

    private fun initView(){

        getAddressList()

        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,
            toolbar_title,getString(R.string.my_address))
        btn_add_new_address.setOnClickListener {
            EventBus.getDefault().postSticky("AddNew")
//            RxBus.publish(EventModel("AddNew"))

            add_new_address.setOnClickListener { MoveToAnotherComponent.moveToAddNewAddressActivity(this)}

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