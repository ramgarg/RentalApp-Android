package com.rental.customer.utils

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.webkit.WebView
import android.webkit.WebViewClient
import com.rental.Constant
import com.rental.agent.view.activity.*
import com.rental.agent.view.fragment.AgentBookingsFragment
import com.rental.customer.dashboard.view.activity.*
import com.rental.login.view.ForgotPasswordActivity
import com.rental.login.view.OTPActivity
import com.rental.login.view.RegistrationUserActivity
import com.rental.customer.myaddress.view.AddNewAddressActivity
import com.rental.customer.myaddress.view.MyAddressActivity
import com.rental.customer.notification.view.NotificationActivity
import com.rental.customer.payment.view.PaymentHistoryActivity
import com.rental.customer.profile.ProfileActivity
import com.rental.customer.webpages.AboutActivity
import com.rental.customer.webpages.TermsConditionActivity
import com.rental.merchant.view.activity.*


class MoveToAnotherComponent {

    companion object{

        fun moveToAgentProfileActivity(context: Context){
            context.startActivity(Intent(context, AgentProfileActivity::class.java))
        }

        fun moveToMerchantAddVehicle(context: Context){
            context.startActivity(Intent(context, MerchantAddVehicle::class.java))
        }

        fun moveToMerchantActivity(context: Context){
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
            context.startActivity(Intent(context, MyAddressActivity::class.java))
        }

        fun moveToMerchantAddressActivity(context: Context){
            context.startActivity(Intent(context, MerchantAddressActivity::class.java))
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

        fun moveToOrderSummaryActivity(context: Context){
            context.startActivity(Intent(context, OrderSummaryActivity::class.java))
        }

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
            context.startActivity(Intent(context, CustomerOrderReviewActivity::class.java))
        }

        fun moveToNotifyAdminActivity(context: Context){
            context.startActivity(Intent(context, NotifyToAdminActivity::class.java))
        }

        fun moveToPaymentActivity(context: Context){
            context.startActivity(Intent(context, PaymentActivity::class.java))
        }
        fun moveToViewDetailsActivity(context: Context){
            context.startActivity(Intent(context, ProductDetailsActivity::class.java))
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