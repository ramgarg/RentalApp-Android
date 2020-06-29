package com.eazyrento.common.view.fragment

import android.app.Activity
import android.app.Dialog
import android.view.Window
import androidx.fragment.app.Fragment
import com.eazyrento.InternetNetworkConnection
import com.eazyrento.R
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

    // show dailog
    protected fun showDialogCustomDialog(context: Activity): Dialog {
        val dialog = Dialog(context)

        dialog .requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent);
        dialog .setCancelable(false)
        dialog .setContentView(R.layout.thank_you_pop)

//        dialog.tv_msg.text=msg

        /*    dialog.btn_ok.setOnClickListener {
                dialog.cancel()
                this.onClickDailog(Constant.OK)
            }*/

        //dialog .show()
        return dialog

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