package com.eazyrento.merchant

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.eazyrento.Constant
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.eazyrento.R
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.merchant.view.fragment.*
import kotlinx.android.synthetic.main.merchant_activity_main.*
import kotlinx.android.synthetic.main.row_merchant_add_vehicle.*
import kotlinx.android.synthetic.main.toolbar.*

open class MerchantNavigationActivity : BaseActivity(){
    override fun <T> moveOnSelecetedItem(type: T) {
    }


}