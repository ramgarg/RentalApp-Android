package com.rental.agent.view.activity

import android.os.Bundle
import com.rental.R
import com.rental.customer.dashboard.view.activity.BaseActivity
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.toolbar.*

class AgentProfileActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_profile)

        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,
            toolbar_title,"Profile")
    }
}