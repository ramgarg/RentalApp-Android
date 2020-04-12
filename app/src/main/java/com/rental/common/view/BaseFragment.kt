package com.rental.common.view

import androidx.fragment.app.Fragment
import com.rental.Env
import com.rental.Env.Companion.isNetworkConnect

open abstract class BaseFragment:Fragment(),ApiResult,ViewClickOnFragment{

    override fun <T> onSuccessApiResult(data: T) {
    }
    protected fun callAPI(): LiveDataFragmentClass? {
        if (isNetworkConnect) {
            (requireActivity() as BaseActivity).showProgress()
            return LiveDataFragmentClass(this)
        }
        return null
    }

    override fun <T,K> onViewClick(type: T, where:K) {
    }

}
interface ViewClickOnFragment{
    fun <T,K>onViewClick(type:T,where:K)
}