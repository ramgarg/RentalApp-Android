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
import com.rental.customer.utils.MoveToActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.toolbar.*


class HomeActivity :AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initView()
    }

    private fun initView(){


        bottom_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        bottom_view.setSelectedItemId(R.id.navigation_home);
        navigationView.setNavigationItemSelectedListener(this);
        setDefaultFragment()
        img_menu.setOnClickListener {  drawer_layout.openDrawer(GravityCompat.START) }
        val header = navigationView.getHeaderView(0)
        header.edit_profile_menu.setOnClickListener {
            MoveToActivity.moveToProfileActivity(this)
            drawer_layout.closeDrawer(GravityCompat.START)
            }
        img_notification.setOnClickListener { MoveToActivity.moveToNotificationActivity(this) }
    }

     fun setDefaultFragment(){
        val fragment = HomeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {

            R.id.navigation_home -> {
                setDefaultFragment();
                toolbar_title.text="Select Category"
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_wishlist -> {
                val fragment = WishListFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                toolbar_title.text="Wishlist"
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_order -> {
                val fragment = OrderFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                toolbar_title.text="Order"
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_profile -> {
                val fragment = ProfieFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                toolbar_title.text="Profile"
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_support -> {
                toolbar_title.text="Help & Support"
                val fragment = SupportFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        when (menuItem.itemId) {
            R.id.nav_about -> {
                MoveToActivity.moveToAboutActivity(this)
            }

            R.id.nav_dashboard -> {
                setDefaultFragment();
                toolbar_title.text="Select Category"
            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Under Development", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_my_address -> {
               MoveToActivity.moveToMyAddressActivity(this)
            }
            R.id.nav_payment -> {
                MoveToActivity.moveToPaymentHistoryActivity(this)
            }
            R.id.nav_tc -> {
                MoveToActivity.moveToTermsActivity(this)
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}