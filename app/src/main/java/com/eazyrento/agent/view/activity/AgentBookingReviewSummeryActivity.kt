package com.eazyrento.agent.view.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.agent.view.adapter.AgentOrderSummaryUsersAdapter
import com.eazyrento.agent.viewmodel.AgentBookingDetailsViewModel
import com.eazyrento.agent.viewmodel.AgentMerchantNeearByViewModel
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.model.modelclass.BookingListItem
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderDetailsResModel
import com.eazyrento.customer.dashboard.model.modelclass.MerchantDetail
import com.eazyrento.customer.dashboard.viewmodel.CustomerOrderDetailsViewModel
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_agent_order_summary.*
import kotlinx.android.synthetic.main.template_order_summery_top_view.*
import kotlinx.android.synthetic.main.toolbar.*

class AgentBookingReviewSummeryActivity:AgentOrderSummaryActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

       val bookingITem = intent.getParcelableExtra<BookingListItem>(Constant.BOOKING_SUMMERY_KEY)

        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,
            toolbar_title,getString(R.string.booking_details))

        agent_asign_merchant_btn.text = resources.getString(R.string.assign_merchant)
        customer_payment_button.visibility = View.INVISIBLE
        payment_view_history.visibility = View.INVISIBLE
        agent_update_order_btn.visibility=View.INVISIBLE
        pending_amount.visibility=View.INVISIBLE

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
        bookingsDetail(orderRes)


    }

    private fun bookingsDetail(orderRes: CustomerOrderDetailsResModel) {

    }

    private fun setUsersAdapter(customerOderDetailsResponse: CustomerOrderDetailsResModel) {
        rec_user_order_summary.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false
        )
        (rec_user_order_summary.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
            1,
            1
        )

        val recycleAdapterUsersHomeCard =
            AgentOrderSummaryUsersAdapter(
                customerOderDetailsResponse.merchant_detail as MutableList<MerchantDetail>,this)

        rec_user_order_summary.adapter = recycleAdapterUsersHomeCard
    }

}