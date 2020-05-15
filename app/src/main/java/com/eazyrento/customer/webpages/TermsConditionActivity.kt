package com.eazyrento.customer.webpages

import android.os.Bundle
import com.eazyrento.R
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.toolbar.*

class TermsConditionActivity : BaseActivity() {
    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_cond)
        topBarWithBackIconAndTitle("Terms & Conditions")

        MoveToAnotherComponent.openWebPage(webview)


    }

}