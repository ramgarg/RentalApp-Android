package com.rental.merchant.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rental.R

class Merchant_Dash_Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.merchant_fragment_dash, container, false)
        return view
    }



    companion object {
        val EXTRA_ITEM_INDEX = "EXTRA_ITEM_INDEX"

        fun newInstance(index: Int): Merchant_Dash_Fragment {
            val f = Merchant_Dash_Fragment()
            val bdl = Bundle()
            bdl.putInt(EXTRA_ITEM_INDEX, index)
            f.arguments = bdl

            return f
        }
    }
}