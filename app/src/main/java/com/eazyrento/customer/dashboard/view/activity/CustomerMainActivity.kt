package com.eazyrento.customer.dashboard.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.agent.view.BaseNavigationActivity
import com.eazyrento.agent.view.activity.AgentPaymentActivity
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.customer.dashboard.view.fragment.*
import com.eazyrento.customer.myaddress.view.MyAddressListActivity
import com.eazyrento.customer.payment.view.PaymentHistoryActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.activity_main.navigation_view
import kotlinx.android.synthetic.main.toolbar.*


class CustomerMainActivity : BaseNavigationActivity(), NavigationView.OnNavigationItemSelectedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setInitData()

        navigation_view.menu.findItem(R.id.nav_payment).isVisible = true
        navigation_view.menu.findItem(R.id.nav_my_address).isVisible = true
        navigation_view.menu.findItem(R.id.profile).isVisible = true
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val value = intent?.getIntExtra(Constant.KEY_INTENT_SUCCESS_ORDER_BOOKING,-1)

        if (value ==Constant.VALUE_INTENT_SUCCESS_ORDER_BOOKING) {
            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, "onNewIntent " + value)
            //booking
            setForthPosFragment()
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
        showToast(resources.getString(R.string.under_development))
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
        MoveToAnotherComponent.moveToActivityWithIntentValue<CustomerPaymentActivity>(this,Constant.KEY_ORDER_DETAILS_ID,
            orderID)
    }

    override fun orderSummeryActivity(orderID: String) {
        MoveToAnotherComponent.moveToActivityWithIntentValue<CustomerOrderSummaryActivity>(this, Constant.ORDER_SUMMERY_KEY,
            orderID)
    }


}