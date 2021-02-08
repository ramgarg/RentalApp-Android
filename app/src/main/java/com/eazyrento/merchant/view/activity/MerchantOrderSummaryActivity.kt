package com.eazyrento.merchant.view.activity

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.appbiz.amountWithCurrencyName
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.common.view.OrderBaseSummaryActivity
import com.eazyrento.customer.dashboard.model.modelclass.OrderDetailsResModel
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.activity_base_order_summary.*
import kotlinx.android.synthetic.main.template_order_summery_top_view.*

class MerchantOrderSummaryActivity : OrderBaseSummaryActivity() {

//    lateinit var orderSummaryViewModel: OrderSummaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // setContentView(R.layout.activity_merchant_order_summary)

        // order details
        setDataAndCallOrderDetailsAPI(intent.extras?.getInt(Constant.ORDER_SUMMERY_KEY)!!)

        customer_payment_button.visibility=View.INVISIBLE


        tv_assign_to_driver.setOnClickListener {
            // for testing
            orderRes.status = ""

            MoveToAnotherComponent.openActivityWithParcelableParam<DriverAvailabilityActivity, OrderDetailsResModel>(this,
                Constant.DRIVER_ASSIGN_ORDER_KEY,orderRes)


        }

        //clickListenerOnViews()

    }



    override fun <T> moveOnSelecetedItem(type: T) {
    }


    override fun <T> onSuccessApiResult(data: T) {
        super.onSuccessApiResult(data)

        if(orderRes.is_category_vehicle && orderRes.is_driver_assigned.not() && orderRes.product_detail?.with_driver==true){
            tv_assign_to_driver.visibility=View.VISIBLE
        }else{
            tv_assign_to_driver.visibility= View.GONE
        }

        try {
            val status=orderRes.order_status

            if (status.equals(Constant.PENDING) && orderRes.is_driver_assigned ) {
                // tracking
                tv_track.visibility = View.VISIBLE
                // assign driver if the order quantitiy is one
            }
        }catch (e:Exception){

        }

        setMaintanceUserRoleAdapter(orderRes.customer_detail,orderRes.agent_detail,null)

        //if()

        orderRes.merchant_order_detail?.let {


            tv_booking_price.text= amountWithCurrencyName( Common.roundOfDouble(it.booking_price))

            order_product_quantity.text=resources.getString(R.string.quantity).plus(it.merchant_quantity)

            pending_amount.visibility=View.GONE
        }

    }

    override fun <T> statusCodeOfApi(data: T) {
        super.statusCodeOfApi(data)
        val data  = data as DataWrapper<T>
        if (data.statusCode==404){
            finish()

        }
    }


    /*fun trackingClickListener(view: View){
        //MoveToAnotherComponent.moveToActivityNormal<TrackingMapActivity>(this)

      //  MoveToAnotherComponent.moveToActivityWithIntentValue<TrackingMapActivity>(this,Constant.KEY_ORDER_ORDER_ID,orderRes.order_id)

        sendParcelableOrderResponse()
    }*/


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