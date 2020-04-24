package com.eazyrento.customer.payment.view

import android.os.Bundle
import com.eazyrento.R
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.payment.model.modelclass.PaymentListResModel
import com.eazyrento.customer.payment.viewmodel.PaymentHistoryViewModel
import com.eazyrento.customer.payment.viewmodel.PaymentListViewModel
import com.eazyrento.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_payment_history.*
import kotlinx.android.synthetic.main.toolbar.*

class PaymentHistoryActivity : BaseActivity() {

    private lateinit var paymentHistoryViewModel: PaymentHistoryViewModel
    override fun <T> moveOnSelecetedItem(type: T) {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_history)

        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,
            toolbar_title,"Payment History")

        getPaymentList()

//        paymentHistoryViewModel=ViewModelProviders.of(this).get(PaymentHistoryViewModel::class.java)
//        paymentHistoryViewModel.getPaymentHistoryResponse().observe(this, Observer {
//            rec_payment_history.adapter= PaymentHistoryAdapter(it.data,this)
//        })


    }

    private fun getPaymentList() {
        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<PaymentListViewModel>(this)
                    .getAddressList()
                , this, this
            )

        }
    }

    override fun <T> onSuccessApiResult(data: T) {
        val paymentList = data as PaymentListResModel
        rec_payment_history.adapter=
            PaymentHistoryAdapter(
                paymentList,
                this
            )
    }
}