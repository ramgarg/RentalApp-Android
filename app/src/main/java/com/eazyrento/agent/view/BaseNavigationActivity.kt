package com.eazyrento.agent.view

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.Window
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.eazyrento.*
import com.eazyrento.appbiz.AppBizLogger
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.notification.view.NotificationFragment
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.webpages.AboutActivity
import com.eazyrento.login.model.modelclass.UserProfile
import com.eazyrento.login.view.ProfileData
import com.eazyrento.login.view.ProfileDataAPILisetner
import com.eazyrento.login.view.UserProfileActivity
import com.eazyrento.supporting.DeeplinkEvents
import com.eazyrento.supporting.DeeplinkEvents.Companion.KEY_DEEPLINK
import com.eazyrento.supporting.DeeplinkEvents.Companion.KEY_ORDER_ID
import com.eazyrento.supporting.LocalManager
import com.eazyrento.supporting.isDeeplinkingFromNotification
import com.google.android.material.bottomnavigation.LabelVisibilityMode.LABEL_VISIBILITY_LABELED
import com.squareup.picasso.Picasso
import initHippoWithUserData
import kotlinx.android.synthetic.main.activity_agent_home_.*
import kotlinx.android.synthetic.main.footer_menu.*
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.toolbar.*
import showHippoConversation

open abstract class BaseNavigationActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        ProfileData().callProfileAPI(this, object : ProfileDataAPILisetner {
            override fun onSuccessProfileDataAPI(userProfile: UserProfile?) {
                setTopHeaderData(userProfile)
            }
        })


    }

    fun setHomeFragment(){
        bottom_navigation_view.selectedItemId = R.id.navigation_home
    }

    fun setForthPosFragment(){
        bottom_navigation_view.selectedItemId = R.id.navigation_common_fourth_pos
    }

    fun setOrderListing(){
        bottom_navigation_view.selectedItemId = R.id.navigation_order
    }
    fun setBooking(){
        setForthPosFragment()
    }
    fun setPaymentActivity(orderID:String){
        paymentActivity(orderID)
    }
    fun setOrderSummeryActivity(orderID:String){
        orderSummeryActivity(orderID)
    }
    fun setNotification(){

        toolbar_title.text=getString(R.string.title_notification)

        moveToSelectedFragment(NotificationFragment())

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
     private fun topBarWithMenuIconListenerAndTitleMessage(title:String){
        super.topBarWithMenuIconAndNotificationWithTitleMessage(title)

        img_notification.setOnClickListener {
            //MoveToAnotherComponent.moveToActivityNormal<NotificationFragment>(this)
            setNotification()
        }
        img_menu.setOnClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
        }

    }

    fun setVisiblity(vis:Int){
        img_notification.visibility = vis
    }
    protected fun setInitData(){

        //hippo chat

        initHippoWithUserData(this)

        //set app version
        setAppVersion()
        setBottomNavigationListener()
        setLeftSliderNavigationListener()

        topBarWithMenuIconListenerAndTitleMessage(resources.getString(R.string.title_home))

        if (isDeeplinkingFromNotification(intent))
            pageNavigationAtDeeplink(DeeplinkEvents.mapPayLoadDataDeeplink)
        else
            setHomeFragment()
    }
    private fun setAppVersion(){
        app_version.text = "V".plus(BuildConfig.VERSION_NAME).plus("-").plus(BuildConfig.VERSION_CODE)
    }

     fun pageNavigationAtDeeplink(mapPayLoadDataDeeplink:Map<String,String>?){

        val value_deeplink = mapPayLoadDataDeeplink?.get(KEY_DEEPLINK)
        val value_order_id = mapPayLoadDataDeeplink?.get(KEY_ORDER_ID)

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"value deeplink: $value_deeplink")
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"value_order_id: $value_order_id")

        when{
            value_deeplink==DeeplinkEvents.BOOKINGS -> {setBooking()}
            value_deeplink==DeeplinkEvents.ORDER_LISTING->{setOrderListing()}
            value_deeplink==DeeplinkEvents.ORDER_SUMMARY && value_order_id!=null->{setOrderSummeryActivity(value_order_id)}
            value_deeplink==DeeplinkEvents.PAYMENT && value_order_id!=null->{setPaymentActivity(value_order_id)}
            else->setHomeFragment()
        }

    }

    protected fun setBottomNavigationListener(){
        // Bottom set listener bottom navigation view
        bottom_navigation_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        bottom_navigation_view.labelVisibilityMode =LABEL_VISIBILITY_LABELED


    }
    protected fun setLeftSliderNavigationListener(){
        // Left side set listener drawer
        navigation_view.setNavigationItemSelectedListener(this)
    }
// profile view
    private fun setTopHeaderData(user_profile:UserProfile?) {
    //val user_profile = EazyRantoApplication.profileData

    val header = navigation_view.getHeaderView(0)
    header.user_name_menu.text=user_profile?.full_name?.capitalize()
    header.user_type_menu.text =Session.getInstance(this)?.getUserRole()?.capitalize()

    Picasso.with(this).load(user_profile?.profile_image).into(header.profile_img_menu)

        header.edit_profile_menu.setOnClickListener {
            openUpdateProfileActivity()
            drawer_layout.closeDrawer(GravityCompat.START)
        }
    }

    //bottom view navigation
     val mOnNavigationItemSelectedListener =

        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->

            val fragment = bottomNavigationListener(menuItem.itemId)

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
                setHomeFragment()
            }

            R.id.nav_about -> {

                MoveToAnotherComponent.moveToActivityNormal<AboutActivity>(this)
            }

            R.id.profile -> {

                MoveToAnotherComponent.moveToActivityNormal<UserProfileActivity>(this)
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
            R.id.select_language->{
                showLanguageDialog()
            }
            R.id.nav_logout -> {
                MoveToAnotherComponent.onLogout(this, Constant.INTENT_LOGOUT_KEY, Constant.LOGOUT_VALUE)
            }
        }
        menuItem.isChecked=false
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    private fun showLanguageDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_language)

        val tv_english = dialog.findViewById(R.id.tv_english) as TextView
        val tv_arabic = dialog.findViewById(R.id.tv_arabic) as TextView

        tv_english.setText(R.string.english)
        tv_arabic.setText(R.string.arabic)

        tv_english.setOnClickListener {
            LocalManager.onLocalLanguuage(this,LocalManager.english_lang_code)
            dialog.dismiss()
        }
        tv_arabic.setOnClickListener {
            LocalManager.onLocalLanguuage(this,LocalManager.arebic_lang_code)
            dialog.dismiss()
        }

        dialog.show()
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
    abstract fun paymentActivity(orderID: String)
    abstract fun orderSummeryActivity(orderID: String)
    abstract fun openUpdateProfileActivity()

    override fun onBackPressed() {
        finishCurrentActivityWithResult(Constant.REQUEST_CODE_FINISH_LOGIN_ON_BACK, Intent())
        super.onBackPressed()
    }

    protected fun showHippoSupport(){
        showProgress()
        Handler().postDelayed(
            {hideProgress()}
            ,Env.HIPPO_LOADER_TIME)

        showHippoConversation(this)
    }


}