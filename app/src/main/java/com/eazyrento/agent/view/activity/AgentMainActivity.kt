package com.eazyrento.agent.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.core.view.GravityCompat
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.agent.view.AgentBaseActivity
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.activity_agent_home_.*
import kotlinx.android.synthetic.main.agent_header.view.*
import kotlinx.android.synthetic.main.booking_dashboard_adapter_view.*
import kotlinx.android.synthetic.main.toolbar.*

class AgentMainActivity : AgentBaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_home_)

        // set listener bottom navigation view
        bottom_navigation_view_agent.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // set listener drawer
        agent_navigation_view.setNavigationItemSelectedListener(this)
        //tool bar menu click lisner , open drawer
        img_menu.setOnClickListener {  drawer_layout_agent.openDrawer(GravityCompat.START) }

        // select home fragment
        bottom_navigation_view_agent.selectedItemId = R.id.agent_navigation_home

        //btn_home_view_all.setOnClickListener { MoveToAnotherComponent.moveToAgentBookingsFragment(this) }

        val header = agent_navigation_view.getHeaderView(0)

        header.agent_edit_profile_menu.setOnClickListener {
            MoveToAnotherComponent.moveToAgentProfileActivity(this)
            drawer_layout_agent.closeDrawer(GravityCompat.START)
        }
        img_notification.setOnClickListener { MoveToAnotherComponent.moveToNotificationActivity(this) }

        //btn_agent_home_view_all.setOnClickListener{ MoveToAnotherComponent.moveToAgentBookingsFragment(this)}


    }

    fun setHomeFragment(){
        bottom_navigation_view_agent.selectedItemId = R.id.agent_navigation_home
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val value = intent?.getIntExtra(Constant.INTENT_SUCCESS_ADDED_PRODUCT,-1)
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"onNewIntent "+value)

        // move to home fragemtn
        setHomeFragment()
    }

}