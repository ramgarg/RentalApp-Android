package com.eazyrento.agent.view.activity

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.agent.model.modelclass.AgentAddNoteReqModelItem
import com.eazyrento.agent.model.modelclass.AgentNotesListResModelItem
import com.eazyrento.agent.viewmodel.AgentCreateNotesViewModel
import com.eazyrento.agent.viewmodel.AgentDeleteNoteViewModel
import com.eazyrento.agent.viewmodel.AgentUpdateNoteViewModel
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import com.eazyrento.merchant.viewModel.MerchantDeleteProductViewModel
import com.eazyrento.merchant.viewModel.MerchantProductDetailViewModel
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_agent_write_note.*
import kotlinx.android.synthetic.main.add_new_address_activity.*
import kotlinx.android.synthetic.main.toolbar.*

class AgentWriteNoteActivity : BaseActivity(){


    val DEFAULT_VALUE= -1
    var edit_Note_ID=-1
    var delete_Note_ID=-1

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_write_note)

        topBarWithBackIconAndTitle(getString(R.string.add_a_note))

        edit_Note_ID = intent.getIntExtra(Constant.INTENT_NOTE_EDIT,DEFAULT_VALUE)
        delete_Note_ID=intent.getIntExtra(Constant.INTENT_NOTE_DELETE,DEFAULT_VALUE)

        if(edit_Note_ID>-1 || delete_Note_ID>-1)
        {
            agent_submit_note_btn.visibility=View.INVISIBLE
            btn_update_note.visibility=View.VISIBLE
            btn_delete_note.visibility=View.VISIBLE
        }else{
            agent_submit_note_btn.visibility=View.VISIBLE
            btn_update_note.visibility=View.INVISIBLE
            btn_delete_note.visibility=View.INVISIBLE

        }

    }

    fun onEdit(view: View){
        if(checkValidation()) {
            callAPI()?.let {
                it.observeApiResult(
                    it.callAPIActivity<AgentUpdateNoteViewModel>(this)
                        .updateNoteAPI(
                            edit_Note_ID,
                            AgentAddNoteReqModelItem(
                                ed_agent_note_heading.text.toString(),
                                ed_agent_note_desc.text.toString())), this, this)
            }
        }
    }

    fun onDelete(view: View){
            callAPI()?.let {
                it.observeApiResult(
                    it.callAPIActivity<AgentDeleteNoteViewModel>(this)
                        .deleteNoteAPI(delete_Note_ID),this,this)
            }
    }

    fun onSubmit(view:View){

        if (checkValidation()){

                 callAPI()?.let {
                    it.observeApiResult(
                        it.callAPIActivity<AgentCreateNotesViewModel>(this)
                            .createNote(AgentAddNoteReqModelItem(ed_agent_note_heading.text.toString(),ed_agent_note_desc.text.toString()))
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

        val agentAddNoteReqModelItem = data as AgentNotesListResModelItem

        MoveToAnotherComponent.openActivityWithParcelableParam<AgentAddNoteActivity,AgentNotesListResModelItem>(this,Constant.INTENT_NOTE_ADDED,agentAddNoteReqModelItem)

    }

}