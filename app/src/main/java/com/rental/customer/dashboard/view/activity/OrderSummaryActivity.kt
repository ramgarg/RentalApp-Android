package com.rental.customer.dashboard.view.activity

import android.os.Bundle
import com.rental.R
import com.rental.customer.utils.MoveToActivity
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.order_summary_activity.*
import kotlinx.android.synthetic.main.toolbar.*


class OrderSummaryActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.order_summary_activity)

        tv_view_history.setOnClickListener { MoveToActivity.moveToPaymentHistoryActivity(this) }

        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,
            toolbar_title,"Order Summary")
    }
}