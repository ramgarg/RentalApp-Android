package com.eazyrento.agent.view.activity

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.agent.model.modelclass.AgentAddNoteReqModelItem
import com.eazyrento.agent.viewmodel.AgentCreateNotesViewModel
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_agent_write_note.*
import kotlinx.android.synthetic.main.toolbar.*

class AgentWriteNoteActivity : BaseActivity(){

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_write_note)

        topBarWithBackIconAndTitle(getString(R.string.add_a_note))

    }

    fun onSubmit(view:View){

        if (checkValidation()){

            AgentAddNoteReqModelItem(-1,ed_agent_note_heading.text.toString(),ed_agent_note_desc.text.toString())
                /*agentAddNoteReqModelItem.header = ed_agent_note_heading.text.toString()
                agentAddNoteReqModelItem.description = ed_agent_note_desc.text.toString()*/

                 callAPI()?.let {
                    it.observeApiResult(
                        it.callAPIActivity<AgentCreateNotesViewModel>(this)
                            .createNote(AgentAddNoteReqModelItem(-1,ed_agent_note_heading.text.toString(),ed_agent_note_desc.text.toString()))
                        , this, this
                    )
                }
        }
    }


    private fun checkValidation():Boolean {

        if (ed_agent_note_heading.text.toString().isEmpty() || ed_agent_note_desc.text.toString().isEmpty()) {
            showToast(ValidationMessage.ENTER_ALL_FIELDS)
            return false
        }
        return true
    }

    override fun <T> onSuccessApiResult(data: T) {

        val agentAddNoteReqModelItem = data as AgentAddNoteReqModelItem

        MoveToAnotherComponent.openActivityWithParcelableParam<AgentAddNoteActivity,AgentAddNoteReqModelItem>(this,Constant.INTENT_NOTE_ADDED,agentAddNoteReqModelItem)

    }

}