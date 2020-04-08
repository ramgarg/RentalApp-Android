package com.rental.agent.view.activity

import android.os.Bundle
import androidx.core.view.GravityCompat
import com.rental.R
import com.rental.agent.view.AgentBaseActivity
import com.rental.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.activity_agent_home_.*
import kotlinx.android.synthetic.main.agent_header.view.*
import kotlinx.android.synthetic.main.toolbar.*

class AgentHomeActivity : AgentBaseActivity(){

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

        val header = agent_navigation_view.getHeaderView(0)

        header.agent_edit_profile_menu.setOnClickListener {
            MoveToAnotherComponent.moveToAgentProfileActivity(this)
            drawer_layout_agent.closeDrawer(GravityCompat.START)
        }
        img_notification.setOnClickListener { MoveToAnotherComponent.moveToNotificationActivity(this) }

        //btn_agent_home_view_all.setOnClickListener{ MoveToAnotherComponent.moveToAgentBookingsFragment(this)}


    }

}