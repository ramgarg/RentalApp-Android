package com.rental.customer.webpages

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rental.R
import com.rental.customer.utils.MoveToActivity
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.toolbar.*

class TermsConditionActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_cond)

        ViewVisibility.isVisibleOrNot(img_back,img_menu,img_notification,toolbar_title,"Terms & Conditions")

        MoveToActivity.openWebPage(webview)

        img_back.setOnClickListener { finish() }

    }

}