package com.rental.common.view

import androidx.fragment.app.Fragment
import com.rental.Constant
import com.rental.Env

open abstract class BaseFragment:Fragment(),ApiResult{
    override fun <T> onSuccessApiResult(data: T) {
    }
    protected fun callAPI(): LiveDataFragmentClass? {
        if (Env.isNetworkConnect)
            return LiveDataFragmentClass(this)
        return null
    }

}