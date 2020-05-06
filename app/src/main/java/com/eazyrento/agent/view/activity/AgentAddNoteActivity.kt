package com.eazyrento.agent.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.core.view.GravityCompat
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.agent.model.modelclass.AgentAddNoteReqModelItem
import com.eazyrento.agent.model.modelclass.AgentNotesListResModel
import com.eazyrento.agent.viewmodel.AgentCreateNotesViewModel
import com.eazyrento.agent.viewmodel.AgentNotesListViewModel
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_agent_add_note.*
import kotlinx.android.synthetic.main.activity_agent_add_note.drawer_layout_agent
import kotlinx.android.synthetic.main.toolbar.*


class AgentAddNoteActivity: BaseActivity(){

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_add_note)

        topBarWithBackIconAndTitle(getString(R.string.mynotes))

        agent_add_note_btn.setOnClickListener { MoveToAnotherComponent.moveToActivityNormal<AgentWriteNoteActivity>(this) }

        fetchMyNotes()

    }

    private fun fetchMyNotes() {
        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<AgentNotesListViewModel>(this)
                    .getNotesList()
                , this, this
            )
        }
    }

    override fun <T> onSuccessApiResult(data: T) {

        val noteList = data as AgentNotesListResModel

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,noteList.toString())


        //set adapter here........
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val addedNote = intent?.getParcelableExtra<AgentAddNoteReqModelItem>(Constant.INTENT_NOTE_ADDED)

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,addedNote.toString())

    }
}