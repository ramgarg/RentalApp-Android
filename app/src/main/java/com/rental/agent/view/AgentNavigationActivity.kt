package com.rental.agent.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.rental.R
import com.rental.agent.view.fragment.*
import com.rental.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.activity_agent_home_.*

open class AgentNavigationActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected val mOnNavigationItemSelectedListener =

        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->

            var fragment:Fragment?
            setVisibleToolbarHeader(View.VISIBLE)

            when (menuItem.itemId) {

                R.id.navigation_order -> {
                    fragment = AgentOrderFragment()
                }

                R.id.navigation_notification-> {
                    fragment = AgentNotificationFragment()
                }

                R.id.navigation_home -> {
                    setVisibleToolbarHeader(View.GONE)
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


            moveToTargatedFragment(fragment)

            return@OnNavigationItemSelectedListener true

        }

    private fun moveToTargatedFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
            .commit()

    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nav_about -> {
                MoveToAnotherComponent.moveToAboutActivity(this)
            }
            R.id.nav_dashboard -> {
                bottom_navigation_view_agent.selectedItemId = R.id.navigation_home
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
//        menuItem.isChecked = false
        drawer_layout_agent.closeDrawer(GravityCompat.START)
        return true
    }

    fun setVisibleToolbarHeader(visible: Int){
        toolbar_header_agent.visibility = visible
    }
}