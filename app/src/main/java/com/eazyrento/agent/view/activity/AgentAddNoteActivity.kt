package com.eazyrento.agent.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.agent.model.modelclass.AgentAddNoteReqModelItem
import com.eazyrento.agent.model.modelclass.AgentNotesListResModel
import com.eazyrento.agent.model.modelclass.AgentNotesListResModelItem
import com.eazyrento.agent.view.adapter.AgentNotesListAdapter
import com.eazyrento.agent.view.adapter.AgentOrderSummaryUsersAdapter
import com.eazyrento.agent.viewmodel.AgentCreateNotesViewModel
import com.eazyrento.agent.viewmodel.AgentNotesListViewModel
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderDetailsResModel
import com.eazyrento.customer.dashboard.model.modelclass.MerchantDetail
import com.eazyrento.customer.myaddress.model.modelclass.AddressListResModel
import com.eazyrento.customer.myaddress.view.adapter.MyAddressAdapter
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_agent_add_note.*
import kotlinx.android.synthetic.main.activity_agent_add_note.drawer_layout_agent
import kotlinx.android.synthetic.main.activity_agent_order_summary.*
import kotlinx.android.synthetic.main.activity_my_address.*
import kotlinx.android.synthetic.main.toolbar.*


class AgentAddNoteActivity: BaseActivity(){

     var noteList:AgentNotesListResModel?=null

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

         noteList = data as AgentNotesListResModel

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,noteList.toString())
        setNotesAdapter(noteList!!)

        //set adapter here........
    }

    private fun setNotesAdapter(notesListResModel: AgentNotesListResModel) {
        rec_note_list.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false
        )
      /*  (rec_note_list.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
            1,
            1
        )*/

        val recycleAdapterNoteCard =
            AgentNotesListAdapter(notesListResModel,this)

        rec_note_list.adapter = recycleAdapterNoteCard
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val addedNote = intent?.getParcelableExtra<AgentNotesListResModelItem>(Constant.INTENT_NOTE_ADDED)
        if(noteList==null) {
            noteList = AgentNotesListResModel()
            setNotesAdapter(noteList!!)
        }
        noteList!!.add(0,addedNote!!)
        rec_note_list.adapter?.notifyDataSetChanged()

    }
}