package com.eazyrento.common.view

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.eazyrento.Constant
import com.eazyrento.InternetNetworkConnection
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.merchant.view.activity.MerchantAddVehicleActivity
import kotlinx.android.synthetic.main.thank_you_pop.*
import kotlinx.android.synthetic.main.toolbar.*


open abstract class BaseActivity: AppCompatActivity(),
    ApiResult,
    ClickDailogListener {
    private var dialogProgrss: Dialog? =null

    abstract fun <T>moveOnSelecetedItem(type:T)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
           try {
               requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
           }catch (e:Exception){
               e.printStackTrace()
           }


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
        dialog .setCancelable(false)
        dialog .setContentView(layout)

        dialog.btn_cancel.visibility = View.VISIBLE

        dialog.tv_msg.text=msg

        dialog.btn_ok.setOnClickListener {
            dialog.cancel()
            this.onClickDailog(Constant.OK)
        }
        dialog.btn_cancel.setOnClickListener {
            dialog.cancel()
            this.onClickDailog(Constant.CANCEL)
        }


        /*if(title.equals("Payment"))
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

        if(InternetNetworkConnection.isNetworkInternetAvailbale(this)) {
            showProgress()
            return LiveDataActivityClass(this)
        }
        else{
            showToast(ValidationMessage.CHECK_INTERNET)
        }
        return null
    }

    override fun <T> onSuccessApiResult(data: T) {

    }
    override fun <T> statusCodeOfApi(data: T) {
    }

    override fun onClickDailog(int: Int) {
    }

    //dismissed progrss barr before leave the activity....
    override fun onDestroy() {
        dialogProgrss?.dismiss()
        super.onDestroy()
    }

    //dismissed progrss barr before Pause the activity....
    override fun onPause() {
        dialogProgrss?.dismiss()
        super.onPause()
    }

    //finish current activity
    protected fun finishCurrentActivity(flag:Int)
    {
        finish()
    }

    //finish current activity with Result
    protected fun finishCurrentActivityWithResult(code:Int,data:Intent)
    {
        setResult(Activity.RESULT_OK, data)
        finishCurrentActivity(code)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    /*Top bar setting*/
    protected fun topBarWithMenuIconAndNotificationWithTitleMessage(title: String){

        img_menu.visibility = View.VISIBLE
        img_notification.visibility = View.VISIBLE

        toolbar_title.text=title

        img_back.visibility = View.GONE


    }

    protected fun topBarWithBackIconAndTitle(title: String) {
        img_back.visibility = View.VISIBLE
        img_menu.visibility = View.GONE
        img_notification.visibility = View.GONE

        toolbar_title.text=title

        img_back.setOnClickListener { backImageIconClick(1) }
    }

    protected fun backImageIconClick(flag: Int){
        finishCurrentActivity(flag)
    }
}

interface ClickDailogListener{
    fun onClickDailog(int: Int)
}
