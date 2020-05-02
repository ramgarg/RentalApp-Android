package com.eazyrento.agent.view.activity

import android.os.Bundle
import android.view.View
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.OrderBaseSummaryActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.activity_agent_order_summary.*
import kotlinx.android.synthetic.main.adapter_users_order_summary.*
import kotlinx.android.synthetic.main.template_order_summery_top_view.*

open class AgentOrderSummaryActivity : OrderBaseSummaryActivity() {

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_agent_order_summary)

        clickListenerOnViews()
    }
    private fun clickListenerOnViews(){
        customer_payment_button.visibility=View.INVISIBLE
        payment_view_history.visibility=View.VISIBLE
        tv_users_tag.setText("Merchant")
        payment_view_history.setOnClickListener { MoveToAnotherComponent.moveToPaymentHistoryActivity(this)}
        agent_update_order_btn.setOnClickListener { MoveToAnotherComponent.moveToAgentUpdateOrderSummaryActivity(this) }
    }

   override fun <T> onSuccessApiResult(data: T) {
       AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())
   }
}