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



    /*@SuppressLint("RestrictedApi")
    protected val mOnNavigationItemSelectedListener =

       *//* BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->

            var fragment: Fragment?
            setVisibleToolbarHeader(View.VISIBLE)

            when (menuItem.itemId) {

                R.id.merchant_navigation_dashboard-> {
                    fragment =
                        MerchantDashFragment()
                    merchant_add_vehicle_btn.visibility=View.INVISIBLE
                }
                R.id.merchant_navigation_order-> {
                    fragment =
                        MerchantOrderListFragment()
                    merchant_add_vehicle_btn.visibility=View.INVISIBLE
                    toolbar_title.text=getString(R.string.order)
                }
                R.id.merchant_navigation_home-> {
                    fragment =
                        MerchantHomeFragment()
                    merchant_add_vehicle_btn.visibility=View.VISIBLE
                    toolbar_title.text=getString(R.string.title_home)
                }
                R.id.merchant_navigation_profile-> {
                    fragment =
                        MerchantProfileFragment()
                    merchant_add_vehicle_btn.visibility=View.INVISIBLE
                    toolbar_title.text=getString(R.string.profile)
                }
                R.id.merchant_navigation_support-> {
                    fragment =
                        MerchantSupportFragment()
                    merchant_add_vehicle_btn.visibility=View.INVISIBLE
                    toolbar_title.text=getString(R.string.support)
                }

                else -> {
                    return@OnNavigationItemSelectedListener false
                }
            }


            moveToTargatedFragment(fragment)

            return@OnNavigationItemSelectedListener true*//*
        }*/

    /*private fun moveToTargatedFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
            .commit()


    }*/

   /* @SuppressLint("RestrictedApi")
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.merchant_nav_dashboard -> {
                MoveToAnotherComponent.moveToMerchantMainActivity(this)
            }
            R.id.merchant_nav_about -> {
                MoveToAnotherComponent.moveToAboutActivity(this)
            }

            R.id.merchant_nav_help -> {


            }
            R.id.merchant_nav_my_address -> {
                MoveToAnotherComponent.moveToMerchantAddressActivity(this)
            }

            R.id.merchant_nav_tc -> {
                MoveToAnotherComponent.moveToTermsActivity(this)
            }
            R.id.merchant_nav_logout -> {
                MoveToAnotherComponent.onLogout(this, Constant.INTENT_LOGOUT_KEY, Constant.LOGOUT_VALUE)
//                Toast.makeText(this, getString(R.string.under_development), Toast.LENGTH_SHORT).show()

            }
        }
        menuItem.isChecked=false
        merchant_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }*/


}