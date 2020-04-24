package com.eazyrento.customer.dashboard.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.eazyrento.R
import com.eazyrento.customer.dashboard.view.adapter.VehicleSpinnerAdapter
import com.eazyrento.customer.payment.viewmodel.PaymentHistoryViewModel
import com.eazyrento.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_notify_admin.*
import kotlinx.android.synthetic.main.toolbar.*

class NotifyToAdminActivity :AppCompatActivity(){

    private lateinit var paymentHistoryViewModel: PaymentHistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify_admin)

        ViewVisibility.isVisibleOrNot(
            this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.notify_admin))

        paymentHistoryViewModel= ViewModelProviders.of(this).get(PaymentHistoryViewModel::class.java)
        paymentHistoryViewModel.getPaymentHistoryResponse().observe(this, Observer {
            vehicle_spinner.adapter=
                VehicleSpinnerAdapter(
                    this,
                    it.data
                )
        })
    }
}