package com.eazyrento.agent.view.activity

import android.os.Bundle
import android.view.View
import com.eazyrento.ValidationMessage
import com.eazyrento.customer.profile.UpdateProfileActivity
import kotlinx.android.synthetic.main.activity_profile.*

class AgentProfileUpdateActivity:UpdateProfileActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lyt_select_document.visibility = View.GONE
        layout_company.visibility = View.GONE

    }

    override fun isProfileValidationCorrect():Boolean{
        if(super.isProfileValidationCorrect().not())
            return false
        when{
            ed_dob.text.toString().isEmpty()-> showToast(ValidationMessage.DATE_OF_BIRTH)
            ed_des.text.toString().isEmpty()->showToast(ValidationMessage.DESCRIPTON)
            else-> return true
        }
        return false
    }
    override fun updateProfileData(){
        super.updateProfileData()

        userProfile?.let {
            it.dob = "" + ed_dob.tag

            it.attached_document = null
            it.buisness = null

        }
    }
}