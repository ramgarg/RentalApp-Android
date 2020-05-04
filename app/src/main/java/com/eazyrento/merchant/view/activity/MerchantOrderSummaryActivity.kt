package com.eazyrento.merchant.view.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.agent.view.adapter.AgentOrderSummaryUsersAdapter
import com.eazyrento.common.view.OrderBaseSummaryActivity
import com.eazyrento.customer.dashboard.model.modelclass.AgentDetail
import com.eazyrento.customer.dashboard.model.modelclass.CustomerDetail
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderDetailsResModel
import com.eazyrento.customer.dashboard.model.modelclass.MerchantDetail
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.merchant.view.adapter.MerchantUsersOrderSummaryAdapter
import kotlinx.android.synthetic.main.activity_agent_order_summary.*
import kotlinx.android.synthetic.main.activity_agent_order_summary.rec_user_order_summary
import kotlinx.android.synthetic.main.activity_merchant_order_summary.*
import kotlinx.android.synthetic.main.adapter_user_order_summery.*
import kotlinx.android.synthetic.main.adapter_users_order_summary.*
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
        orderStatus(orderRes)

    }
    private fun orderStatus(orderRes: CustomerOrderDetailsResModel) {
        val customerDetail = orderRes.customer_detail
        val agentDetail = orderRes.agent_detail
        if (orderRes.order_status == Constant.COMPLETED) {
            //agent_update_order_btn.visibility = View.INVISIBLE
            agent_asign_merchant_btn.visibility = View.INVISIBLE

            if (agentDetail != null) {
                merchant_user1_view.visibility = View.VISIBLE
                tv_user_name.text = agentDetail.full_name
                tv_user_tag.text = agentDetail.mobile_number
                img_user_call.visibility = View.VISIBLE
                //img_users_call.contentDescription=orderRes.agent_detail.mobile_number

            }
            else{
                merchant_user1_view.visibility=View.INVISIBLE
            }
            if (customerDetail != null) {

                merchant_user2_view.visibility= View.VISIBLE
                tv_users_name.text=customerDetail.full_name
                tv_users_tag.text=customerDetail.mobile_number
                img_users_call.visibility=View.INVISIBLE
            } else {
                merchant_user2_view.visibility = View.INVISIBLE
            }
        } else if (orderRes.order_status != Constant.COMPLETED) {
            pending_amount.visibility = View.VISIBLE
            pending_amount.text = Constant.PENDING_AMOUNT + "- " + orderRes.pending_order_amount
            if (agentDetail != null) {
                merchant_user1_view.visibility = View.VISIBLE
                tv_user_name.text = agentDetail.full_name
                tv_user_tag.text = agentDetail.mobile_number
                img_user_call.visibility = View.VISIBLE
                //img_users_call.contentDescription=orderRes.agent_detail.mobile_number

            }
            else{
                merchant_user1_view.visibility=View.INVISIBLE
            }
            if (customerDetail != null) {

                merchant_user2_view.visibility = View.VISIBLE
                tv_users_name.text=customerDetail.full_name
                tv_users_tag.text=customerDetail.mobile_number
                img_users_call.visibility=View.INVISIBLE
            } else {
                merchant_user2_view.visibility = View.INVISIBLE
            }

        }


    }

}