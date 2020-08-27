package com.eazyrento.merchant.view.activity

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.common.view.OrderBaseSummaryActivity
import com.eazyrento.customer.dashboard.model.modelclass.BaseUserRoleDetail
import kotlinx.android.synthetic.main.template_order_summery_top_view.*

class MerchantOrderSummaryActivity : OrderBaseSummaryActivity() {

//    lateinit var orderSummaryViewModel: OrderSummaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // setContentView(R.layout.activity_merchant_order_summary)

        // order details
        setDataAndCallOrderDetailsAPI(intent.extras?.getInt(Constant.ORDER_SUMMERY_KEY)!!)
        customer_payment_button.visibility=View.INVISIBLE

        //clickListenerOnViews()

    }

    override fun <T> moveOnSelecetedItem(type: T) {
    }


    override fun <T> onSuccessApiResult(data: T) {
        super.onSuccessApiResult(data)

        setMaintanceUserRoleAdapter(orderRes.customer_detail,orderRes.agent_detail,null)

        orderRes.merchant_order_detail?.let {
            tv_booking_price.text= Constant.DOLLAR.plus(it.booking_price)

            order_product_quantity.text=resources.getString(R.string.quantity).plus(it.merchant_quantity)

            pending_amount.visibility=View.GONE
        }


        /*setMaintaniceUserRoleVisiblity(maintanceLayoutInflater(R.layout.adapter_users_order_summary),orderRes.agent_detail.let {  it.userRole =Constant.AGENT
        it
        })

        setMaintaniceUserRoleVisiblity(maintanceLayoutInflater(R.layout.adapter_users_order_summary_delete),orderRes.customer_detail.let {  it.userRole =Constant.CUSTOMER
            it
        })*/
        //setMaintaniceUserRoleVisiblity(setUserRoleFlag(orderRes.customer_detail,Constant.CUSTOMER,customer_maintance_view))

        /*setUserRoleDetailsForMaintance(MaintanceUserRoleView(img_user_pic,tv_user_name,tv_users_role,phone_view,user_rating),orderRes.customer_detail.let { it.userRole =Constant.CUSTOMER
            it})
        setUserRoleDetailsForMaintance(MaintanceUserRoleView(img_user_pic,tv_user_name,tv_users_role,phone_view,user_rating),orderRes.agent_detail.let { it.userRole =Constant.AGENT
            it})*/
        //orderStatus(orderRes)

    }


    /*private fun orderStatus(orderRes: OrderDetailsResModel) {

        val customerDetail = orderRes.customer_detail
        val agentDetail = orderRes.agent_detail
        if (orderRes.order_status == Constant.COMPLETED) {
            //order_rate_review.visibility=View.VISIBLE
            payment_view_history.visibility=View.INVISIBLE

            if (agentDetail != null) {
                merchant_user1_view.visibility = View.VISIBLE
                tv_users_name.text = agentDetail.full_name
                tv_users_tag.text = Constant.AGENT
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
                tv_users_name.text = agentDetail.full_name
                tv_users_tag.text = Constant.AGENT
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


    }*/

    /*fun rateAndReviews(feedbackReqModel: FeedbackReqModel){

        MoveToAnotherComponent.openActivityWithParcelableParam<RateAndReviewActivity, FeedbackReqModel>(this,Constant.INTENT_RATE_REVIEWS,feedbackReqModel)
    }*/

}