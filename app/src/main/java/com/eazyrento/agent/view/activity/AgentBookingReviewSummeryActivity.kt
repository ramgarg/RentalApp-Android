package com.eazyrento.agent.view.activity

import android.os.Bundle
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.agent.viewmodel.AgentBookingDetailsViewModel
import com.eazyrento.agent.viewmodel.AgentMerchantNeearByViewModel
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.model.modelclass.BookingListItem
import com.eazyrento.customer.dashboard.viewmodel.CustomerOrderDetailsViewModel
import com.eazyrento.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.activity_agent_order_summary.*

class AgentBookingReviewSummeryActivity:AgentOrderSummaryActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

       val bookingITem = intent.getParcelableExtra<BookingListItem>(Constant.BOOKING_SUMMERY_KEY)

        agent_asign_merchant_btn.text = resources.getString(R.string.assign_merchant)

        agent_asign_merchant_btn.setOnClickListener {

            MoveToAnotherComponent.openActivityWithParcelableParam<AgentFinalAsignMerchantActivity,BookingListItem>(this,Constant.BOOKING_SUMMERY_KEY,bookingITem)
        }


        //bookinjg details API call

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<AgentBookingDetailsViewModel>(this)
                    .agentBookingDetails(bookingITem.id)
                , this, this
            )
        }



    }

    override fun <T> onSuccessApiResult(data: T) {
        super.onSuccessApiResult(data)
    }


}