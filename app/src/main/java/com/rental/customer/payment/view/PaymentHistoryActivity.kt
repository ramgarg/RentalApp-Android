package com.rental.customer.payment.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rental.R
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.toolbar.*

class PaymentHistoryActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_history)

        ViewVisibility.isVisibleOrNot(img_back,img_menu,img_notification,toolbar_title,"Payment History")
    }
}