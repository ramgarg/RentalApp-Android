package com.rental.common.view

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
import com.rental.R
import com.rental.appbiz.retrofitapi.ApiObserver
import com.rental.appbiz.retrofitapi.ChangedListener
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.login.model.modelclass.RegisterUserResModel
import retrofit2.Response


open abstract class BaseActivity: AppCompatActivity() {
    private var dialog: Dialog? =null

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

}