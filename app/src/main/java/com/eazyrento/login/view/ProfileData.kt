package com.eazyrento.login.view

import android.app.Activity
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.eazyrento.*
import com.eazyrento.agent.view.activity.AgentProfileUpdateActivity
import com.eazyrento.common.view.ApiResult
import com.eazyrento.common.view.LiveDataActivityClass
import com.eazyrento.common.view.UserInfoAPP
import com.eazyrento.customer.dashboard.view.activity.CustomerProfileUpdateActivity
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.login.model.modelclass.ProfileModelReqRes
import com.eazyrento.login.model.modelclass.UserProfile
import com.eazyrento.login.viewmodel.ProfileUserViewModel
import com.eazyrento.merchant.view.activity.MerchantProfileUpdateActivity
import com.squareup.picasso.Picasso

class ProfileData {

    fun setData(context:Activity){

        val userProfile = EazyRantoApplication.profileData

        userProfile?.let { 
        context.findViewById<TextView>(R.id.tv_merchant_name).text =it.full_name.capitalize()
            context.findViewById<TextView>(R.id.tv_user_type).text = Session.getInstance(context)?.getUserRole()?.capitalize()
            context.findViewById<TextView>(R.id.merchant_email).text=it.email

            val countryCode  = if(it.country_code==null) "" else it.country_code

            context.findViewById<TextView>(R.id.merchant_phone).text=countryCode.plus(it.mobile_number)

            context.findViewById<TextView>(R.id.merchant_address).text=it.address_info?.address_line.plus(" ").plus(it.address_info?.state).plus(" ").plus(it.address_info?.country)

        Picasso.with(context).load(it.profile_image).into(context.findViewById<ImageView>(R.id.profile_img))
      }
    }

    fun profileAPFromILogin(activity:FragmentActivity,loginUserStatus: LoginUserStatus){

        callProfileAPI(activity,object :ProfileDataAPILisetner{

            override fun onSuccessProfileDataAPI(userProfile: UserProfile?) {

                userProfile?.let {

                        //if user first time login
                        if (userFirstTime(it)) {
                            openUpdateProfileActivity(activity)
                            loginUserStatus.onUser(LoginUserStatus.UserStatus.FIRST_TIME)
                        } else {
                            loginUserStatus.onUser(LoginUserStatus.UserStatus.NOT_FIRST_TIME)
                        }

                    }
            }

        })
    }
      fun callProfileAPI(
         activity: FragmentActivity,
         profileDataAPILisetner: ProfileDataAPILisetner
     ){
        if(InternetNetworkConnection.isNetworkInternetAvailbale(activity)) {

            val livedata = LiveDataActivityClass(object : ApiResult {
                override fun <T> onSuccessApiResult(data: T) {

                    EazyRantoApplication.profileData = (data as ProfileModelReqRes).user_profile
                    profileDataAPILisetner.onSuccessProfileDataAPI(EazyRantoApplication.profileData)

                }

                override fun <T> statusCodeOfApi(data: T) {
                }

            })
            livedata.observeApiResult(
                livedata.callAPIActivity<ProfileUserViewModel>(activity).getProfileUser(),activity,activity
            )
        }
         else{
            Common.showToast(activity,R.string.CHECK_INTERNET)
        }
    }


    private fun userFirstTime(it: UserProfile):Boolean{

        if(it.address_info ==null || it.address_info.id ==null){
            return true
        }
        return false
    }

    private fun openUpdateProfileActivity(activity: Activity){

        val cls= when(Session.getInstance(EazyRantoApplication.context)?.getUserRole()){

            UserInfoAPP.CUSTOMER ->CustomerProfileUpdateActivity::class.java
            UserInfoAPP.AGENT -> AgentProfileUpdateActivity::class.java
            UserInfoAPP.MERCHANT -> MerchantProfileUpdateActivity::class.java
            else ->null

        }

        cls?.let {
            MoveToAnotherComponent.startActivityForResult(activity,it,Constant.REQUEST_CODE_FINISH_FIRST_TIME_USER,
                Constant.KEY_FINISH_FIRST_TIME_USER,Constant.VALUE_FINISH_FIRST_TIME_USER)
        }

    }


}
interface LoginUserStatus{
   enum class UserStatus{
       FIRST_TIME,NOT_FIRST_TIME
   }
    fun onUser(user:UserStatus)
}
interface ProfileDataAPILisetner{
    fun onSuccessProfileDataAPI(userProfile: UserProfile?)
}