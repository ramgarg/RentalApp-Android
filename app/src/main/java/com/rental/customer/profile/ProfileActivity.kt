package com.rental.customer.profile

import android.os.Bundle
import android.view.View
import com.rental.R
import com.rental.common.view.BaseActivity
import com.rental.customer.utils.ViewVisibility
import com.rental.login.model.modelclass.ProfileModelReqRes
import com.rental.login.model.modelclass.UserProfile
import com.rental.login.viewmodel.UpdateProfileUserViewModel
import kotlinx.android.synthetic.main.activity_register_user.*
import kotlinx.android.synthetic.main.toolbar.*

class ProfileActivity :BaseActivity() {

    override fun <T> moveOnSelecetedItem(type: T) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,
            toolbar_title,"Profile")

    }

    public fun onClickSaveButton(view:View){
//        val userProfile = UserProfile()
//        updateProfileUser(userProfile)
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
        }
    }
}