package com.rental.customer.myaddress.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rental.R
import com.rental.customer.dashboard.view.activity.BaseActivity
import com.rental.customer.utils.MoveToActivity
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_my_address.*
import kotlinx.android.synthetic.main.toolbar.*

class MyAddressActivity :BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_my_address)

      initView()
    }

    private fun initView(){
        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,
            toolbar_title,getString(R.string.my_address))
        btn_add_new_address.setOnClickListener { MoveToActivity.moveToAddNewAddressActivity(this) }
//        edit_address_home.setOnClickListener { MoveToActivity.moveToEditAddressActivity(this)  }

    }
}