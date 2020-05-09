package com.eazyrento.customer.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.webkit.WebView
import android.webkit.WebViewClient
import com.eazyrento.EazyRantoApplication
import com.eazyrento.ValidationMessage
import com.eazyrento.agent.view.activity.*
import com.eazyrento.customer.dashboard.view.activity.*
import com.eazyrento.customer.notification.view.NotificationActivity
import com.eazyrento.customer.payment.view.PaymentHistoryActivity
import com.eazyrento.customer.webpages.AboutActivity
import com.eazyrento.customer.webpages.TermsConditionActivity
import com.eazyrento.login.view.LoginUserActivity
import com.eazyrento.merchant.view.activity.*


class MoveToAnotherComponent {

    companion object{

        
        fun moveToMerchantMainActivity(context: Context){
            context.startActivity(Intent(context, MerchantMainActivity::class.java))
        }

        fun moveToAboutActivity(context: Context){
            context.startActivity(Intent(context, AboutActivity::class.java))
        }
        fun moveToTermsActivity(context: Context){
            context.startActivity(Intent(context, TermsConditionActivity::class.java))
        }

        fun moveToPaymentHistoryActivity(context: Context){
            context.startActivity(Intent(context, PaymentHistoryActivity::class.java))
        }

        fun moveToAgentUpdateOrderSummaryActivity(context: Context){
            context.startActivity(Intent(context, AgentUpdateOrderActivity::class.java))
        }

        fun moveToHomeActivity(context: Context){
            context.startActivity(Intent(context, CustomerMainActivity::class.java))
        }
        // agent activity
        fun moveToAgentHomeActivity(context: Context){
            context.startActivity(Intent(context, AgentMainActivity::class.java))
        }

        fun moveToNotificationActivity(context: Context){
            context.startActivity(Intent(context, NotificationActivity::class.java))
        }

        fun moveToNotifyAdminActivity(context: Context){
            context.startActivity(Intent(context, NotifyToAdminActivity::class.java))
        }


        fun moveToCategoryActivity(context: Context){
            context.startActivity(Intent(context, ProductCategoryActivity::class.java))
        }


        fun openWebPage(webView: WebView){
            webView.setWebViewClient(WebViewClient())
            webView.getSettings().setJavaScriptEnabled(true)
            webView.getSettings().setDomStorageEnabled(true)
            webView.setOverScrollMode(WebView.OVER_SCROLL_NEVER)
//            webView.loadUrl("https://www.google.com")
            webView.loadUrl ( "file:///android_asset/privacy.html" );
        }

        // start activity for result
        //normal

        inline fun <reified T>startActivityForResult(activity: Activity,requestCode: Int,key:String,value: Int) {
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

        // log out user

        fun onLogout(context: Context, key: String,value: Int) {

            Common.showToast(context,ValidationMessage.LOGOUT_MESSAGE)

            EazyRantoApplication.onLogoutUpdateSession()

            val intent = Intent(context, LoginUserActivity::class.java)

            intent.putExtra(key,value)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }

        inline fun <reified T>moveToActivityWithIntentValue(context: Context, key: String, value: Int) {
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