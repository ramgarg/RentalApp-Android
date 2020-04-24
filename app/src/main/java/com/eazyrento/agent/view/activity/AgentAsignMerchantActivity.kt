package com.eazyrento.agent.view.activity

import android.os.Bundle
import com.eazyrento.R
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.common.viewmodel.OrderListingVM
import com.eazyrento.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.toolbar.*

class AgentAsignMerchantActivity : BaseActivity() {
    private lateinit var orderListingVM: OrderListingVM
    override fun <T> moveOnSelecetedItem(type: T) {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_assign_merchant)

        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,
            toolbar_title,"Payment History")


    }
}