package com.eazyrento.agent.view

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.eazyrento.Constant
import com.eazyrento.agent.view.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.eazyrento.R
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.activity_agent_home_.*
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.toolbar.*

open abstract class BaseNavigationActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun setHomeFragment(){
        bottom_navigation_view.selectedItemId = R.id.navigation_home
    }

    // dynamic title
    fun setNavigationIconAndTitle(hashMap:HashMap<Int, MenuData>){

       val keys = hashMap.keys

        for (key in keys){
            val item = bottom_navigation_view.menu.findItem(key)

            item.icon =getDrawable(hashMap.get(key)!!.iconID)

            item.title = resources.getString(hashMap.get(key)!!.titleID)

        }
    }

    // top bar
      fun topBarWithMenuIconAndTitleMessage(title:String){
        super.topBarWithMenuIconAndNotificationWithTitleMessage(title)

        img_notification.setOnClickListener { MoveToAnotherComponent.moveToNotificationActivity(this) }
        img_menu.setOnClickListener { drawer_layout.openDrawer(GravityCompat.START)}

    }

    protected fun setInitData(){
        setBottomNavigationListener()
        setLeftSliderNavigationListener()

        setTopHeaderListener()
        topBarWithMenuIconAndTitleMessage(resources.getString(R.string.title_home))

        // select home fragment
        setHomeFragment()
    }

    protected fun setBottomNavigationListener(){
        // Bottom set listener bottom navigation view
        bottom_navigation_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


    }
    protected fun setLeftSliderNavigationListener(){
        // Left side set listener drawer
        navigation_view.setNavigationItemSelectedListener(this)
    }

// profile view
    protected fun setTopHeaderListener(){
        val header = navigation_view.getHeaderView(0)

        header.edit_profile_menu.setOnClickListener {
            MoveToAnotherComponent.moveToAgentProfileActivity(this)
            drawer_layout.closeDrawer(GravityCompat.START)
        }
    }

    //bottom view navigation
     val mOnNavigationItemSelectedListener =

        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->

            var fragment = bottomNavigationListener(menuItem.itemId)

            fragment?.let {
                moveToSelectedFragment(it)
                return@OnNavigationItemSelectedListener true
            }
            return@OnNavigationItemSelectedListener false

        }

    protected fun moveToSelectedFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
            .commit()

    }

    //left navigation slider barr.....

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nav_dashboard -> {
//                MoveToAnotherComponent.moveToAgentHomeActivity(this)
                setHomeFragment()
            }

            R.id.nav_about -> {
                MoveToAnotherComponent.moveToAboutActivity(this)
            }

            R.id.nav_help -> {
                helpAndSupport()
            }
            R.id.nav_add_note -> {
               addNotes()
            }

            R.id.nav_tc -> {
                MoveToAnotherComponent.moveToTermsActivity(this)
            }
            R.id.nav_payment->{
                viewPaymentHistory()
            }
            R.id.nav_my_address->{
                viewMyAddress()
            }
            R.id.nav_logout -> {
                MoveToAnotherComponent.onLogout(this, Constant.INTENT_LOGOUT_KEY, Constant.LOGOUT_VALUE)
            }
        }
        menuItem.isChecked=false
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

   /* fun setVisibleToolbarHeader(visible: Int){
        toolbar_header.visibility = visible
    }*/

    //common
     data class MenuData(val iconID:Int,val titleID: Int)
    // bottom navigation method
    abstract fun bottomNavigationListener(menuItemID: Int):Fragment?
    // slider left menu
    abstract fun helpAndSupport()
    abstract fun addNotes()
    abstract fun viewPaymentHistory()
    abstract fun viewMyAddress()



}