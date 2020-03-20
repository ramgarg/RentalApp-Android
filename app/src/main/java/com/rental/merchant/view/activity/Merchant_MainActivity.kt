package com.rental.merchant.view.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.rental.R
import com.rental.customer.dashboard.view.fragment.*
import com.rental.customer.utils.Common
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.merchant.view.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.img_gif
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.merchant_activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class Merchant_MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.merchant_activity_main)

        initView()
    }

    private fun initView(){
        Common.showLoading(this, merchant_layout_loading, img_gif)
        merchant_bottom_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        merchant_bottom_view.selectedItemId=R.id.merchant_navigation_home
        merchant_navigationView.setNavigationItemSelectedListener(this)

        setDefaultFragment()

        img_menu.setOnClickListener {  merchnat_drawer_layout.openDrawer(GravityCompat.START) }

        val header = merchant_navigationView.getHeaderView(0)

        header.edit_profile_menu.setOnClickListener {
            MoveToAnotherComponent.moveToProfileActivity(this)
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        img_notification.setOnClickListener { MoveToAnotherComponent.moveToNotificationActivity(this) }
    }
    private fun setDefaultFragment(){
        val fragment = Merchant_HomeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.merchant_main_container, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {

            R.id.merchant_navigation_home -> {
                Common.showLoading(this, merchant_layout_loading, img_gif)
                setDefaultFragment()
                toolbar_title.text=getString(R.string.select_category)
                return@OnNavigationItemSelectedListener true
            }
            R.id.merchant_navigation_dashboard -> {
                //Common.showLoading(this, merchant_layout_loading, img_gif)
                val fragment = Merchant_Dash_Fragment()
                supportFragmentManager.beginTransaction().replace(R.id.merchant_main_container, fragment, fragment.javaClass.simpleName)
                    .commit()
                toolbar_title.text=getString(R.string.app_name)
                return@OnNavigationItemSelectedListener true
            }
            R.id.merchant_navigation_order -> {
                Common.showLoading(this, merchant_layout_loading, img_gif)
                val fragment = Merchant_OrderFragment()
                supportFragmentManager.beginTransaction().replace(R.id.merchant_main_container, fragment, fragment.javaClass.simpleName)
                    .commit()
                toolbar_title.text=getString(R.string.order)
                return@OnNavigationItemSelectedListener true
            }

            R.id.merchant_navigation_profile -> {
                Common.showLoading(this, merchant_layout_loading, img_gif)
                val fragment = Merchant_ProfileFragment()
                supportFragmentManager.beginTransaction().replace(R.id.merchant_main_container, fragment, fragment.javaClass.simpleName)
                    .commit()
                toolbar_title.text=getString(R.string.profie)
                return@OnNavigationItemSelectedListener true
            }
            R.id.merchant_navigation_support -> {
                Common.showLoading(this, merchant_layout_loading, img_gif)
                toolbar_title.text=getString(R.string.help)
                val fragment = Merchant_Support_Fragment()
                supportFragmentManager.beginTransaction().replace(R.id.merchant_main_container, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }

        false
    }
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.merchant_nav_about -> {
                MoveToAnotherComponent.moveToAboutActivity(this)
            }
            R.id.merchant_nav_dashboard -> {
                setDefaultFragment()
                toolbar_title.text=getString(R.string.select_category)
            }
            R.id.merchant_nav_logout -> {
                Toast.makeText(this, getString(R.string.under_development), Toast.LENGTH_SHORT).show()
            }
            R.id.merchant_nav_my_address -> {
                MoveToAnotherComponent.moveToMyAddressActivity(this)
            }

            R.id.merchant_nav_tc -> {
                MoveToAnotherComponent.moveToTermsActivity(this)
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}