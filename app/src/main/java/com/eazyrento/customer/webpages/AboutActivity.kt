package com.eazyrento.customer.webpages

import android.os.Bundle
import com.eazyrento.R
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import com.eazyrento.webservice.PathURL
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.toolbar.*


class AboutActivity : BaseActivity() {

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_about)
            MoveToAnotherComponent.openWebPage(webview, PathURL.BASE_URL + PathURL.ABOUT_US)
            topBarWithBackIconAndTitle(getString(R.string.about_us))

        }
}