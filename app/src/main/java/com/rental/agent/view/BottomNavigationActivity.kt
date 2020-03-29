package com.rental.agent.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rental.R
import com.rental.agent.view.fragment.*
import com.rental.customer.utils.Common
import kotlinx.android.synthetic.main.activity_main.*

open class BottomNavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected val mOnNavigationItemSelectedListener =

        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->

            var fragment:Fragment?

            when (menuItem.itemId) {

                R.id.navigation_order -> {
                    fragment = AgentOrderFragment();
                }

                R.id.navigation_notification-> {
                    fragment = AgentNotificationFragment()
                }

                R.id.navigation_home -> {
                    fragment = AgentHomeFragment()
                }
                R.id.navigation_profile -> {
                    fragment = AgentProfileFragment()
                }
                R.id.navigation_support -> {
                    fragment = AgentSupportFragment()
                }
                else -> {
                    return@OnNavigationItemSelectedListener false
                }
            }

           // Common.showLoading(this, layout_loading, img_gif)
//            toolbar_title.text=getString(R.string.help)
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
                .commit()
            return@OnNavigationItemSelectedListener true

        }
}