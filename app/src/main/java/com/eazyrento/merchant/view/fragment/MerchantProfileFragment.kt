package com.eazyrento.merchant.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.fragment.BaseFragment
import com.eazyrento.login.model.modelclass.ProfileModelReqRes
import com.eazyrento.login.viewmodel.ProfileUserViewModel
import kotlinx.android.synthetic.main.merchant_fragment_profile.*

class MerchantProfileFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.merchant_fragment_profile, container, false)

        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIFragment<ProfileUserViewModel>(this).getProfileUser()
                , viewLifecycleOwner, requireActivity()
            )
        }

    }

    override fun <T> onSuccessApiResult(data: T) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        val userProfile = data as ProfileModelReqRes
        tv_merchant_name.text=userProfile.user_profile.full_name
        merchant_email.text=userProfile.user_profile.email
        merchant_phone.text=userProfile.user_profile.mobile_number
        merchant_address.text=userProfile.user_profile.address_info.address_line+" "+userProfile.user_profile.address_info.state+" "+userProfile.user_profile.address_info.country

    }
}