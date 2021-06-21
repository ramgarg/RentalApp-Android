package com.eazyrento.customer.webpages

import android.os.Bundle
import com.eazyrento.R
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.webservice.PathURL
import kotlinx.android.synthetic.main.privacy_policy.*

class PrivacyPolicy : BaseActivity() {

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.privacy_policy)
        topBarWithBackIconAndTitle(getString(R.string.privacy_policy))

        Common.openWebPage(webview, PathURL.BASE_URL + PathURL.PRIVACY_POLICY,this)


    }
}