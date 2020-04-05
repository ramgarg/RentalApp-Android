package com.rental.common.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.rental.R
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.login.model.modelclass.RegisterUserResModel


open abstract class BaseActivity: AppCompatActivity() {
    private var progressBar: ProgressBar? =null
    abstract fun showToast(msg:String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

   fun setProgressBar(){
       setContentView(R.layout.progress_bar_api)
   }
    fun showProgress(){
        if(progressBar == null){
            setProgressBar()
        }
        progressBar?.visibility = View.VISIBLE
    }
    fun hideProgress(){
        progressBar?.visibility = View.GONE
    }
    fun errorHandle(it: DataWrapper<RegisterUserResModel>) {

       if(it.error!=null){
           showToast(it.error.toString())
       }
        else if(it.apiException!=null){
           showToast(it.apiException.toString())
       }
    }
}