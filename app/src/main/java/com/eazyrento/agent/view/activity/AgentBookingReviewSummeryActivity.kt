package com.eazyrento.agent.view.activity

import android.os.Bundle
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.agent.viewmodel.AgentMerchantNeearByViewModel
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.model.modelclass.BookingListItem
import com.eazyrento.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.activity_agent_order_summary.*

class AgentBookingReviewSummeryActivity:AgentOrderSummaryActivity() {

    lateinit var bookingITem:BookingListItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        agent_asign_merchant_btn.text = resources.getString(R.string.assign_merchant)

        bookingITem = intent.getParcelableExtra<BookingListItem>(Constant.BOOKING_SUMMERY_KEY)

        agent_asign_merchant_btn.setOnClickListener {

            MoveToAnotherComponent.openActivityWithParcelableParam<AgentFinalAsignMerchantActivity,BookingListItem>(this,Constant.BOOKING_SUMMERY_KEY,bookingITem)
        }


    }



}