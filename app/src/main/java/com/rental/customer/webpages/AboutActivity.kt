package com.rental.customer.webpages

import android.os.Bundle
import com.rental.R
import com.rental.common.view.BaseActivity
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.toolbar.*


class AboutActivity :BaseActivity() {
    override fun <T> moveOnSelecetedItem(type: T) {
    }


    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_about)

            MoveToAnotherComponent.openWebPage(webview)
            ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,toolbar_title,
                "About")
        }

}