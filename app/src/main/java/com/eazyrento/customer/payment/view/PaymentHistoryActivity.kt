package com.eazyrento.customer.payment.view

import android.os.Bundle
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.payment.model.modelclass.PaymentListResModel
import com.eazyrento.customer.payment.viewmodel.PaymentHistoryViewModel
import com.eazyrento.customer.payment.viewmodel.PaymentListViewModel
import com.eazyrento.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_payment_history.*
import kotlinx.android.synthetic.main.toolbar.*

class PaymentHistoryActivity : BaseActivity() {

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_history)
        topBarWithBackIconAndTitle("payment_noti History")

        val orderID = intent.getStringExtra(Constant.KEY_PAYMENT_HISTORY)

        getPaymentList(orderID)

    }

    private fun getPaymentList(orderID:String?) {
        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<PaymentListViewModel>(this)
                    .getPaymentList(orderID)
                , this, this
            )

        }
    }

    override fun <T> onSuccessApiResult(data: T) {
        val paymentList = data as PaymentListResModel

        if (paymentList.isEmpty()){
            showToast(ValidationMessage.NO_DATA_FOUND)
            return
        }
        rec_payment_history.adapter=
            PaymentHistoryAdapter(
                paymentList,
                this
            )
    }
}