package com.rental.agent.view.activity

import android.os.Bundle
import androidx.core.view.GravityCompat
import com.rental.R
import com.rental.agent.view.AgentBaseActivity
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_agent_add_note.*
import kotlinx.android.synthetic.main.activity_agent_add_note.agent_navigation_view
import kotlinx.android.synthetic.main.activity_agent_add_note.drawer_layout_agent
import kotlinx.android.synthetic.main.toolbar.*


class AgentAddNoteActivity: AgentBaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_add_note)

        agent_navigation_view.setNavigationItemSelectedListener(this)
        //tool bar menu click lisner , open drawer
        img_menu.setOnClickListener {  drawer_layout_agent.openDrawer(GravityCompat.START) }

        agent_add_note_btn.setOnClickListener { MoveToAnotherComponent.moveToWriteNotesActivity(this) }

        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,toolbar_title,
            "Notes")
        img_notification.setOnClickListener { MoveToAnotherComponent.moveToNotificationActivity(this) }

    }
}