package com.rental.merchant.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rental.R
import com.rental.merchant.view.adapter.Dash_Pager_Adapter
import com.rental.merchant.view.fragment.Merchant_Dash_Fragment
import com.rental.merchant.view.fragment.Merchant_dash_pager_interface
import kotlinx.android.synthetic.main.merchant_dash_activity.*
import java.util.ArrayList

class Merchant_Dash_Activity : AppCompatActivity(), Merchant_dash_pager_interface {
    override fun goNext() {
        viewPager.currentItem = viewPager.currentItem + 1
    }

    override fun goBack() {
        viewPager.currentItem = viewPager.currentItem - 1
    }
    private lateinit var adapter: Dash_Pager_Adapter
    private var fragments: List<Fragment> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.merchant_dash_activity)

        fragments = getFragments()
        adapter = Dash_Pager_Adapter(supportFragmentManager, fragments)
        viewPager.adapter = adapter

        adapter.notifyDataSetChanged()
    }

    private fun getFragments(): List<Fragment> {
        val fList = ArrayList<Fragment>()
        fList.add(Merchant_Dash_Fragment.newInstance(1))
        fList.add(Merchant_Dash_Fragment.newInstance(2))
        fList.add(Merchant_Dash_Fragment.newInstance(3))
        fList.add(Merchant_Dash_Fragment.newInstance(4))
        return fList
    }
}