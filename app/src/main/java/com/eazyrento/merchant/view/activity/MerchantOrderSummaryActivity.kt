package com.eazyrento.merchant.view.activity

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.common.view.OrderBaseSummaryActivity
import com.eazyrento.customer.dashboard.model.modelclass.*
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.merchant.model.modelclass.FeedbackReqModel
import kotlinx.android.synthetic.main.activity_merchant_order_summary.*
import kotlinx.android.synthetic.main.adapter_user_order_summery.*
import kotlinx.android.synthetic.main.adapter_users_order_summary.*
import kotlinx.android.synthetic.main.phone_view.*
import kotlinx.android.synthetic.main.template_order_summery_top_view.*
import kotlinx.android.synthetic.main.template_order_summery_top_view.order_rate_review

class MerchantOrderSummaryActivity : OrderBaseSummaryActivity() {

    val feedbackReqModel = FeedbackReqModel()

//    lateinit var orderSummaryViewModel: OrderSummaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_merchant_order_summary)

        // order details
        setDataAndCallOrderDetailsAPI(intent.extras?.getInt(Constant.ORDER_SUMMERY_KEY)!!)
        clickListenerOnViews()

    }

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    private fun clickListenerOnViews(){

        customer_payment_button.visibility=View.INVISIBLE
        //agent_update_order_btn.setOnClickListener { MoveToAnotherComponent.moveToAgentUpdateOrderSummaryActivity(this) }
        //payment_view_history.setOnClickListener { MoveToAnotherComponent.moveToPaymentHistoryActivity(this) }
        //tv_pay_now.setOnClickListener { MoveToAnotherComponent.moveToPaymentActivity(this) }
        order_rate_review.setOnClickListener {
            feedbackReqModel.customer_id = orderRes.customer_detail.id
            feedbackReqModel.agent_id = orderRes.agent_detail.id
            feedbackReqModel.order_id=orderRes.order_id
            rateAndReviews(feedbackReqModel)
        }
    }


    override fun <T> onSuccessApiResult(data: T) {
        super.onSuccessApiResult(data)
        orderStatus(orderRes)

    }
    private fun orderStatus(orderRes: CustomerOrderDetailsResModel) {

        val customerDetail = orderRes.customer_detail
        val agentDetail = orderRes.agent_detail
        if (orderRes.order_status == Constant.COMPLETED) {
            //order_rate_review.visibility=View.VISIBLE
            payment_view_history.visibility=View.INVISIBLE

            if (agentDetail != null) {
                merchant_user1_view.visibility = View.VISIBLE
                tv_user_name.text = agentDetail.full_name
                tv_user_tag.text = Constant.AGENT
                phone_view.visibility = View.GONE
                user_rating.visibility= View.VISIBLE
                user_rating.setOnClickListener {
                    Common.phoneCallWithNumber(agentDetail.mobile_number, this)
                }

            }
            else{
                merchant_user1_view.visibility=View.INVISIBLE
            }
            if (customerDetail != null) {

                merchant_user2_view.visibility= View.VISIBLE
                tv_users_name.text=customerDetail.full_name
                tv_users_tag.text=Constant.CUSTOMER
                phone_view.visibility=View.GONE
                user_rating.visibility=View.VISIBLE
            } else {
                merchant_user2_view.visibility = View.INVISIBLE
            }
        } else if (orderRes.order_status != Constant.COMPLETED) {
            order_rate_review.visibility=View.INVISIBLE
            pending_amount.visibility = View.VISIBLE
            pending_amount.text = Constant.PENDING_AMOUNT + orderRes.pending_order_amount
            if (agentDetail != null) {
                merchant_user1_view.visibility = View.VISIBLE
                tv_user_name.text = agentDetail.full_name
                tv_user_tag.text = Constant.AGENT
                phone_view.visibility = View.VISIBLE
                phone_view.setOnClickListener {
                    Common.phoneCallWithNumber(agentDetail.mobile_number, this)}

            }
            else{
                merchant_user1_view.visibility=View.INVISIBLE
            }
            if (customerDetail != null) {

                merchant_user2_view.visibility = View.VISIBLE
                tv_users_name.text=customerDetail.full_name
                tv_users_tag.text=Constant.CUSTOMER
                phone_view.visibility=View.INVISIBLE
            } else {
                merchant_user2_view.visibility = View.INVISIBLE
            }

        }


    }

    fun rateAndReviews(feedbackReqModel: FeedbackReqModel){

        MoveToAnotherComponent.openActivityWithParcelableParam<RateAndReviewActivity, FeedbackReqModel>(this,Constant.INTENT_RATE_REVIEWS,feedbackReqModel)
    }

}