package com.eazyrento.customer.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import com.eazyrento.EazyRantoApplication
import com.eazyrento.R
import com.eazyrento.login.view.LoginActivity
import java.util.ArrayList


class MoveToAnotherComponent {

    companion object{

        // start activity for result
        //normal int value

        inline fun <reified T>startActivityForResult(activity: Activity,requestCode: Int,key:String,value: Int) {
            val intent = Intent(activity, T::class.java)
            intent.putExtra(key,value)
            activity.startActivityForResult(intent,requestCode)
        }
        //string params value

        inline fun <reified T>startActivityForResult(activity: Activity,requestCode: Int,key:String,value: String) {
            val intent = Intent(activity, T::class.java)
            intent.putExtra(key,value)
            activity.startActivityForResult(intent,requestCode)
        }

        // without inline function
        fun <T>startActivityForResult(activity: Activity,cls:Class<T>,requestCode: Int,key:String,value: Int) {
            val intent = Intent(activity, cls)
            intent.putExtra(key,value)
            activity.startActivityForResult(intent,requestCode)
        }

        // Bollean value
        inline fun <reified T>startActivityForResult(activity: Activity,requestCode: Int,key:String,value: Boolean) {
            val intent = Intent(activity, T::class.java)
            intent.putExtra(key,value)
            activity.startActivityForResult(intent,requestCode)
        }

        //with parsble
        inline fun <reified T,K>startActivityResultWithParcelable(activity: Activity, key: String,type: K,requestCode: Int) {
            val intent = Intent(activity, T::class.java)
            intent.putExtra(key,type as Parcelable)
            activity.startActivityForResult(intent,requestCode)
        }

        inline fun <reified T,K>openActivityWithParcelableParam(context: Context, key: String,type: K) {
            val intent = Intent(context, T::class.java)
            intent.putExtra(key,type as Parcelable)
            context.startActivity(intent)
        }

        // send list to onter Actiivity
        inline fun <reified T>startActivityResultWithParcelableList(activity: Activity, key: String,type: ArrayList<Parcelable>,requestCode: Int) {
            val intent = Intent(activity, T::class.java)
            intent.putParcelableArrayListExtra(key,type)
            activity.startActivityForResult(intent,requestCode)
        }

        // log out user

        fun onLogout(context: Context, key: String,value: Int) {

            Common.showToast(context,R.string.LOGOUT_MESSAGE)

            EazyRantoApplication.onLogoutUpdateSession()

            val intent = Intent(context, LoginActivity::class.java)

            intent.putExtra(key,value)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }

        inline fun <reified T>moveToActivityWithIntentValue(context: Context, key: String, value: Int) {
            val intent = Intent(context, T::class.java)
            intent.putExtra(key,value)
            context.startActivity(intent)
        }
        //with string value
        inline fun <reified T>moveToActivityWithIntentValue(context: Context, key: String, value: String) {
            val intent = Intent(context, T::class.java)
            intent.putExtra(key,value)
            context.startActivity(intent)
        }
//without params open activity
        inline fun <reified T>moveToActivityNormal(context: Context) {
            val intent = Intent(context, T::class.java)
            context.startActivity(intent)
        }

    }

}