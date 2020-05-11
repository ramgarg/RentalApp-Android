package com.eazyrento.agent.view.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.agent.model.modelclass.AgentFeedbackReqModel
import com.eazyrento.agent.view.adapter.AgentOrderSummaryUsersAdapter
import com.eazyrento.common.view.OrderBaseSummaryActivity
import com.eazyrento.customer.dashboard.model.modelclass.CustomerFeedbackRequestModel
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderDetailsResModel
import com.eazyrento.customer.dashboard.model.modelclass.MerchantDetail
import com.eazyrento.customer.dashboard.view.activity.CustomerFeedbackActivity
import com.eazyrento.customer.dashboard.view.adapter.CustomerOrderSummaryUsersAdapter
import com.eazyrento.customer.payment.view.PaymentHistoryActivity
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.activity_agent_order_summary.*
import kotlinx.android.synthetic.main.activity_agent_order_summary.rec_user_order_summary
import kotlinx.android.synthetic.main.activity_customer_order_summary.*
import kotlinx.android.synthetic.main.adapter_user_order_summery.*
import kotlinx.android.synthetic.main.adapter_users_order_summary.*
import kotlinx.android.synthetic.main.phone_view.*
import kotlinx.android.synthetic.main.template_order_summery_top_view.*

open class AgentOrderSummaryActivity : OrderBaseSummaryActivity() {

    var merchant_ID=-1
    var agentFeedbackReqModel = AgentFeedbackReqModel()

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_agent_order_summary)


        val booking_id = intent.extras?.getInt(Constant.ORDER_SUMMERY_KEY, -1)

        // order details
        if (booking_id != -1)
            setDataAndCallOrderDetailsAPI(booking_id!!)
        clickListenerOnViews()
    }

    private fun clickListenerOnViews() {
        customer_payment_button.visibility = View.INVISIBLE
        payment_view_history.visibility = View.VISIBLE
        per_hour.visibility = View.INVISIBLE
        agent_asign_merchant_btn.setOnClickListener { showToast(ValidationMessage.UNDER_DEVELOPMENT) }
        payment_view_history.setOnClickListener {
            MoveToAnotherComponent.moveToActivityNormal<PaymentHistoryActivity>(this)
        }
        agent_update_order_btn.setOnClickListener {
            MoveToAnotherComponent.moveToAgentUpdateOrderSummaryActivity(
                this
            )
        }
        order_rate_review.setOnClickListener {

            agentFeedbackReqModel.customer_id = orderRes.customer_detail.id
            agentFeedbackReqModel.merchant_id= merchant_ID
            agentFeedbackReqModel.order_id = orderRes.order_id
            rateAndReview(agentFeedbackReqModel)
        }
    }

    private fun rateAndReview(agentFeedbackReqModel: AgentFeedbackReqModel) {
        MoveToAnotherComponent.openActivityWithParcelableParam<AgentFeedbackActivity, AgentFeedbackReqModel>(this,Constant.INTENT_RATE_REVIEWS,agentFeedbackReqModel)

    }

    fun sendMerchantID(merchantId: Int) {
        merchant_ID=merchantId

    }

    override fun <T> onSuccessApiResult(data: T) {
        super.onSuccessApiResult(data)
        orderStatus(orderRes)
        }

    private fun orderStatus(orderRes: CustomerOrderDetailsResModel) {
        val merchantDetail= orderRes.merchant_detail
        val customerDetail = orderRes.customer_detail
        if (orderRes.order_status == Constant.COMPLETED) {
            agent_update_order_btn.visibility=View.INVISIBLE
            agent_asign_merchant_btn.visibility=View.INVISIBLE

            if (customerDetail != null) {
                agent_customer_view.visibility = View.VISIBLE
                tv_users_name.text = customerDetail.full_name
                tv_users_tag.text = customerDetail.mobile_number
                phone_view.visibility=View.VISIBLE
                phone_view.setOnClickListener {
                    Common.phoneCallWithNumber(customerDetail.mobile_number, this)
                }

            }
            if (merchantDetail != null) {
                rec_user_order_summary.visibility = View.VISIBLE
                phone_view.visibility = View.VISIBLE
                setUsersAdapter(orderRes)
            } else {
                rec_user_order_summary.visibility = View.INVISIBLE
            }
        }
        else if(orderRes.order_status!= Constant.COMPLETED) {
            pending_amount.visibility = View.VISIBLE
            pending_amount.text = Constant.PENDING_AMOUNT + "- " + orderRes.pending_order_amount
            if (customerDetail != null) {
                agent_customer_view.visibility = View.VISIBLE
                tv_users_name.text = customerDetail.full_name
                tv_users_tag.text = customerDetail.mobile_number
                phone_view.visibility = View.VISIBLE
                phone_view.setOnClickListener {
                    Common.phoneCallWithNumber(customerDetail.mobile_number, this)
                }

            }
            if (orderRes.merchant_detail != null) {
                if (orderRes.merchant_detail.isNotEmpty()) {
                    rec_user_order_summary.visibility = View.VISIBLE
                    phone_view?.visibility = View.VISIBLE
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
            AgentOrderSummaryUsersAdapter(
                customerOderDetailsResponse.merchant_detail as MutableList<MerchantDetail>,this)

        rec_user_order_summary.adapter = recycleAdapterUsersHomeCard
    }
    }
