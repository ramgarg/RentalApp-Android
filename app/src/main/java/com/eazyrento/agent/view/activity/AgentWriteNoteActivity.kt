package com.eazyrento.agent.view.activity

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.agent.model.modelclass.AgentAddNoteReqModelItem
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_agent_write_note.*
import kotlinx.android.synthetic.main.toolbar.*

class AgentWriteNoteActivity : BaseActivity(){

    lateinit var agentAddNoteReqModelItem: AgentAddNoteReqModelItem
    override fun <T> moveOnSelecetedItem(type: T) {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_write_note)

        initialize()

        agent_submit_note_btn.setOnClickListener{
            Toast.makeText(this, getString(R.string.note_added), Toast.LENGTH_SHORT).show()
            MoveToAnotherComponent.moveToMyNotesActivity(this)}


    }
    private fun initialize(){
        agent_submit_note_btn.setOnClickListener { checkValidation(ed_agent_note_heading,ed_agent_note_desc) }
        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,toolbar_title,
            getString(R.string.add_a_note))
    }

    private fun checkValidation(editTextHeading: EditText,editTextDesc:EditText):Boolean {

        if (editTextHeading.text.toString().isEmpty()) {
//            otpView.showToast("Please Enter Valid OTP")
            Toast.makeText(this, ValidationMessage.VALID_HEADING, Toast.LENGTH_SHORT).show()
        } else if (editTextDesc.text.toString().isEmpty()) {
            Toast.makeText(this, ValidationMessage.VALID_HEADING, Toast.LENGTH_SHORT).show()
        } else {

            agentAddNoteReqModelItem.header = editTextHeading.text.toString()
            agentAddNoteReqModelItem.description = editTextDesc.text.toString()

            /* callAPI()?.let {
                it.observeApiResult(
                    it.callAPIActivity<AgentCreateNotesViewModel>(this)
                        .createNote(agentAddNoteReqModelItem)
                    , this, this
                )
            }
         */
        }
        return false
    }

}