package com.eazyrento.customer.dashboard.view.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.common.view.OrderBaseSummaryActivity
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderDetailsResModel
import com.eazyrento.customer.dashboard.model.modelclass.MerchantDetail
import com.eazyrento.customer.dashboard.view.adapter.CustomerOrderSummaryUsersAdapter
import com.eazyrento.customer.payment.view.PaymentHistoryActivity
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.merchant.model.modelclass.FeedbackReqModel
import com.eazyrento.merchant.view.activity.RateAndReviewActivity
import kotlinx.android.synthetic.main.activity_agent_order_summary.*
import kotlinx.android.synthetic.main.activity_customer_order_summary.*
import kotlinx.android.synthetic.main.activity_customer_order_summary.rec_user_order_summary
import kotlinx.android.synthetic.main.adapter_user_order_summery.*
import kotlinx.android.synthetic.main.adapter_users_order_summary.*
import kotlinx.android.synthetic.main.phone_view.*
import kotlinx.android.synthetic.main.template_order_summery_top_view.*


class CustomerOrderSummaryActivity : OrderBaseSummaryActivity() {

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_customer_order_summary)

        setDataAndCallOrderDetailsAPI(intent.extras?.getInt(Constant.ORDER_SUMMERY_KEY)!!)
        clickListenerOnViews()
    }

    private fun clickListenerOnViews(){
       payment_view_history.setOnClickListener { MoveToAnotherComponent.moveToActivityNormal<PaymentHistoryActivity>(this) }
        order_rate_review.setOnClickListener {

            feedbackReqModel.agent_id = orderRes.agent_detail.id
            rateAndReviews(feedbackReqModel)
        }
        customer_payment_button.setOnClickListener { MoveToAnotherComponent.moveToActivityNormal<PaymentActivity>(this) }
    }

    override fun <T> onSuccessApiResult(data: T) {
        super.onSuccessApiResult(data)
        orderStatus(orderRes)
    }

     fun orderStatus(orderRes: CustomerOrderDetailsResModel) {
         val merchantdetail=orderRes.merchant_detail
         val agentdetail=orderRes.agent_detail

        if(orderRes.order_status== Constant.COMPLETED)
        {
            if (agentdetail != null) {
                users_view.visibility = View.VISIBLE
                tv_users_name.text = agentdetail.full_name
                tv_users_tag.text = agentdetail.mobile_number
                phone_view.setOnClickListener {
                    Common.phoneCallWithNumber(agentdetail.mobile_number, this) }
                //img_users_call.contentDescription=orderRes.agent_detail.mobile_number

            }
            if(merchantdetail != null) {
                if (merchantdetail.isNotEmpty()) {
                    rec_user_order_summary.visibility = View.VISIBLE
                    phone_view.visibility = View.INVISIBLE
                    setUsersAdapter(orderRes)
                } else {
                    rec_user_order_summary.visibility = View.INVISIBLE
                }
            }

        }
        else if(orderRes.order_status!= Constant.COMPLETED) {
            if (agentdetail != null) {
                users_view.visibility = View.VISIBLE
                tv_users_name.text = agentdetail.full_name
                tv_users_tag.text = agentdetail.mobile_number
                phone_view.setOnClickListener {
                    Common.phoneCallWithNumber(agentdetail.mobile_number, this) }

            } else {
                rec_user_order_summary.visibility = View.INVISIBLE
            }
            if (merchantdetail != null) {
                if (orderRes.merchant_detail.isNotEmpty()) {
                    rec_user_order_summary.visibility = View.VISIBLE
                    phone_view.visibility=View.INVISIBLE
                    setUsersAdapter(orderRes)
                } else {
                    rec_user_order_summary.visibility = View.INVISIBLE
                }
            }
        }
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
            CustomerOrderSummaryUsersAdapter(
                customerOderDetailsResponse.merchant_detail as MutableList<MerchantDetail>,this)

        rec_user_order_summary.adapter = recycleAdapterUsersHomeCard
    }

}