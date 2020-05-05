package com.eazyrento.merchant.view.activity

import android.os.Bundle
import android.view.View
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.myaddress.viewmodel.AddressCreateViewModel
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.merchant.model.modelclass.MerchantNotifyAdminReqModelItem
import com.eazyrento.merchant.viewModel.MerchantNotifyAdminViewModel
import kotlinx.android.synthetic.main.activity_merchant_notify_admin.*
import kotlinx.android.synthetic.main.add_new_address_activity.*

class MerchantNotifyAdminActivity : BaseActivity() {

    val notifyAdminReqModelItem = MerchantNotifyAdminReqModelItem()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant_notify_admin)
        topBarWithBackIconAndTitle(resources.getString(R.string.notify_admin))
        initView()
    }

    private fun initView() {
        btn_notify_admin_submit.setOnClickListener { moveTOSave() }
    }

    private fun moveTOSave() {
        if (checkValidation())
            return
        setNotifyAdminListItem()
    }

    private fun checkValidation(): Boolean {

        when {
            ed_name.text.toString().isEmpty()->showToast(ValidationMessage.VALID_NAME)
            ed_capacity.text.toString().isEmpty()->showToast(ValidationMessage.VALID_CAPACITY)
            ed_fuel_type.text.toString().isEmpty()->showToast(ValidationMessage.VALID_FUEL_TYPE)
            ed_power.text.toString().isEmpty()->showToast(ValidationMessage.VALID_POWER)
            ed_price.text.toString().isEmpty()->showToast(ValidationMessage.VALID_PRICE)
            ed_des.text.toString().isEmpty()->showToast(ValidationMessage.VALID_DESC)

            else-> {
                return false
            }
        }
        return true

    }

    private fun setNotifyAdminListItem() {
        notifyAdminReqModelItem.name=ed_name.text.toString()
        notifyAdminReqModelItem.capacity=ed_capacity.text.toString()
        notifyAdminReqModelItem.power=ed_power.text.toString()
        notifyAdminReqModelItem.price=ed_price.text.toString()
        notifyAdminReqModelItem.fuel_type=ed_fuel_type.text.toString()
        notifyAdminReqModelItem.desc=ed_des.text.toString()

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<MerchantNotifyAdminViewModel>(this)
                    .notifyAdmin(notifyAdminReqModelItem)
                , this, this
            )
        }
    }

    override fun <T> onSuccessApiResult(data: T) {
        data?.let {
            showToast(ValidationMessage.ADMIN_NOTIFY)

                //MoveToAnotherComponent.moveToListAddressActivity(this)
        }
    }

    override fun <T> moveOnSelecetedItem(type: T) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}