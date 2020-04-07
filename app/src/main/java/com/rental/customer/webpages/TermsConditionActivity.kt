package com.rental.customer.webpages

import android.os.Bundle
import com.rental.R
import com.rental.common.view.BaseActivity
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.toolbar.*

class TermsConditionActivity :BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_cond)

        MoveToAnotherComponent.openWebPage(webview)

        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,toolbar_title,
            "Terms & Conditions")

    }

}