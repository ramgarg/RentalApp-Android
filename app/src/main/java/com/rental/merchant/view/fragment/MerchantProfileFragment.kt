package com.rental.merchant.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rental.R
import com.rental.merchant.view.activity.MerchantMainActivity
import kotlinx.android.synthetic.main.merchant_activity_main.*

class MerchantProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.merchant_fragment_profile, container, false)
        (activity as MerchantMainActivity).merchant_layout_loading.visibility= View.GONE
        return view

    }
}