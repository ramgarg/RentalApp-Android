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
import kotlinx.android.synthetic.main.toolbar.*

open class AgentNavigationActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected val mOnNavigationItemSelectedListener =

        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->

            var fragment:Fragment?
            setVisibleToolbarHeader(View.VISIBLE)

            when (menuItem.itemId) {


                R.id.agent_navigation_order -> {
                    fragment = AgentOrderFragment()
                    toolbar_title.text=getString(R.string.order)
                }

                R.id.agent_navigation_notification-> {
                    fragment = AgentNotificationFragment()
                    toolbar_title.text=getString(R.string.title_notification)
                }

                R.id.agent_navigation_home -> {
                    setVisibleToolbarHeader(View.GONE)
                    fragment = AgentHomeFragment()

                }
                R.id.agent_navigation_profile -> {
                    fragment = AgentProfileFragment()
                    toolbar_title.text=getString(R.string.profie)
                }
                R.id.agent_navigation_support -> {
                    fragment = AgentSupportFragment()
                    toolbar_title.text=getString(R.string.help)
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
            R.id.agent_nav_about -> {
                MoveToAnotherComponent.moveToAboutActivity(this)
            }

            R.id.agent_nav_help -> {

                Toast.makeText(this, getString(R.string.under_development), Toast.LENGTH_SHORT).show()
            }
            R.id.agent_nav_add_note -> {
                MoveToAnotherComponent.moveToMyNotesActivity(this)
                toolbar_title.text=getString(R.string.mynotes)
            }

            R.id.agent_nav_tc -> {
                MoveToAnotherComponent.moveToTermsActivity(this)
            }
        }
        menuItem.isChecked=false
        drawer_layout_agent.closeDrawer(GravityCompat.START)
        return true
    }

    fun setVisibleToolbarHeader(visible: Int){
        toolbar_header_agent.visibility = visible
    }
}