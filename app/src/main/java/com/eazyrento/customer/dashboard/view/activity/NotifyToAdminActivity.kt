package com.eazyrento.customer.dashboard.view.activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.dashboard.model.modelclass.NotifyAdminProductUnavailble
import com.eazyrento.customer.dashboard.view.adapter.VehicleSpinnerAdapter
import com.eazyrento.customer.dashboard.viewmodel.NotifyAdminProdUnavailbleViewModel
import com.eazyrento.customer.payment.viewmodel.PaymentHistoryViewModel
import com.eazyrento.customer.utils.ViewVisibility
import com.eazyrento.merchant.viewModel.MerchantNotifyAdminViewModel
import kotlinx.android.synthetic.main.activity_notify_admin.*
import kotlinx.android.synthetic.main.activity_rate_and_review.*
import kotlinx.android.synthetic.main.toolbar.*

class NotifyToAdminActivity :BaseActivity(){

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify_admin)
        topBarWithBackIconAndTitle(getString(R.string.notify_admin))

    }

    fun onSubmitClick(view:View){

        // validation to check
        if (checkValidation())
            return

        sendDataToServer()
    }

    private fun checkValidation(): Boolean {
        if (ed_product_name.text.toString().isEmpty()){
            showToast(R.string.VALID_NAME)
            return true
        }else if (ed_product_capacity.text.toString().isEmpty()) {
            showToast(R.string.VALID_CAPACITY)
            return true
        }
        return false

    }

    private fun sendDataToServer(){

        var name = ed_product_name.text.toString()
        var capacity = ed_product_capacity.text.toString()
        var description = ed_product_details.text.toString()

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<NotifyAdminProdUnavailbleViewModel>(this)
                    .notifyAdmin(NotifyAdminProductUnavailble(name,capacity,description))
                , this, this
            )
        }
    }

    override fun <T> onSuccessApiResult(data: T) {
        super.onSuccessApiResult(data)
        showToast(R.string.REQUEST_SUCCESSED)
        finishCurrentActivity(Activity.RESULT_OK)
    }
}