package com.rental.merchant

import android.app.Activity
import com.rental.common.view.fragment.BaseFragment
import kotlinx.android.synthetic.main.merchant_activity_main.*

open abstract class MerchantBaseFragment : BaseFragment() {
    fun setDrawerVisibility(visibility:Int,activity: Activity){
        activity.merchant_drawer_layout.visibility = visibility
    }
}