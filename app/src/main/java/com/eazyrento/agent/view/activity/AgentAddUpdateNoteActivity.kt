package com.eazyrento.agent.view.activity

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.agent.model.modelclass.AgentAddNoteReqModelItem
import com.eazyrento.agent.model.modelclass.AgentNotesListResModelItem
import com.eazyrento.agent.viewmodel.AgentCreateNotesViewModel
import com.eazyrento.agent.viewmodel.AgentUpdateNoteViewModel
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.activity_agent_write_note.*

class AgentAddUpdateNoteActivity : BaseActivity() {

    var edit_Note_ID = -1

    override fun <T> moveOnSelecetedItem(type: T) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_write_note)

        var obj = intent.getParcelableExtra<AgentNotesListResModelItem>(Constant.INTENT_NOTE_EDIT)

        if (obj != null) {
            topBarWithBackIconAndTitle(getString(R.string.update_note))
            edit_Note_ID = obj.id
            ed_agent_note_heading.setText(obj.header)
            ed_agent_note_desc.setText(obj.description)

        } else {
            topBarWithBackIconAndTitle(getString(R.string.add_a_note))
        }


        if (edit_Note_ID > -1) {
            agent_submit_note_btn.visibility = View.INVISIBLE
            agent_update_note_btn.visibility = View.VISIBLE

        } else {
            agent_submit_note_btn.visibility = View.VISIBLE
            agent_update_note_btn.visibility = View.INVISIBLE
        }

    }

    fun onEdit(view: View) {

        if (checkValidation()) {
            callAPI()?.let {
                it.observeApiResult(
                    it.callAPIActivity<AgentUpdateNoteViewModel>(this)
                        .updateNoteAPI(
                            edit_Note_ID,
                            AgentAddNoteReqModelItem(
                                ed_agent_note_heading.text.toString(),
                                ed_agent_note_desc.text.toString()
                            )
                        ), this, this
                )
            }
        }
    }


    fun onSubmit(view: View) {

        if (checkValidation()) {

            callAPI()?.let {
                it.observeApiResult(
                    it.callAPIActivity<AgentCreateNotesViewModel>(this)
                        .createNote(
                            AgentAddNoteReqModelItem(
                                ed_agent_note_heading.text.toString(),
                                ed_agent_note_desc.text.toString()
                            )
                        )
                    , this, this
                )
            }
        }
    }


    private fun checkValidation(): Boolean {

        if (ed_agent_note_heading.text.toString().isEmpty() || ed_agent_note_desc.text.toString()
                .isEmpty()
        ) {
            showToast(ValidationMessage.ENTER_ALL_FIELDS)
            return false
        }
        return true
    }

    override fun <T> onSuccessApiResult(data: T) {

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        val agentAddNoteReqModelItem = data as AgentNotesListResModelItem

        // on update note
        var flag = Constant.INTENT_NOTE_ADDED

        if (edit_Note_ID!=-1){

            flag = Constant.INTENT_NOTE_UPDATED
        }
        MoveToAnotherComponent.openActivityWithParcelableParam<AgentAddNoteListActivity, AgentNotesListResModelItem>(
            this,
            flag,
            agentAddNoteReqModelItem
        )

    }

}