package com.eazyrento.common.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eazyrento.*
import com.eazyrento.common.model.modelclass.LangaugeChangeReqModel
import com.eazyrento.common.viewmodel.LanguageChangeViewModel
import com.eazyrento.login.viewmodel.LoginUserViewModel
import com.eazyrento.supporting.LocalManager
import kotlinx.android.synthetic.main.thank_you_pop.*
import kotlinx.android.synthetic.main.toolbar.*


open abstract class BaseActivity: AppCompatActivity(),
    ApiResult,
    ClickDailogListener {

    private var dialogProgrss: Dialog? =null

    abstract fun <T>moveOnSelecetedItem(type:T)

    override fun attachBaseContext(newBase: Context?) {

        val language =  Session.getInstance(newBase)?.getLocalLanguage()

        if (language!=null)
            super.attachBaseContext(LocalManager.updateResources(newBase!!,language))
        else
            super.attachBaseContext(newBase)

    }
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
       //dialogProgrss?.window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
       //dialogProgrss?.requestWindowFeature(Window.FEATURE_NO_TITLE)
       dialogProgrss?.window?.setBackgroundDrawable(ColorDrawable(0))
       dialogProgrss?.setContentView(R.layout.progress_bar_api)
       val lp = WindowManager.LayoutParams()
       lp.copyFrom(dialogProgrss?.window?.attributes)
       lp.width = WindowManager.LayoutParams.WRAP_CONTENT
       lp.height = WindowManager.LayoutParams.WRAP_CONTENT
       dialogProgrss?.window?.attributes = lp

       dialogProgrss?.setCancelable(true)

   }
    fun showProgress(){

        if(dialogProgrss == null){
            setProgressBar()
        }
        dialogProgrss?.let {
            if (it.isShowing.not())
                it.show()
        }
    }
    fun hideProgress(){
        dismissProgress()
       /* dialogProgrss?.let {
            it.hide()
            it.dismiss()
            dialogProgrss =null
        }*/
    }

    fun dismissProgress(){
        dialogProgrss?.let {
            it.hide()
            it.dismiss()
            dialogProgrss =null
        }
    }
    protected fun showToast(msg: Int){
        Toast.makeText(this,msg, Toast.LENGTH_LONG).show()
    }

    protected fun showToastString(msg: String){
        Toast.makeText(this,msg, Toast.LENGTH_LONG).show()
    }
    protected fun showDialog(title: Int, msg:String, context: Activity, layout:Int):Dialog {
        val dialog = Dialog(context)
        dialog .requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent);
        dialog .setCancelable(false)
        dialog .setContentView(layout)

        //dialog.btn_cancel.visibility = View.VISIBLE

        dialog.tv_msg.text=msg

        dialog.btn_ok.setOnClickListener {
            dialog.cancel()
            this.onClickDailog(Constant.OK)
        }

        dialog .show()

        return dialog

    }

    protected fun showDialogCustomDialog(context: Activity):Dialog {
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


    //call api
    protected fun callAPI(): LiveDataActivityClass?{

        if(InternetNetworkConnection.isNetworkInternetAvailbale(this)) {
            showProgress()
            return LiveDataActivityClass(this)
        }
        else{
            showToast(R.string.CHECK_INTERNET)
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
        dismissProgress()
        super.onDestroy()
    }

    //dismissed progrss barr before Pause the activity....
    override fun onPause() {
        dismissProgress()
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

    fun restartCurrentActivity(){
        finish()
        startActivity(intent)

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

       /* img_notification.setOnClickListener { MoveToAnotherComponent.moveToActivityNormal<NotificationActivity>(this) }
        img_menu.setOnClickListener { drawer_layout.openDrawer(GravityCompat.START)}*/


    }

    protected fun topBarWithBackIconAndTitle(title: String) {
        img_back.visibility = View.VISIBLE
        img_menu.visibility = View.GONE
        img_notification.visibility = View.GONE

        toolbar_title.text=title

        if (title.equals(getString(R.string.view_product)))
         img_back.setOnClickListener { backImageIconClick(0) }
        else
            img_back.setOnClickListener { backImageIconClick(1) }
    }

    protected fun backImageIconClick(flag: Int){

        if (flag==0) {
            finishCurrentActivityWithResult(Activity.RESULT_OK, Intent())
            return

        }

        finishCurrentActivity(flag)
    }

    fun onChangeLanguage(lang:String){
        val userID = Session.getInstance(this)?.getUserID()
        val langaugeChangeReqModel  = LangaugeChangeReqModel(userID!!,lang)
        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<LanguageChangeViewModel>(this)
                    .setLanguage(langaugeChangeReqModel)
                , this, this
            )
        }
    }
}


interface ClickDailogListener{
    fun onClickDailog(int: Int)
}
