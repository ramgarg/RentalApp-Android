package com.rental.merchant

import android.app.Activity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.merchant_activity_main.*

open class MerchantBaseFragment : Fragment() {
    fun setDrawerVisibility(visibility:Int,activity: Activity){
        activity.merchant_drawer_layout.visibility = visibility
    }
}