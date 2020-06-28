package com.eazyrento.merchant.view.activity

import android.os.Bundle
import android.view.View
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.customer.profile.UpdateProfileActivity
import kotlinx.android.synthetic.main.activity_profile.*

class MerchantProfileUpdateActivity : UpdateProfileActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout_des.visibility = View.GONE
        layout_company.hint = getString(R.string.business_name)

    }

    override fun isProfileValidationCorrect():Boolean{
        if(super.isProfileValidationCorrect().not())
            return false
        when{
          ed_company_name.text.toString().isEmpty()->showToast(ValidationMessage.BUSINESS)
          sp_select_document.selectedItemPosition == 0 ->showToast(ValidationMessage.DOCUMENT)

            else ->return true
        }

        return false
    }

    override fun updateProfileData(){
        super.updateProfileData()

        userProfile?.let {

            it.description = null

            it.dob = "" + ed_dob.tag

            it.attached_document = "" + selectBase64StringAttachedDoc

            selectBase64StringAttachedDoc?.let { inner ->
                it.attached_document = inner
                it.id_proof_title = selectProfID!!
            }
        }
    }
}