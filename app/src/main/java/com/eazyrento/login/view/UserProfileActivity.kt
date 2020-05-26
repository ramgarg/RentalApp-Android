package com.eazyrento.login.view

import android.os.Bundle
import com.eazyrento.R
import com.eazyrento.common.view.BaseActivity

class UserProfileActivity:BaseActivity() {
    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        topBarWithBackIconAndTitle(resources.getString(R.string.profile))
        ProfileData().setData(this)

    }
}