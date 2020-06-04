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
import com.eazyrento.customer.notification.view.NotificationActivity
import com.eazyrento.customer.profile.UpdateProfileActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.webpages.AboutActivity
import com.eazyrento.login.model.modelclass.ProfileModelReqRes
import com.eazyrento.login.model.modelclass.UserProfile
import com.eazyrento.login.view.ProfileData
import com.eazyrento.login.view.ProfileDataAPILisetner
import com.eazyrento.login.view.UserProfileActivity
import com.eazyrento.login.viewmodel.ProfileUserViewModel
import com.google.android.material.bottomnavigation.LabelVisibilityMode.LABEL_VISIBILITY_LABELED
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_agent_home_.*
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.toolbar.*

open abstract class BaseNavigationActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        getProfileAPI()
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

        img_notification.setOnClickListener { MoveToAnotherComponent.moveToActivityNormal<NotificationActivity>(this) }
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
        bottom_navigation_view.labelVisibilityMode =LABEL_VISIBILITY_LABELED


    }
    protected fun setLeftSliderNavigationListener(){
        // Left side set listener drawer
        navigation_view.setNavigationItemSelectedListener(this)
    }

    private fun openUpdateProfileActivity(){
        MoveToAnotherComponent.moveToActivityNormal<UpdateProfileActivity>(this)
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