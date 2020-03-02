package com.rental.customer.dashboard.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rental.R
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.toolbar.*

class OrderReviewActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_order_review)

        ViewVisibility.isVisibleOrNot(
            this, img_back, img_menu, img_notification,
            toolbar_title, "Order Review")

    }
}