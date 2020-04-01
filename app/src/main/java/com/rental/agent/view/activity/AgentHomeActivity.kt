package com.rental.agent.view.activity

import android.os.Bundle
import androidx.core.view.GravityCompat
import com.rental.R
import com.rental.agent.view.AgentBaseActivity
import kotlinx.android.synthetic.main.activity_agent_home_.*
import kotlinx.android.synthetic.main.activity_agent_home_.navigationView
import kotlinx.android.synthetic.main.toolbar.*

class AgentHomeActivity : AgentBaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_agent_home_)

        // set listener bottom navigation view
        bottom_navigation_view_agent.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // set listener drawer
        navigationView.setNavigationItemSelectedListener(this)
        //tool bar menu click lisner , open drawer
        img_menu.setOnClickListener {  drawer_layout_agent.openDrawer(GravityCompat.START) }

        // select home fragment
        bottom_navigation_view_agent.selectedItemId = R.id.navigation_home


    }

}