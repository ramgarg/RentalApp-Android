package com.rental.customer.notification.view

import android.os.Bundle
import com.rental.R
import com.rental.common.view.BaseActivity
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.toolbar.*

class NotificationActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,
            toolbar_title,"Notifications")
    }
}