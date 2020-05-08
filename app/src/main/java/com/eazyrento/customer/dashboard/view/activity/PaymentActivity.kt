package com.eazyrento.customer.dashboard.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eazyrento.R
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.Common.Companion.showToast
import com.eazyrento.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.toolbar.*

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_payment)

        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,
            toolbar_title,getString(R.string.payment))

        button_submit.setOnClickListener {
         Common.showDialog(getString(R.string.payment),getString(R.string.thank_you),this,R.layout.thank_you_pop)
            showToast(this,resources.getString(R.string.under_development))

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