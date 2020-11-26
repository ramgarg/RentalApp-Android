package com.eazyrento.customer.dashboard.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.agent.view.BaseNavigationActivity
import com.eazyrento.agent.view.activity.AgentProfileUpdateActivity
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.customer.dashboard.view.fragment.*
import com.eazyrento.customer.myaddress.view.MyAddressListActivity
import com.eazyrento.customer.notification.view.NotificationFragment
import com.eazyrento.customer.payment.view.PaymentHistoryActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.activity_agent_home_.*
import kotlinx.android.synthetic.main.activity_main.navigation_view
import kotlinx.android.synthetic.main.toolbar.*


class CustomerMainActivity : BaseNavigationActivity(), NavigationView.OnNavigationItemSelectedListener{

    override fun setNotificationFragment() {
        /*fun setNavigationFragment(){
            bottom_navigation_view.selectedItemId = R.id.navigation_common_first_pos
        }
        fun setNotificationFragment(){*/

            toolbar_title.text=getString(R.string.title_notification)

            moveToSelectedFragment(NotificationFragment())

       // }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setInitData()

        navigation_view.menu.findItem(R.id.nav_payment).isVisible = true
        navigation_view.menu.findItem(R.id.nav_my_address).isVisible = true
        navigation_view.menu.findItem(R.id.profile).isVisible = true
        navigation_view.menu.findItem(R.id.nav_near_by_driver).isVisible = true

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val value = intent?.getIntExtra(Constant.KEY_INTENT_SUCCESS_ORDER_BOOKING,-1)

        if (value ==Constant.VALUE_INTENT_SUCCESS_ORDER_BOOKING) {
            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, "onNewIntent " + value)
            //booking
            setForthPosFragment()
            return
        }

        if (value ==Constant.VALUE_INTENT_NEAR_BY_DRIVER) {
            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, "onNewIntent " + value)
            //booking
            setHomeFragment()
            return
        }


    }



    override fun bottomNavigationListener(menuItemID: Int): Fragment? {

        val fragment:Fragment?
        
        when (menuItemID) {

            R.id.navigation_home -> {
                fragment=
                    CustomerHomeFragment()
                toolbar_title.text=getString(R.string.select_category)
            }
            R.id.navigation_common_first_pos -> {
                fragment=
                    WishListFragment()

                toolbar_title.text=getString(R.string.wishlist)
            }
            R.id.navigation_order -> {
                fragment=
                    CustomerOrderListFragment()

                toolbar_title.text=getString(R.string.order)

            }

            R.id.navigation_common_fourth_pos -> {
                fragment=
                    CustomerBookingsFragment()

                toolbar_title.text=getString(R.string.bookings)
            }
            R.id.navigation_support -> {
                /*toolbar_title.text=getString(R.string.help)
                fragment=SupportFragment()*/
                showHippoSupport()

                fragment =null
            }
            else ->{
                return null
            }
        }
        return fragment
    }

    override fun helpAndSupport() {
        showToast(R.string.under_development)
    }

    override fun addNotes() {
    }

    override fun viewPaymentHistory() {
        MoveToAnotherComponent.moveToActivityNormal<PaymentHistoryActivity>(this)
    }

    override fun viewMyAddress() {
        MoveToAnotherComponent.moveToActivityNormal<MyAddressListActivity>(this)
    }

    override fun paymentActivity(orderID: String) {
        var value:Int =-1
        try {
            value = orderID.toInt()
        }catch (e:Exception){
            e.printStackTrace()
        }

        MoveToAnotherComponent.moveToActivityWithIntentValue<CustomerPaymentActivity>(this,Constant.KEY_ORDER_DETAILS_ID,
            value)
    }

    override fun orderSummeryActivity(orderID: String) {

        var value:Int =-1
        try {
            value = orderID.toInt()
        }catch (e:Exception){
            e.printStackTrace()
        }

        MoveToAnotherComponent.moveToActivityWithIntentValue<CustomerOrderSummaryActivity>(this, Constant.ORDER_SUMMERY_KEY,
            value)
    }

    override fun openUpdateProfileActivity() {
        MoveToAnotherComponent.moveToActivityNormal<CustomerProfileUpdateActivity>(this)
    }


}