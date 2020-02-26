package com.rental.customer.payment.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rental.R
import com.rental.customer.dashboard.view.activity.BaseActivity
import com.rental.customer.dashboard.view.adapter.DashBoardAdapter
import com.rental.customer.dashboard.viewmodel.HomeViewModel
import com.rental.customer.payment.viewmodel.PaymentHistoryViewModel
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_payment_history.*
import kotlinx.android.synthetic.main.toolbar.*

class PaymentHistoryActivity :BaseActivity() {

    private lateinit var paymentHistoryViewModel:PaymentHistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_history)

        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,
            toolbar_title,"Payment History")
        paymentHistoryViewModel=ViewModelProviders.of(this).get(PaymentHistoryViewModel::class.java)
        paymentHistoryViewModel.getPaymentHistoryResponse().observe(this, Observer {
            rec_payment_history.adapter= PaymentHistoryAdapter(it.data,this)
        })


    }
}