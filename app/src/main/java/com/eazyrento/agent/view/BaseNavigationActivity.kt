package com.eazyrento.agent.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.eazyrento.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.eazyrento.common.view.ApiResult
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.common.view.LiveDataActivityClass
import com.eazyrento.customer.profile.ProfileActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.login.model.modelclass.ProfileModelReqRes
import com.eazyrento.login.model.modelclass.UserProfile
import com.eazyrento.login.viewmodel.ProfileUserViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_agent_home_.*
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.toolbar.*

open abstract class BaseNavigationActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        if (EazyRantoApplication.profileData==null)
           getProfileAPI()
    }
    private fun getProfileAPI(){
        if(InternetNetworkConnection.isNetworkInternetAvailbale(this)) {

            val livedata = LiveDataActivityClass(object :ApiResult{
                override fun <T> onSuccessApiResult(data: T) {
                    EazyRantoApplication.profileData = (data as ProfileModelReqRes).user_profile

                    EazyRantoApplication.profileData?.let { setTopHeaderData(it)}
                }

                override fun <T> statusCodeOfApi(data: T) {
                }

            })
            livedata.observeApiResult(
                livedata.callAPIActivity<ProfileUserViewModel>(this).getProfileUser(),this,this
            )
     }
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
    private fun setTopHeaderData(user_profile: UserProfile) {

    val header = navigation_view.getHeaderView(0)
    header.user_name_menu.text=user_profile.full_name
    header.user_type_menu.text =Session.getInstance(this)?.getUserRole()

    Picasso.with(this).load(user_profile.profile_image).into(header.profile_img_menu)

        header.edit_profile_menu.setOnClickListener {
            MoveToAnotherComponent.moveToActivityNormal<ProfileActivity>(this)
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

    override fun onBackPressed() {
        finishCurrentActivityWithResult(Constant.REQUEST_CODE_FINISH_LOGIN_ON_BACK, Intent())
        super.onBackPressed()
    }


}