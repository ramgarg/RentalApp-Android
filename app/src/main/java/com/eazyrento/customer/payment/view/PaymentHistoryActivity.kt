package com.eazyrento.customer.payment.view

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.agent.model.modelclass.PaymentRecivedOrNotReqModel
import com.eazyrento.agent.view.activity.AgentOrderSummaryActivity
import com.eazyrento.agent.viewmodel.AgentPaymentRecivedOrDeclineViewModel
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.dashboard.view.activity.CustomerOrderSummaryActivity
import com.eazyrento.customer.payment.model.modelclass.PaymentListResModel
import com.eazyrento.customer.payment.model.modelclass.PaymentListResModelItem
import com.eazyrento.customer.payment.viewmodel.PaymentHistoryViewModel
import com.eazyrento.customer.payment.viewmodel.PaymentListViewModel
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_payment_history.*
import kotlinx.android.synthetic.main.thank_you_pop.*
import kotlinx.android.synthetic.main.toolbar.*

class PaymentHistoryActivity : BaseActivity() {
    //private  var mOrderID:String? =null

    override fun <T> moveOnSelecetedItem(type: T) {
        val paymentListResModelItem = type as PaymentListResModelItem

        val dialog = showDialogCustomDialog(this)
        dialog.tv_msg.text = getString(R.string.payment_received)

        dialog.btn_cancle.let {
            it.visibility = View.VISIBLE
            it.text = getString(R.string.no)
            it.setOnClickListener {
                dialog.cancel()
                paymentReceviedOrNot(PaymentRecivedOrNotReqModel(paymentListResModelItem.id, false))
            }
        }
        dialog.btn_ok.let {
            it.text = getString(R.string.yes)
            it.setOnClickListener {
                dialog.cancel()
                paymentReceviedOrNot(PaymentRecivedOrNotReqModel(paymentListResModelItem.id, true))
            }
        }

        dialog.show()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_history)
        topBarWithBackIconAndTitle(getString(R.string.payment_history))

        var mOrderID = intent.getStringExtra(Constant.KEY_PAYMENT_HISTORY_CUSTOMER)

        if (mOrderID == null)
            mOrderID = intent.getStringExtra(Constant.KEY_PAYMENT_HISTORY_AGENT)

        getPaymentList(mOrderID)

    }

    private fun getPaymentList(orderID: String?) {
        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<PaymentListViewModel>(this)
                    .getPaymentList(orderID)
                , this, this
            )

        }
    }

    private fun paymentReceviedOrNot(paymentRecivedOrNotReqModel: PaymentRecivedOrNotReqModel) {

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<AgentPaymentRecivedOrDeclineViewModel>(this)
                    .paymentAcceptOrDecline(paymentRecivedOrNotReqModel)
                , this, this
            )

        }

    }

    override fun <T> onSuccessApiResult(data: T) {
        if (data is JsonElement){
            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())
            showToast(R.string.THANKYOU_FOR_CONFIRMING)
            if (intent.getStringExtra(Constant.KEY_PAYMENT_HISTORY_CUSTOMER)!=null){
                MoveToAnotherComponent.moveToActivityNormal<CustomerOrderSummaryActivity>(this)
            }
            else if (intent.getStringExtra(Constant.KEY_PAYMENT_HISTORY_AGENT)!=null){
                MoveToAnotherComponent.moveToActivityNormal<AgentOrderSummaryActivity>(this)
            }
            else finishCurrentActivity(Activity.RESULT_OK)
            return
        }

        if (data is PaymentListResModel) {

            if (data.isEmpty()) {
                showToast(R.string.NO_DATA_FOUND)
                return
            }
            rec_payment_history.adapter =
                PaymentHistoryAdapter(
                    data,
                    this
                )
        }
    }
}