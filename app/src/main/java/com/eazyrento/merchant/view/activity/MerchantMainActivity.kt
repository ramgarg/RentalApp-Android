package com.eazyrento.merchant.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.agent.view.BaseNavigationActivity
import com.eazyrento.agent.view.activity.AgentProfileUpdateActivity
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.customer.myaddress.view.MyAddressListActivity
import com.eazyrento.customer.notification.view.NotificationFragment
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.merchant.view.fragment.*
import kotlinx.android.synthetic.main.merchant_activity_main.bottom_navigation_view
import kotlinx.android.synthetic.main.merchant_activity_main.navigation_view
import kotlinx.android.synthetic.main.toolbar.*

class MerchantMainActivity : BaseNavigationActivity() {

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
        setContentView(R.layout.merchant_activity_main)

        setInitData()
        /*specific data*/
        setBottomNavigationItemsDynamic()

        navigation_view.menu.findItem(R.id.nav_my_address).isVisible = true

    }

    fun setBottomNavigationItemsDynamic(){
        val hashMap = HashMap<Int,MenuData>()

        hashMap.put(R.id.navigation_common_first_pos,MenuData(R.mipmap.dashboard,R.string.dashboard))
        hashMap.put(R.id.navigation_common_fourth_pos,MenuData(R.mipmap.profile_inactive,R.string.profile))

        setNavigationIconAndTitle(hashMap)
    }

    override fun bottomNavigationListener(menuItemID: Int): Fragment? {

        var fragment:Fragment?

        when (menuItemID) {

            R.id.navigation_common_first_pos-> {
                fragment =
                    MerchantDashFragment()
                toolbar_title.text=getString(R.string.dashboard)
            }
            R.id.navigation_order-> {
                fragment =
                    MerchantOrderListFragment()
                toolbar_title.text=getString(R.string.orders)
            }
            R.id.navigation_home-> {
                fragment =
                    MerchantHomeFragment()
                toolbar_title.text=getString(R.string.home)
            }
            R.id.navigation_common_fourth_pos-> {
                fragment =
                    MerchantProfileFragment()
                toolbar_title.text=getString(R.string.profile)
            }
            R.id.navigation_support-> {
                /*fragment =
                    MerchantSupportFragment()
                toolbar_title.text=getString(R.string.support)*/
                showHippoSupport()
                fragment = null
            }

            else -> {
                return null
            }
        }

        return fragment

    }



    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

//        val value = intent?.getIntExtra(Constant.INTENT_SUCCESS_ADDED_PRODUCT,-1)
//        val value = intent?.getIntExtra(Constant.INTENT_DRIVER_ASSIGNED_BY_MERCHANT,-1)
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"onNewIntent")

        // move to home fragemtn
        setHomeFragMent()
    }

    fun setHomeFragMent()
    {
        bottom_navigation_view.selectedItemId = R.id.navigation_home
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode== Activity.RESULT_OK && requestCode ==Constant.MERCHANT_HOME_FRAGMENT_REQUEST_CODE){
            setHomeFragMent()
        }
    }

    override fun helpAndSupport() {
        moveToSelectedFragment(MerchantSupportFragment())
    }

    override fun addNotes() {
    }

    override fun viewPaymentHistory() {
    }

    override fun viewMyAddress() {
        MoveToAnotherComponent.moveToActivityNormal<MyAddressListActivity>(this)
    }
    override fun paymentActivity(orderID: String) {
        MoveToAnotherComponent.moveToActivityWithIntentValue<MerchantMainActivity>(this,Constant.KEY_ORDER_DETAILS_ID,
            orderID)
    }

    override fun orderSummeryActivity(orderID: String) {
        try {
            MoveToAnotherComponent.moveToActivityWithIntentValue<MerchantOrderSummaryActivity>(
                this, Constant.ORDER_SUMMERY_KEY,
                orderID.toInt()
            )
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun openUpdateProfileActivity() {
        MoveToAnotherComponent.moveToActivityNormal<MerchantProfileUpdateActivity>(this)
    }
}