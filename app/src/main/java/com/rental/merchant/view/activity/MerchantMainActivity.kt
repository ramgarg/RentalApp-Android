package com.rental.merchant.view.activity

import android.os.Bundle
import androidx.core.view.GravityCompat
import com.rental.R
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.merchant.MerchantBaseActivity
import kotlinx.android.synthetic.main.merchant_activity_main.*
import kotlinx.android.synthetic.main.merchant_header.view.*
import kotlinx.android.synthetic.main.toolbar.*

class MerchantMainActivity : MerchantBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.merchant_activity_main)

        bottom_navigation_view_merchant.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // set listener drawer
        merchant_navigationView.setNavigationItemSelectedListener(this)
        //tool bar menu click lisner , open drawer
        img_menu.setOnClickListener {  merchant_drawer_layout.openDrawer(GravityCompat.START) }

        // select home fragment
        bottom_navigation_view_merchant.selectedItemId = R.id.merchant_navigation_home

        merchant_add_vehicle_btn.setOnClickListener{ MoveToAnotherComponent.moveToMerchantAddVehicle(this) }

        val header = merchant_navigationView.getHeaderView(0)

        header.merchant_edit_profile_menu.setOnClickListener {
            MoveToAnotherComponent.moveToMerchantProfileActivity(this)
            merchant_drawer_layout.closeDrawer(GravityCompat.START)
        }
        img_notification.setOnClickListener { MoveToAnotherComponent.moveToNotificationActivity(this) }

        //btn_merchant_home_view_all.setOnClickListener{ MoveToAnotherComponent.moveToAgentBookingsFragment(this)}


    }

}