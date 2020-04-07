package com.rental.customer.dashboard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rental.R
import com.rental.customer.dashboard.view.activity.CustomerMainActivity
import kotlinx.android.synthetic.main.activity_main.*

class SupportFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        (activity as CustomerMainActivity).layout_loading.visibility=View.GONE
        return view
    }

}
