package com.eazyrento.agent.view.activity

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.agent.viewmodel.AgentBookingDetailsViewModel
import com.eazyrento.common.model.modelclass.BookingListItem
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_base_order_summary.*
import kotlinx.android.synthetic.main.template_order_summery_top_view.*
import kotlinx.android.synthetic.main.toolbar.*

class AgentBookingReviewSummeryActivity:AgentOrderSummaryActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

       val bookingITem = intent.getParcelableExtra<BookingListItem>(Constant.BOOKING_SUMMERY_KEY)

        /*ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,
            toolbar_title,getString(R.string.booking_details))*/

        topBarWithBackIconAndTitle(getString(R.string.booking_details))

        agent_asign_merchant_and_request_payment.text = resources.getString(R.string.assign_merchant)

        customer_payment_button.visibility = View.INVISIBLE
        payment_view_history.visibility = View.INVISIBLE
        agent_update_order_btn.visibility=View.INVISIBLE
        pending_amount.visibility=View.INVISIBLE

        agent_asign_merchant_and_request_payment.setOnClickListener {

            if (isNearByDriverShow()){
                MoveToAnotherComponent.
                openActivityWithParcelableParam<AgentNearByDriversMapActivity,BookingListItem>(this,
                    Constant.BOOKING_SUMMERY_KEY,bookingITem)
            }else {
                MoveToAnotherComponent.openActivityWithParcelableParam<AgentFinalAsignMerchantActivity, BookingListItem>(
                    this,
                    Constant.BOOKING_SUMMERY_KEY,
                    bookingITem
                )
            } }


        //bookinjg details API call

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<AgentBookingDetailsViewModel>(this)
                    .agentBookingDetails(bookingITem.id)
                , this, this
            )
        }



    }

    private fun isNearByDriverShow():Boolean{
        if (orderRes.product_detail?.with_driver==true &&  orderRes.product_detail?.quantity==1 && orderRes.is_category_vehicle ){
            return true
        }
        return false
    }

    override fun <T> onSuccessApiResult(data: T) {
        super.onSuccessApiResult(data)

        tv_booking_price.text= Constant.DOLLAR.plus(orderRes.product_detail?.starting_price)

        if (isNearByDriverShow()){
            agent_asign_merchant_and_request_payment.text = resources.getString(R.string.check_near_driver)
        }


    }

}