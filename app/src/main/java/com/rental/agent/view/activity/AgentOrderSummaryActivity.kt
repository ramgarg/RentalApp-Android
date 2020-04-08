package com.rental.agent.view.activity

import android.os.Bundle
import com.rental.R
import com.rental.common.view.BaseActivity
import com.rental.customer.dashboard.viewmodel.OrderSummaryViewModel
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.agent_order_summary.*
import kotlinx.android.synthetic.main.order_summary_activity.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus

class AgentOrderSummaryActivity : BaseActivity() {

    lateinit var orderSummaryViewModel : OrderSummaryViewModel
    override fun <T> moveOnSelecetedItem(type: T) {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.agent_order_summary)
        setResponseViews()
        ViewVisibility.isVisibleOrNot(this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.order_summary))

        clickListenerOnViews()
    }
    private fun clickListenerOnViews(){
        agent_asign_merchant_btn.setOnClickListener { MoveToAnotherComponent.moveToPaymentHistoryActivity(this) }
    }

    private fun setResponseViews() {
        agent_tv_st_date_sel.text="12 Jan 2020"
        agent_tv_st_time_sel.text="4:00pm"
        agent_tv_end_date_sel.text="12 Feb 2020"
        agent_tv_end_time_sel.text="3:00pm"
        agent_checkbox_with_driver.isClickable=false
    }

}