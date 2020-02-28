package com.rental.customer.dashboard.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rental.R
import com.rental.customer.utils.MoveToActivity
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_view_details.*
import kotlinx.android.synthetic.main.toolbar.*

class ViewDetailActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_view_details)

        ViewVisibility.isVisibleOrNot(
            this, img_back, img_menu, img_notification,
            toolbar_title, "Details")

        btn_next.setOnClickListener {
            MoveToActivity.moveToBookingDetailsActivity(this)
        }
    }
}