package com.rental.customer.dashboard.view.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.rental.R
import com.rental.customer.dashboard.view.fragment.*
import com.rental.customer.utils.Common.Companion.showLoading
import com.rental.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity :AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView(){


        showLoading(this,layout_loading,img_gif)

        bottom_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        bottom_view.selectedItemId=R.id.navigation_home
        navigationView.setNavigationItemSelectedListener(this)

        setDefaultFragment()

        img_menu.setOnClickListener {  drawer_layout.openDrawer(GravityCompat.START) }

        val header = navigationView.getHeaderView(0)

        header.edit_profile_menu.setOnClickListener {
            MoveToAnotherComponent.moveToProfileActivity(this)
            drawer_layout.closeDrawer(GravityCompat.START)
            }
        img_notification.setOnClickListener { MoveToAnotherComponent.moveToNotificationActivity(this) }
    }

     private fun setDefaultFragment(){
        val fragment = HomeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {

            R.id.navigation_home -> {
                showLoading(this,layout_loading,img_gif)
                setDefaultFragment()
                toolbar_title.text=getString(R.string.select_category)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_wishlist -> {
                showLoading(this,layout_loading,img_gif)
                val fragment = WishListFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
                    .commit()
                toolbar_title.text=getString(R.string.wishlist)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_order -> {
                showLoading(this,layout_loading,img_gif)
                val fragment = OrderFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
                    .commit()
                toolbar_title.text=getString(R.string.order)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_profile -> {
                showLoading(this,layout_loading,img_gif)
                val fragment = ProfieFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
                    .commit()
                toolbar_title.text=getString(R.string.profie)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_support -> {
                showLoading(this,layout_loading,img_gif)
                toolbar_title.text=getString(R.string.help)
                val fragment = SupportFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }

        false
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nav_about -> {
                MoveToAnotherComponent.moveToAboutActivity(this)
            }
            R.id.nav_dashboard -> {
                setDefaultFragment()
                toolbar_title.text=getString(R.string.select_category)
            }
            R.id.nav_logout -> {
                Toast.makeText(this, getString(R.string.under_development), Toast.LENGTH_SHORT).show()
            }
            R.id.nav_my_address -> {
               MoveToAnotherComponent.moveToMyAddressActivity(this)
            }
            R.id.nav_payment -> {
                MoveToAnotherComponent.moveToPaymentHistoryActivity(this)
            }
            R.id.nav_tc -> {
                MoveToAnotherComponent.moveToTermsActivity(this)
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}