package com.rental.common.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.rental.Env
import com.rental.Env.Companion.isNetworkConnect
import com.rental.R
import com.rental.appbiz.retrofitapi.ApiObserver
import com.rental.appbiz.retrofitapi.ChangedListener
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.common.model.modelclass.ProductDetailsResModel
import com.rental.customer.utils.Common
import com.rental.login.model.modelclass.RegisterUserResModel
import kotlinx.android.synthetic.main.thank_you_pop.*
import retrofit2.Response


open abstract class BaseActivity: AppCompatActivity(),ApiResult,ClickDailogListener {
    private var dialog: Dialog? =null

    abstract fun <T>moveOnSelecetedItem(type:T)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    }

   fun setProgressBar(){
       dialog = Dialog(this)
       dialog?.window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
       dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
       dialog?.window?.setBackgroundDrawable(ColorDrawable(0))
       dialog?.setContentView(R.layout.progress_bar_api)
       val lp = WindowManager.LayoutParams()
       lp.copyFrom(dialog?.window?.attributes)
       lp.width = WindowManager.LayoutParams.MATCH_PARENT
       lp.height = WindowManager.LayoutParams.MATCH_PARENT
       dialog?.window?.attributes = lp

   }
    fun showProgress(){

        if(dialog == null){
            setProgressBar()
        }
        dialog?.show()
    }
    fun hideProgress(){

        dialog?.hide()
    }
    protected fun showToast(msg: String){
        Toast.makeText(this,msg, Toast.LENGTH_LONG).show()
    }
    protected fun showDialog(title: String, msg:String, context: Activity, layout:Int) {
        val dialog = Dialog(context)
        dialog .requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent);
        dialog .setCancelable(true)
        dialog .setContentView(layout)

        dialog.btn_ok.setOnClickListener {
            dialog.cancel()
            this.onClickDailog(1)
        }
        dialog.tv_msg.text=msg

       /* if(title.equals("Payment"))
            Common.thankYou(dialog, msg)
        else if(title.equals("UserType"))
            Common.userDialog(context, dialog)
        else if(title.equals("UserDay"))
            Common.userDayDialog(context, dialog)
        else
            Common.rating(dialog)*/

        dialog .show()

    }


    //call api
    protected fun callAPI():LiveDataActivityClass?{
        if(isNetworkConnect)
            return LiveDataActivityClass(this)
        return null
    }

    override fun <T> onSuccessApiResult(data: T) {

    }

    override fun onClickDailog(int: Int) {
    }


}

interface ClickDailogListener{
    fun onClickDailog(int: Int)
}