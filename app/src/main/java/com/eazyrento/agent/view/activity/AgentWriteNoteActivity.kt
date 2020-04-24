package com.eazyrento.agent.view.activity

import android.os.Bundle
import android.widget.Toast
import com.eazyrento.R
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_agent_write_note.*
import kotlinx.android.synthetic.main.toolbar.*

class AgentWriteNoteActivity : BaseActivity(){
    override fun <T> moveOnSelecetedItem(type: T) {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_write_note)

        agent_submit_note_btn.setOnClickListener{
            Toast.makeText(this, getString(R.string.note_added), Toast.LENGTH_SHORT).show()
            MoveToAnotherComponent.moveToMyNotesActivity(this)}
        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,toolbar_title,
            "Add a Note")

    }
}