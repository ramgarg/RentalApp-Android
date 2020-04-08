package com.rental.agent.view.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rental.R
import com.rental.common.view.BaseActivity
import com.rental.common.viewmodel.OrderListingVM
import com.rental.customer.payment.view.PaymentHistoryAdapter
import com.rental.customer.payment.viewmodel.PaymentHistoryViewModel
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_payment_history.*
import kotlinx.android.synthetic.main.toolbar.*

class AgentAsignMerchantActivity : BaseActivity() {
    private lateinit var orderListingVM: OrderListingVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_assign_merchant)

        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,
            toolbar_title,"Payment History")


    }
}