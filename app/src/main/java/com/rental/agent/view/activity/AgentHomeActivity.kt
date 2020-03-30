package com.rental.agent.view.activity

import android.os.Bundle
import com.rental.R
import com.rental.agent.AgentBaseActivity
import com.rental.agent.view.fragment.AgentHomeFragment
import kotlinx.android.synthetic.main.activity_agent_home_.*

class AgentHomeActivity :AgentBaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_agent_home_)

        // adding bottom navigation view
        bottom_navigation_view_agent.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        bottom_navigation_view_agent.selectedItemId = R.id.navigation_home

//        setDefaultFragment()

    }


    private fun setDefaultFragment(){
        val fragment = AgentHomeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
            .commit()
    }
}