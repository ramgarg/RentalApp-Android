package com.rental.merchant.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rental.R
import com.rental.merchant.view.fragment.Merchant_Dash_Fragment
import kotlinx.android.synthetic.main.merchant_dash_activity.*
import java.util.ArrayList

class Merchant_Dash_Activity : AppCompatActivity() {

    private var fragments: List<Fragment> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.merchant_dash_activity)

    }
}