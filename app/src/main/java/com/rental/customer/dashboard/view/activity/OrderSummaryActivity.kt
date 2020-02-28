package com.rental.customer.dashboard.view.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import com.rental.R
import com.rental.customer.utils.MoveToActivity
import com.rental.customer.utils.TimeDateSelector
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.order_summary_activity.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*


class OrderSummaryActivity : BaseActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.order_summary_activity)

        tv_view_history.setOnClickListener { MoveToActivity.moveToPaymentHistoryActivity(this) }

        ViewVisibility.isVisibleOrNot(
            this, img_back, img_menu, img_notification,
            toolbar_title, "Order Summary"
        )
        tv_pay_now.setOnClickListener {
            MoveToActivity.moveToPaymentActivity(
                this
            )
        }

        layout_st_date.setOnClickListener {
            TimeDateSelector.dateSelector(this, tv_st_date_sel)
        }

        layout_st_time.setOnClickListener {
            TimeDateSelector.timeSelector(this, tv_st_time_sel)
        }

        layout_end_date.setOnClickListener {
            TimeDateSelector.dateSelector(this,tv_end_date_sel)
        }

        layout_end_time.setOnClickListener {
            TimeDateSelector.timeSelector(this,tv_end_time_sel)
        }


    }

}