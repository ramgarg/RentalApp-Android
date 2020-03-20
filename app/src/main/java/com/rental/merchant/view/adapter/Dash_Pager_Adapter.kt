package com.rental.merchant.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rental.merchant.view.fragment.Merchant_Dash_Fragment

class Dash_Pager_Adapter (fm: FragmentManager, private val fragments: List<Fragment>) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return this.fragments[position]
    }

    override fun getCount(): Int {
        return this.fragments.size
}

}