package com.eazyrento.customer.profile

import android.os.Bundle
import android.view.View
import com.eazyrento.R
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.utils.ViewVisibility
import com.eazyrento.login.model.modelclass.UserProfile
import com.eazyrento.login.viewmodel.UpdateProfileUserViewModel
import kotlinx.android.synthetic.main.toolbar.*

class ProfileActivity : BaseActivity() {

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