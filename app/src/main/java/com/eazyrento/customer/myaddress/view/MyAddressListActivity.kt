package com.eazyrento.customer.myaddress.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.myaddress.model.modelclass.AddressListResModel
import com.eazyrento.customer.myaddress.view.adapter.MyAddressAdapter
import com.eazyrento.customer.myaddress.viewmodel.AddressDeleteViewModel
import com.eazyrento.customer.myaddress.viewmodel.AddressListViewModel
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.login.model.modelclass.AddressInfo
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_my_address.*

open class MyAddressListActivity : BaseActivity() {
    private var defaultProductID: Int = -1
    private var deleteObjAddressAtPos = -1
    private var updateAddressInfo: AddressInfo? = null

    private lateinit var listOfAddress: ArrayList<AddressInfo>

    override fun <T> moveOnSelecetedItem(type: T) {

        if (defaultProductID != -1) {

            val intent = Intent()
            intent.putExtra(Constant.KEY_ADDRESS, type as AddressInfo)
            finishCurrentActivityWithResult(Constant.ADDRESS_REQUECT_CODE, intent)
        } else {
            updateAddressInfo = type as AddressInfo
            MoveToAnotherComponent.startActivityResultWithParcelable<AddNewAddressActivity, AddressInfo>(
                this,
                Constant.INTENT_ADDRESS_EDIT,
                updateAddressInfo!!,
                Constant.INTENT_UPDATE_DELETE_CREATE_REQUEST
            )

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_my_address)
        topBarWithBackIconAndTitle(getString(R.string.my_address))

        defaultProductID = intent.getIntExtra(Constant.INTENT_ADDR_LIST, -1)

        fetchAddressList()
    }

    fun onAddAddressClick(view: View) {
        MoveToAnotherComponent.startActivityForResult<AddNewAddressActivity>(this,Constant.INTENT_UPDATE_DELETE_CREATE_REQUEST,Constant.KEY_DATA_IS_CREATING,1)
    }

    private fun fetchAddressList() {
        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<AddressListViewModel>(this)
                    .getAddressList()
                , this, this
            )

        }
    }

    override fun <T> onSuccessApiResult(data: T) {
        if (deleteObjAddressAtPos != -1 && data is JsonElement) {

            if (listOfAddress.size > 0) {
                listOfAddress.removeAt(deleteObjAddressAtPos)
                deleteObjAddressAtPos = -1
                rec_my_address.adapter?.notifyDataSetChanged()
            }
            return

        }

        listOfAddress = data as AddressListResModel
        rec_my_address.adapter =
            MyAddressAdapter(
                listOfAddress,
                this
            )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constant.INTENT_UPDATE_DELETE_CREATE_REQUEST && resultCode == Activity.RESULT_OK) {

            val addressInfo =
                data?.getParcelableExtra<AddressInfo>(Constant.KEY_UPDATE_DELETE_CREATE_REQUEST)

            val isDelete =data?.getBooleanExtra("delete",false)

                if (addressInfo?.id == updateAddressInfo?.id) {

                    listOfAddress.remove(updateAddressInfo)
                    updateAddressInfo = null

                }

            //editing or crating .....
            if (addressInfo!=null && isDelete==false)
                listOfAddress.add(addressInfo)

            rec_my_address.adapter?.notifyDataSetChanged()



        }
    }

    fun deleteAddress(addressInfoPos: Int) {

        deleteObjAddressAtPos = addressInfoPos

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<AddressDeleteViewModel>(this)
                    .deleteAddress(listOfAddress.get(addressInfoPos).id!!)
                , this, this
            )
        }
    }

}