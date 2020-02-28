package com.rental.customer.myaddress.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rental.R
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.add_new_address_activity.*
import kotlinx.android.synthetic.main.toolbar.*

class EditAddressActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_address_activity)

        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,
            toolbar_title,"My Address")

        btn_save.visibility=View.GONE
        layout_update.visibility=View.VISIBLE
    }
}