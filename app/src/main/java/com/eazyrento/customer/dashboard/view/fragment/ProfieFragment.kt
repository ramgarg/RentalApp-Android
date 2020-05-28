package com.eazyrento.customer.dashboard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.fragment.BaseFragment
import com.eazyrento.login.model.modelclass.ProfileModelReqRes
import com.eazyrento.login.view.ProfileData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*

open class ProfieFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

    val view = inflater.inflate(R.layout.fragment_profile, container, false)
//        (activity as MainActivity).layout_loading.visibility=View.GONE
        return view

}
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ProfileData().setData(requireActivity())

        /* callAPI()?.let {
             it.observeApiResult(
                 it.callAPIFragment<ProfileUserViewModel>(this).getProfileUser()
                 , viewLifecycleOwner, requireActivity()
             )
         }*/

    }
    override fun <T> onSuccessApiResult(data: T) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        val userProfile = data as ProfileModelReqRes

        tv_customer_name.text=userProfile.user_profile.full_name
        customer_email.text=userProfile.user_profile.email
        customer_phone.text=userProfile.user_profile.mobile_number
        customer_address.text=userProfile.user_profile.address_info.address_line+" "+userProfile.user_profile.address_info.state+" "+userProfile.user_profile.address_info.country

        Picasso.with(requireContext()).load(userProfile.user_profile.profile_image).into(img_profile)


    }


}
