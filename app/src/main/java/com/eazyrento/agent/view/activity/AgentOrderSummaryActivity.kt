package com.eazyrento.agent.view.activity

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.agent.model.modelclass.AgentFeedbackReqModel
import com.eazyrento.common.view.MaintanceUserRoleView
import com.eazyrento.common.view.OrderBaseSummaryActivity
import com.eazyrento.customer.payment.view.PaymentHistoryActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.activity_base_order_summary.*
import kotlinx.android.synthetic.main.adapter_users_order_summary.*
import kotlinx.android.synthetic.main.maintance_layout.*
import kotlinx.android.synthetic.main.phone_view.*
import kotlinx.android.synthetic.main.template_order_summery_top_view.*

open class AgentOrderSummaryActivity : OrderBaseSummaryActivity() {

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        agent_asign_merchant_and_request_payment.visibility = View.VISIBLE
        payment_view_history.visibility=View.VISIBLE
        agent_update_order_btn.visibility=View.VISIBLE
        customer_payment_button.visibility = View.GONE

        val booking_id = intent.extras?.getInt(Constant.ORDER_SUMMERY_KEY, -1)

        // order details
        if (booking_id != -1)
            setDataAndCallOrderDetailsAPI(booking_id!!)
        clickListenerOnViews()
    }

    private fun clickListenerOnViews() {

        agent_asign_merchant_and_request_payment.setOnClickListener {
            MoveToAnotherComponent.moveToActivityWithIntentValue<AgentPaymentActivity>(this,Constant.KEY_ORDER_DETAILS_ID,
                intent.extras?.getInt(Constant.ORDER_SUMMERY_KEY, -1)!!)
        }

        payment_view_history.setOnClickListener {
            MoveToAnotherComponent.moveToActivityWithIntentValue<PaymentHistoryActivity>(this,
                Constant.KEY_PAYMENT_HISTORY,orderRes.order_id)
        }
        agent_update_order_btn.setOnClickListener {

           /* MoveToAnotherComponent.startActivityForResult<AgentUpdateOrderActivity>(
                this,Constant.REQUEST_CODE_FINISH_ORDER_DETAILS_ON_BACK,Constant.KEY_ORDER_DETAILS_ID,
                intent.extras?.getInt(Constant.ORDER_SUMMERY_KEY, -1)!!
            )*/
            MoveToAnotherComponent.moveToActivityWithIntentValue<AgentUpdateOrderActivity>(this,Constant.KEY_ORDER_DETAILS_ID,
                intent.extras?.getInt(Constant.ORDER_SUMMERY_KEY, -1)!!)
        }

    }

    override fun <T> onSuccessApiResult(data: T) {
        super.onSuccessApiResult(data)

        setMaintanceUserRoleAdapter(orderRes.customer_detail,null,orderRes.merchant_detail)

        }

    /*private fun orderStatus(orderRes: OrderDetailsResModel) {
        val merchantDetail= orderRes.merchant_detail
        val customerDetail = orderRes.customer_detail
        if (orderRes.order_status == Constant.COMPLETED) {
            agent_update_order_btn.visibility=View.GONE
            agent_asign_merchant_btn.visibility=View.GONE

            if (customerDetail != null) {
                rec_user_order_summary.visibility = View.VISIBLE
                tv_users_name.text = customerDetail.full_name
                tv_users_tag.text = Constant.CUSTOMER
                phone_view.visibility=View.GONE
                user_rating.visibility=View.VISIBLE
                phone_view.setOnClickListener {
                    Common.phoneCallWithNumber(customerDetail.mobile_number, this)
                }

            }
            if (merchantDetail != null) {
                rec_user_order_summary.visibility = View.VISIBLE
                phone_view.visibility = View.GONE
                user_rating.visibility=View.VISIBLE
                setUsersAdapter(orderRes)
            } else {
                rec_user_order_summary.visibility = View.INVISIBLE
            }
        }
        else if(orderRes.order_status!= Constant.COMPLETED) {
            pending_amount.visibility = View.VISIBLE
            pending_amount.text = Constant.PENDING_AMOUNT + orderRes.pending_order_amount
            if (customerDetail != null) {
                agent_customer_view.visibility = View.VISIBLE
                tv_users_name.text = customerDetail.full_name
                tv_users_tag.text = Constant.CUSTOMER
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


    }*/

    /*private fun setUsersAdapter(customerOderDetailsResponse: OrderDetailsResModel) {
        recyle_merchant_list_maintance.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false
        )
        (recyle_merchant_list_maintance.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
            1,
            1
        )

        val recycleAdapterUsersHomeCard =
            AgentOrderSummaryUsersAdapter(
                customerOderDetailsResponse.merchant_detail as MutableList<MerchantDetail>,this)

        recyle_merchant_list_maintance.adapter = recycleAdapterUsersHomeCard
    }*/

   /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.REQUEST_CODE_FINISH_ORDER_DETAILS_ON_BACK){

            rec_user_order_summary.adapter?.notifyDataSetChanged()
        }
    }*/

}
