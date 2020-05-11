package com.eazyrento.agent.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.agent.model.modelclass.AgentNotesListResModel
import com.eazyrento.agent.model.modelclass.AgentNotesListResModelItem
import com.eazyrento.agent.view.adapter.AgentNotesListAdapter
import com.eazyrento.agent.viewmodel.AgentDeleteNoteViewModel
import com.eazyrento.agent.viewmodel.AgentNotesListViewModel
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.common.view.BaseActivity

import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_agent_add_note.*



class AgentAddNoteActivity: BaseActivity(){

     private var noteList:AgentNotesListResModel?=null
     private var deleteItemPos:Int =-1

    override fun <T> moveOnSelecetedItem(type: T) {
        MoveToAnotherComponent.openActivityWithParcelableParam<AgentWriteNoteActivity,AgentNotesListResModelItem>(this,Constant.INTENT_NOTE_EDIT,type as AgentNotesListResModelItem)
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

    override fun <T> statusCodeOfApi(data: T) {
        // delete cash
        val data  = data as DataWrapper<T>

        if (data.statusCode ==204 ) {
                if (deleteItemPos!=-1) {
                    noteList?.removeAt(deleteItemPos)
                    rec_note_list.adapter?.notifyDataSetChanged()
                }
        }

    }

    override fun <T> onSuccessApiResult(data: T) {

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,noteList.toString())

        if (data is JsonElement){
            return
        }
         noteList = data as AgentNotesListResModel

        setNotesAdapter(noteList!!)

    }

    private fun setNotesAdapter(notesListResModel: AgentNotesListResModel) {
        rec_note_list.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false
        )

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

    fun onDelete(pos: Int){
        deleteItemPos = pos

       callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<AgentDeleteNoteViewModel>(this)
                    .deleteNoteAPI(noteList!!.get(pos).id),this,this)
        }
    }
}