package com.eazyrento.customer.dashboard.view.activity

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
import kotlinx.android.synthetic.main.toolbar.*

class NotifyToAdminActivity :BaseActivity(){

    var notifyAdminProductUnavailble = NotifyAdminProductUnavailble("xyz","11","dsd")

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify_admin)
        topBarWithBackIconAndTitle(getString(R.string.notify_admin))

    }

    fun onSubmitClick(view:View){

        // validation to check

        sendDataToServer()
    }

    private fun sendDataToServer(){
        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<NotifyAdminProdUnavailbleViewModel>(this)
                    .notifyAdmin(notifyAdminProductUnavailble)
                , this, this
            )
        }
    }

    override fun <T> onSuccessApiResult(data: T) {
        super.onSuccessApiResult(data)
        showToast(ValidationMessage.REQUEST_SUCCESSED)
    }
}