package com.rental.merchant.view.activity

import android.os.Bundle
import com.rental.R
import com.rental.customer.dashboard.viewmodel.OrderSummaryViewModel
import com.rental.customer.utils.Common
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.ViewVisibility
import com.rental.merchant.MerchantBaseActivity
import kotlinx.android.synthetic.main.merchant_order_summary.*
import kotlinx.android.synthetic.main.order_summary_activity.*
import kotlinx.android.synthetic.main.order_summary_activity.tv_end_date_sel
import kotlinx.android.synthetic.main.order_summary_activity.tv_end_time_sel
import kotlinx.android.synthetic.main.order_summary_activity.tv_st_date_sel
import kotlinx.android.synthetic.main.order_summary_activity.tv_st_time_sel
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MerchantOrderSummaryActivity :MerchantBaseActivity() {

    lateinit var orderSummaryViewModel: OrderSummaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.merchant_order_summary)

//        orderSummaryViewModel=ViewModelProviders.of(this).get(OrderSummaryViewModel::class.java)
//        orderSummaryViewModel.getOrderSummaryResponse().observe(this, Observer {
//            //Here response will come form api
        setResponseViews()
//        })

        ViewVisibility.isVisibleOrNot(
            this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.order_summary)
        )

        clickListenerOnViews()

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

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
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

    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onPause() {
        super.onPause()
        EventBus.getDefault().unregister(this)
    }
}