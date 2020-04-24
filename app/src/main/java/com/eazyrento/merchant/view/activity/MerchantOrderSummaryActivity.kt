package com.eazyrento.merchant.view.activity

import android.os.Bundle
import android.view.View
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.OrderBaseSummaryActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.order_summary_template.*
import kotlinx.android.synthetic.main.template_order_summery_top_view.*

class MerchantOrderSummaryActivity : OrderBaseSummaryActivity() {

//    lateinit var orderSummaryViewModel: OrderSummaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_merchant_order_summary)
        customer_payment_button.visibility=View.INVISIBLE
       /* setResponseViews()

        ViewVisibility.isVisibleOrNot(
            this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.order_summary)
        )

        clickListenerOnViews()*/

    }

    override fun <T> moveOnSelecetedItem(type: T) {
        TODO("Not yet implemented")
    }

    private fun clickListenerOnViews(){
       // tv_view_history.setOnClickListener { MoveToAnotherComponent.moveToPaymentHistoryActivity(this) }
        //tv_pay_now.setOnClickListener { MoveToAnotherComponent.moveToPaymentActivity(this) }
        tv_merchant_rate_review.setOnClickListener { MoveToAnotherComponent.moveToOrderReviewActivity(this) }
    }

    private fun setResponseViews(){
        tv_st_date_sel.text="12 Jan 2020"
        tv_st_time_sel.text="4:00pm"
        tv_end_date_sel.text="12 Feb 2020"
        tv_end_time_sel.text="3:00pm"

    }

    override fun <T> onSuccessApiResult(data: T) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())
    }

   /* @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun messageReceive(customEvent: String?) {
        if (customEvent.equals("OPEN_ACTIVE")) {
            Common.hideGroupViews(
                tv_order_id, tv_delver_date, tv_days, tv_coupon, coupon_applied,
                tv_happy, tv_happy_with, tv_give_tip
            )

            Common.showGroupViews(tv_pending_amount, tv_pay_now)

        } else {
            Common.invisibleGroupViews(tv_pending_amount, tv_pay_now)
            Common.showGroupViews(
                tv_order_id,
                tv_delver_date,
                tv_days,
                tv_coupon,
                coupon_applied,
                tv_happy,
                tv_happy_with,
                tv_give_tip
            )
            Common.hideGroupViews(layout_driver)
        }

    }*/

    /*override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onPause() {
        super.onPause()
        EventBus.getDefault().unregister(this)
    }*/
}