package com.eazyrento.customer.dashboard.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.MaintanceUserRoleView
import com.eazyrento.common.view.OrderBaseSummaryActivity
import com.eazyrento.common.view.TipActivity
import com.eazyrento.customer.dashboard.model.modelclass.BaseUserRoleDetail
import com.eazyrento.customer.payment.view.PaymentHistoryActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.activity_base_order_summary.*
import kotlinx.android.synthetic.main.adapter_users_order_summary.*
import kotlinx.android.synthetic.main.maintance_layout.*
import kotlinx.android.synthetic.main.phone_view.*
import kotlinx.android.synthetic.main.template_order_summery_top_view.*
import kotlinx.android.synthetic.main.template_tip.*


class CustomerOrderSummaryActivity : OrderBaseSummaryActivity() {
    private var mBookingID:Int = -1

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_customer_order_summary)

        mBookingID = intent.extras?.getInt(Constant.ORDER_SUMMERY_KEY)!!
        setDataAndCallOrderDetailsAPI(mBookingID)

        clickListenerOnViews()
    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"onNewIntent")
        if (mBookingID != -1)
            setDataAndCallOrderDetailsAPI(mBookingID)
    }

    private fun clickListenerOnViews(){

        payment_view_history.setOnClickListener {

           MoveToAnotherComponent.moveToActivityWithIntentValue<PaymentHistoryActivity>(this,
               Constant.KEY_PAYMENT_HISTORY_CUSTOMER,orderRes.order_id)
       }
        /*order_rate_review.setOnClickListener {

            customerFeedbackReqModel.agent_id = orderRes.agent_detail.id
            customerFeedbackReqModel.merchant_id=merchant_ID
            customerFeedbackReqModel.order_id = orderRes.order_id
            rateAndReview(customerFeedbackReqModel)
        }*/
        customer_payment_button.setOnClickListener {

            MoveToAnotherComponent.moveToActivityWithIntentValue<CustomerPaymentActivity>(this,Constant.KEY_ORDER_DETAILS_ID,
                intent.extras?.getInt(Constant.ORDER_SUMMERY_KEY, -1)!!)

//            MoveToAnotherComponent.moveToActivityNormal<PaymentActivity>(this)
        }


    }

    override fun <T> onSuccessApiResult(data: T) {

        super.onSuccessApiResult(data)
        val status=orderRes.order_status

        setMaintanceUserRoleAdapter(null,orderRes.agent_detail,orderRes.merchant_detail)

        if(status==Constant.COMPLETED) {

            lyt_tip.visibility = View.VISIBLE
            btn_tip.setOnClickListener {
                MoveToAnotherComponent.moveToActivityWithIntentValue<TipActivity>(
                    this, Constant.KEY_ORDER_DETAILS_ID,
                    intent.extras?.getInt(Constant.ORDER_SUMMERY_KEY, -1)!!
                )
            }
        }

        //customer_maintance_view.visibility =View.GONE

        //setMaintaniceUserRoleVisiblity(setUserRoleFlag(orderRes.agent_detail,Constant.AGENT,agent_maintance_view))

       /* setMaintaniceUserRoleVisiblity(maintanceLayoutInflater(R.layout.adapter_users_order_summary),orderRes.agent_detail.let {  it.userRole =Constant.AGENT
            it
        })
*/
    }

     /*private fun orderStatus(orderRes: OrderDetailsResModel) {

         val merchantdetail=orderRes.merchant_detail
         val agentdetail=orderRes.agent_detail

        if(orderRes.order_status == Constant.COMPLETED)
        {
            if (agentdetail != null) {
                users_view.visibility = View.VISIBLE
                tv_users_name.text = agentdetail.full_name
                tv_users_tag.text = Constant.AGENT
                phone_view.visibility=View.GONE
                user_rating.visibility=View.VISIBLE
                user_rating.setOnClickListener {
                    Common.phoneCallWithNumber(agentdetail.mobile_number, this)
                }

            }
            if(merchantdetail != null) {
                if (merchantdetail.isNotEmpty()) {
                    rec_user_order_summary.visibility = View.VISIBLE
                    phone_view.visibility = View.GONE
                    user_rating.visibility=View.VISIBLE
                    setUsersAdapter(orderRes)
                } else {
                    rec_user_order_summary.visibility = View.INVISIBLE
                }
            }

        }
        else if(orderRes.order_status!= Constant.COMPLETED) {
            pending_amount.text= Constant.PENDING_AMOUNT+orderRes.pending_order_amount

            if (agentdetail != null) {
                users_view.visibility = View.VISIBLE
                tv_users_name.text = agentdetail.full_name
                tv_users_tag.text = Constant.AGENT
                user_rating.visibility=View.INVISIBLE
                phone_view.visibility=View.VISIBLE
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
    }*/


   /* private fun setUsersAdapter(customerOderDetailsResponse: OrderDetailsResModel) {
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
    }*/

    /*fun sendMerchantID(merchantId: Int) {
       merchant_ID=merchantId

    }*/
    /*fun rateAndReview(customerfeedbackReqModel: CustomerFeedbackRequestModel){

        MoveToAnotherComponent.openActivityWithParcelableParam<CustomerFeedbackActivity,CustomerFeedbackRequestModel>(this,Constant.INTENT_RATE_REVIEWS,customerfeedbackReqModel)
    }*/

}