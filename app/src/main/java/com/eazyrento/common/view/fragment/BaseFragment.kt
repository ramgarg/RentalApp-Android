package com.eazyrento.common.view.fragment

import androidx.fragment.app.Fragment
import com.eazyrento.InternetNetworkConnection
import com.eazyrento.ValidationMessage
import com.eazyrento.common.view.ApiResult
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.common.view.LiveDataFragmentClass
import com.eazyrento.customer.utils.Common

open abstract class BaseFragment:Fragment(), ApiResult,
    ViewClickOnFragment {

    override fun <T> onSuccessApiResult(data: T) {
    }

    override fun <T> statusCodeOfApi(data: T) {
    }

    protected fun callAPI(): LiveDataFragmentClass? {
        if (InternetNetworkConnection.isNetworkInternetAvailbale(requireContext())) {
            (requireActivity() as BaseActivity).showProgress()
            return LiveDataFragmentClass(this)
        }
        else{
            Common.showToast(requireActivity(),ValidationMessage.CHECK_INTERNET)
        }
        return null
    }

    override fun <T,K> onViewClick(type: T, where:K) {
    }

}
interface ViewClickOnFragment{
    fun <T,K>onViewClick(type:T,where:K)
}