package com.rental.merchant.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rental.R
import com.rental.appbiz.AppBizLogger
import com.rental.common.view.fragment.BaseFragment
import com.rental.login.model.modelclass.ProfileModelReqRes
import com.rental.login.viewmodel.ProfileUserViewModel
import com.rental.merchant.view.activity.MerchantMainActivity
import kotlinx.android.synthetic.main.merchant_activity_main.*

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

    }
}