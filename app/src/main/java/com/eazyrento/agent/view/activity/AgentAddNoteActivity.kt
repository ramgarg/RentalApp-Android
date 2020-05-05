package com.eazyrento.agent.view.activity

import android.os.Bundle
import androidx.core.view.GravityCompat
import com.eazyrento.R
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_agent_add_note.*
import kotlinx.android.synthetic.main.activity_agent_add_note.drawer_layout_agent
import kotlinx.android.synthetic.main.toolbar.*


class AgentAddNoteActivity: BaseActivity(){

    override fun <T> moveOnSelecetedItem(type: T) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_add_note)

        //tool bar menu click lisner , open drawer
//        img_menu.setOnClickListener {  drawer_layout_agent.penDrawer(GravityCompat.START) }

        agent_add_note_btn.setOnClickListener { MoveToAnotherComponent.moveToWriteNotesActivity(this) }

        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,toolbar_title,
            "Notes")
        img_notification.setOnClickListener { MoveToAnotherComponent.moveToNotificationActivity(this) }

    }
}