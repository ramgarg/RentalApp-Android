package com.eazyrento.customer.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.webkit.WebView
import android.webkit.WebViewClient
import com.eazyrento.agent.view.activity.*
import com.eazyrento.agent.view.fragment.AgentBookingsFragment
import com.eazyrento.customer.dashboard.view.activity.*
import com.eazyrento.login.view.ForgotPasswordActivity
import com.eazyrento.login.view.OTPActivity
import com.eazyrento.login.view.RegistrationUserActivity
import com.eazyrento.customer.myaddress.view.AddNewAddressActivity
import com.eazyrento.customer.myaddress.view.MyAddressListActivity
import com.eazyrento.customer.notification.view.NotificationActivity
import com.eazyrento.customer.payment.view.PaymentHistoryActivity
import com.eazyrento.customer.profile.ProfileActivity
import com.eazyrento.customer.webpages.AboutActivity
import com.eazyrento.customer.webpages.TermsConditionActivity
import com.eazyrento.merchant.view.activity.*


class MoveToAnotherComponent {

    companion object{

        fun moveToAgentProfileActivity(context: Context){
            context.startActivity(Intent(context, AgentProfileActivity::class.java))
        }

        fun moveToListAddressActivity(context: Context){
            context.startActivity(Intent(context, MyAddressListActivity::class.java))
        }

        fun moveToMerchantAddVehicle(context: Context){
            context.startActivity(Intent(context, MerchantAddVehicle::class.java))
        }

        fun moveToMerchantMainActivity(context: Context){
            context.startActivity(Intent(context, MerchantMainActivity::class.java))
        }

        fun moveToForgotPasswordActivity(context: Context){
            context.startActivity(Intent(context, ForgotPasswordActivity::class.java))
        }

        fun moveToRegistrationActivity(context: Context){
            context.startActivity(Intent(context, RegistrationUserActivity::class.java))
        }

        fun moveToOTPActivity(context: Context){
            context.startActivity(Intent(context, OTPActivity::class.java))
        }

        fun moveToMyAddressActivity(context: Context){
            context.startActivity(Intent(context, MyAddressListActivity::class.java))
        }

        fun moveToMerchantAddressActivity(context: Context){
            context.startActivity(Intent(context, MerchantAddressListActivity::class.java))
        }

        fun moveToMyNotesActivity(context: Context){
            context.startActivity(Intent(context, AgentAddNoteActivity::class.java))
        }

        fun moveToWriteNotesActivity(context: Context){
            context.startActivity(Intent(context, AgentWriteNoteActivity::class.java))
        }

        fun moveToAddNewAddressActivity(context: Context){
            context.startActivity(Intent(context, AddNewAddressActivity::class.java))
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
            context.startActivity(Intent(context, AgentHomeActivity::class.java))
        }

        fun moveToNotificationActivity(context: Context){
            context.startActivity(Intent(context, NotificationActivity::class.java))
        }

        fun moveToMerchantProfileActivity(context: Context){
            context.startActivity(Intent(context, MerchantProfileActivity::class.java))
        }

        fun moveToProfileActivity(context: Context){
            context.startActivity(Intent(context, ProfileActivity::class.java))
        }

       /* fun moveToOrderSummaryActivity(context: Context){
            context.startActivity(Intent(context, OrderSummaryActivity::class.java))
        }*/

        fun moveToAgentOrderSummaryActivity(context: Context){
            context.startActivity(Intent(context, AgentOrderSummaryActivity::class.java))
        }

        fun moveToAgentBookingsFragment(context: Context){
            context.startActivity(Intent(context, AgentBookingsFragment::class.java))
        }


        fun moveToMerchantOrderSummaryActivity(context: Context){
            context.startActivity(Intent(context, MerchantOrderSummaryActivity::class.java))
        }

        fun moveToOrderReviewActivity(context: Context){
            context.startActivity(Intent(context, CustomerBookingSubmitReviewActivity::class.java))
        }

        fun moveToNotifyAdminActivity(context: Context){
            context.startActivity(Intent(context, NotifyToAdminActivity::class.java))
        }

        fun moveToPaymentActivity(context: Context){
            context.startActivity(Intent(context, PaymentActivity::class.java))
        }

        fun moveToBookingDetailsActivity(context: Context){
            context.startActivity(Intent(context, CustomerBookingDetailsActivity::class.java))
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

        inline fun <reified T>moveToLogout(context: Context, key: String,value: Int) {
            val intent = Intent(context, T::class.java)
            intent.putExtra(key,value)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }

        inline fun <reified T>moveToActivity(context: Context, key: String, value: Int) {
            val intent = Intent(context, T::class.java)
            intent.putExtra(key,value)
            context.startActivity(intent)
        }

    }

}