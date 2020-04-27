package com.eazyrento.customer.profile

import android.os.Bundle
import android.view.View
import com.eazyrento.R
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.myaddress.view.adapter.MyAddressAdapter
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import com.eazyrento.login.model.modelclass.AddressInfo
import com.eazyrento.login.model.modelclass.UserProfile
import com.eazyrento.login.viewmodel.UpdateProfileUserViewModel
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.toolbar.*

class ProfileActivity : BaseActivity() {

    override fun <T> moveOnSelecetedItem(type: T) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,
            toolbar_title,"Profile")
        btn_save.setOnClickListener { onClickSaveButton() }
        btn_select_location.setOnClickListener { MoveToAnotherComponent.moveToListAddressActivity(this) }

    }

     fun onClickSaveButton(){

      //val userProfile = UserProfile()
      // updateProfileUser(userProfile)
    }


    /*
    * update profile
    * */
    fun updateProfileUser(userProfile: UserProfile){

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<UpdateProfileUserViewModel>(this)
                    .getProfileUser(userProfile)
                , this, this
            )
            userProfile.full_name=""+ed_user_name.text
            userProfile.dob=""+ed_dob.text
            userProfile.gender=""+sp_gender.selectedItemId
            userProfile.email=""+ed_email.text
            userProfile.description=""+ed_des.text
            userProfile.country_code=""+ed_country.text
            userProfile.buisness=""+ed_company_name.text
            userProfile.attached_document=""+sp_select_document.selectedItemId
            userProfile.address_info.address_line
            userProfile.address_info.address_type
            userProfile.address_info.city
            userProfile.address_info.country
            userProfile.id_proof_title=""+sp_select_document.selectedItem
            userProfile.mobile_number=""+ed_phone.text
            userProfile.profile_image=""+img_profile
            userProfile.username_choice=""+ed_user_name.text

        }

    }
}