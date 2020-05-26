package com.eazyrento.agent.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.agent.view.BaseNavigationActivity
import com.eazyrento.agent.view.fragment.*
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.activity_agent_home_.navigation_view
import kotlinx.android.synthetic.main.toolbar.*

class AgentMainActivity : BaseNavigationActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_home_)

        setInitData()
        // specific method of every module
        setBottomNavigationItemsDynamic()

        navigation_view.menu.findItem(R.id.nav_add_note).isVisible = true
        navigation_view.menu.findItem(R.id.profile).isVisible = true


    }

    fun setBottomNavigationItemsDynamic(){
        val hashMap = HashMap<Int,MenuData>()

        hashMap.put(R.id.navigation_common_first_pos,MenuData(R.mipmap.notification,R.string.title_notification))
        hashMap.put(R.id.navigation_common_fourth_pos,MenuData(R.mipmap.order_inactive,R.string.bookings))

        setNavigationIconAndTitle(hashMap)
    }




    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val value = intent?.getIntExtra(Constant.INTENT_SUCCESS_ADDED_PRODUCT,-1)
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"onNewIntent "+value)

        // move to home fragemtn
        setHomeFragment()
    }
/*
*
* Bottom menu item listner
* */
    override fun bottomNavigationListener(itemId:Int): Fragment? {

        var fragment:Fragment?

        when (itemId) {


            R.id.navigation_common_first_pos-> {

                fragment =
                    AgentNotificationFragment()
                toolbar_title.text=getString(R.string.title_notification)
            }

            R.id.navigation_order -> {
                fragment =
                    AgentOrderListFragment()
                toolbar_title.text=getString(R.string.order)
            }

            R.id.navigation_home -> {
                //setVisibleToolbarHeader(View.GONE)
                toolbar_title.text=getString(R.string.home)
                fragment =
                    AgentHomeFragment()

            }
            R.id.navigation_common_fourth_pos -> {
                fragment =
                    AgentBookingsFragment()
                toolbar_title.text=getString(R.string.bookings)
            }
            R.id.navigation_support -> {
                fragment =
                    AgentSupportFragment()
                toolbar_title.text=getString(R.string.help)
            }
            else -> {
                return null
            }
        }
        return fragment
    }

    override fun helpAndSupport() {
        moveToSelectedFragment(AgentSupportFragment())
    }

    override fun addNotes() {
        MoveToAnotherComponent.moveToActivityNormal<AgentAddNoteActivity>(this)
//        toolbar_title.text=getString(R.string.mynotes)
    }

    override fun viewPaymentHistory() {
    }

    override fun viewMyAddress() {
    }


}