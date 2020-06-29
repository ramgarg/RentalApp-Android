package com.eazyrento.customer.dashboard.view.activity

import android.os.Bundle
import android.view.View
import com.eazyrento.ValidationMessage
import com.eazyrento.customer.profile.UpdateProfileBase
import kotlinx.android.synthetic.main.activity_profile.*

class CustomerProfileUpdateActivity : UpdateProfileBase() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layout_dob.visibility = View.GONE
        lyt_select_document.visibility = View.GONE

    }
    override fun isProfileValidationCorrect():Boolean{
        if(super.isProfileValidationCorrect().not())
            return false
        when{
            ed_company_name.text.toString().isEmpty()->showToast(ValidationMessage.COMPANY)
            ed_des.text.toString().isEmpty()->showToast(ValidationMessage.DESCRIPTON)
            else -> return true
        }
        return false
    }

    override fun updateProfileData(){

        super.updateProfileData()

        userProfile?.let {
                it.dob = null
                it.attached_document = null
        }
    }
}