package com.rental.merchant.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rental.R
import com.rental.customer.dashboard.view.activity.MainActivity
import com.rental.merchant.view.activity.Merchant_MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.merchant_activity_main.*

class Merchant_Support_Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.merchant_help_support, container, false)
        (activity as Merchant_MainActivity).merchant_layout_loading.visibility= View.GONE
        return view
    }
}