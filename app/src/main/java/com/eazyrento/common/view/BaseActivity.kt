package com.eazyrento.common.view

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.pm.ActivityInfo
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eazyrento.Env.Companion.isNetworkConnect
import com.eazyrento.R
import kotlinx.android.synthetic.main.thank_you_pop.*


open abstract class BaseActivity: AppCompatActivity(),
    ApiResult,
    ClickDailogListener {
    private var dialogProgrss: Dialog? =null

    abstract fun <T>moveOnSelecetedItem(type:T)

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    }

   private fun setProgressBar(){
       dialogProgrss = Dialog(this)
       dialogProgrss?.window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
       dialogProgrss?.requestWindowFeature(Window.FEATURE_NO_TITLE)
       dialogProgrss?.window?.setBackgroundDrawable(ColorDrawable(0))
       dialogProgrss?.setContentView(R.layout.progress_bar_api)
       val lp = WindowManager.LayoutParams()
       lp.copyFrom(dialogProgrss?.window?.attributes)
       lp.width = WindowManager.LayoutParams.MATCH_PARENT
       lp.height = WindowManager.LayoutParams.MATCH_PARENT
       dialogProgrss?.window?.attributes = lp

   }
    fun showProgress(){

        if(dialogProgrss == null){
            setProgressBar()
        }
        dialogProgrss?.show()
    }
    fun hideProgress(){

        dialogProgrss?.hide()
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
    protected fun callAPI(): LiveDataActivityClass?{

        if(isNetworkConnect) {
            showProgress()
            return LiveDataActivityClass(this)
        }
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