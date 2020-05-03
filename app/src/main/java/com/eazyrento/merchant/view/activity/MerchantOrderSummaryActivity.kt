package com.eazyrento.merchant.view.activity

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.common.view.OrderBaseSummaryActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.order_summary_template.*
import kotlinx.android.synthetic.main.template_order_summery_top_view.*

class MerchantOrderSummaryActivity : OrderBaseSummaryActivity() {

//    lateinit var orderSummaryViewModel: OrderSummaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_merchant_order_summary)
        customer_payment_button.visibility=View.INVISIBLE

        // order details
        setDataAndCallOrderDetailsAPI(intent.extras?.getInt(Constant.ORDER_SUMMERY_KEY)!!)

    }

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    private fun clickListenerOnViews(){
       // tv_view_history.setOnClickListener { MoveToAnotherComponent.moveToPaymentHistoryActivity(this) }
        //tv_pay_now.setOnClickListener { MoveToAnotherComponent.moveToPaymentActivity(this) }
        tv_merchant_rate_review.setOnClickListener { MoveToAnotherComponent.moveToOrderReviewActivity(this) }
    }


    override fun <T> onSuccessApiResult(data: T) {
        super.onSuccessApiResult(data)
    }

}