package com.rental.customer.dashboard.view.activity

import android.os.Bundle
import com.rental.R
import com.rental.customer.utils.MoveToActivity
import com.rental.customer.utils.Common
import com.rental.customer.utils.Common.Companion.hideGroupViews
import com.rental.customer.utils.Common.Companion.invisibleGroupViews
import com.rental.customer.utils.Common.Companion.showGroupViews
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.order_summary_activity.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class OrderSummaryActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.order_summary_activity)

        tv_view_history.setOnClickListener { MoveToActivity.moveToPaymentHistoryActivity(this) }

        ViewVisibility.isVisibleOrNot(
            this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.order_summary)
        )
        tv_pay_now.setOnClickListener {
            MoveToActivity.moveToPaymentActivity(
                this
            )
        }

        layout_st_date.setOnClickListener {
            Common.dateSelector(this, tv_st_date_sel)
        }

        layout_st_time.setOnClickListener {
            Common.timeSelector(this, tv_st_time_sel)
        }

        layout_end_date.setOnClickListener {
            Common.dateSelector(this, tv_end_date_sel)
        }

        layout_end_time.setOnClickListener {
            Common.timeSelector(this, tv_end_time_sel)
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun messageReceive(customEvent: String?) {
        if (customEvent.equals("OPEN_ACTIVE")) {
            hideGroupViews(tv_order_id,tv_delver_date,tv_days,tv_coupon,coupon_applied,
                tv_happy,tv_happy_with, tv_give_tip)

            showGroupViews(tv_pending_amount,tv_pay_now)

        } else {
            invisibleGroupViews(tv_pending_amount,tv_pay_now)
            showGroupViews(tv_order_id,tv_delver_date,tv_days,tv_coupon,coupon_applied,tv_happy,tv_happy_with,
                tv_give_tip)
            hideGroupViews(layout_driver)

            tv_give_tip.setOnClickListener {
                MoveToActivity.moveToPaymentActivity(this)
            }
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