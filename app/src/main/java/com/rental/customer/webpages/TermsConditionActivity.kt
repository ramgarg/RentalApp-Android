package com.rental.customer.webpages

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rental.R
import com.rental.customer.dashboard.view.activity.BaseActivity
import com.rental.customer.utils.MoveToActivity
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.toolbar.*

class TermsConditionActivity :BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_cond)

        MoveToActivity.openWebPage(webview)

        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,toolbar_title,
            "Terms & Conditions")

    }

}