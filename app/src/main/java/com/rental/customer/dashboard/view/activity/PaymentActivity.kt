package com.rental.customer.dashboard.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rental.R
import com.rental.customer.utils.Common
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.toolbar.*

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_payment)

        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,
            toolbar_title,getString(R.string.payment))

        button_submit.setOnClickListener {
         Common.showDialog(getString(R.string.payment),this,R.layout.thank_you_pop)
        }

        img_cash.setOnClickListener {
            check_cash.visibility=View.VISIBLE
            check_paypal.visibility=View.GONE
        }

        img_paypal.setOnClickListener {
            check_cash.visibility=View.GONE
            check_paypal.visibility=View.VISIBLE
        }

    }

}