package com.rental.customer.utils

import android.content.Context
import android.content.Intent
import android.webkit.WebView
import android.webkit.WebViewClient
import com.rental.agent.view.activity.AgentHomeActivity
import com.rental.agent.view.activity.AgentProfileActivity
import com.rental.customer.dashboard.view.activity.*
import com.rental.login.view.ForgotPasswordActivity
import com.rental.login.view.OTPActivity
import com.rental.login.view.RegistrationActivity
import com.rental.customer.myaddress.view.AddNewAddressActivity
import com.rental.customer.myaddress.view.MyAddressActivity
import com.rental.customer.notification.view.NotificationActivity
import com.rental.customer.payment.view.PaymentHistoryActivity
import com.rental.customer.profile.ProfileActivity
import com.rental.customer.webpages.AboutActivity
import com.rental.customer.webpages.TermsConditionActivity
import com.rental.merchant.view.activity.MerchantProfileActivity
import com.rental.merchant.view.activity.MerchantAddVehicle
import com.rental.merchant.view.activity.MerchantMainActivity


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
            context.startActivity(Intent(context, RegistrationActivity::class.java))
        }

        fun moveToOTPActivity(context: Context){
            context.startActivity(Intent(context, OTPActivity::class.java))
        }

        fun moveToMyAddressActivity(context: Context){
            context.startActivity(Intent(context, MyAddressActivity::class.java))
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
            context.startActivity(Intent(context, MainActivity::class.java))
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


        fun moveToOrderReviewActivity(context: Context){
            context.startActivity(Intent(context, OrderReviewActivity::class.java))
        }

        fun moveToNotifyAdminActivity(context: Context){
            context.startActivity(Intent(context, NotifyToAdminActivity::class.java))
        }

        fun moveToPaymentActivity(context: Context){
            context.startActivity(Intent(context, PaymentActivity::class.java))
        }
        fun moveToViewDetailsActivity(context: Context){
            context.startActivity(Intent(context, ViewDetailActivity::class.java))
        }

        fun moveToBookingDetailsActivity(context: Context){
            context.startActivity(Intent(context, BookingDetailsActivity::class.java))
        }

        fun moveToCategoryActivity(context: Context){
            context.startActivity(Intent(context, CategoryActivity::class.java))
        }

        fun openWebPage(webView: WebView){
            webView.setWebViewClient(WebViewClient())
            webView.getSettings().setJavaScriptEnabled(true)
            webView.getSettings().setDomStorageEnabled(true)
            webView.setOverScrollMode(WebView.OVER_SCROLL_NEVER)
//            webView.loadUrl("https://www.google.com")
            webView.loadUrl ( "file:///android_asset/privacy.html" );
        }
    }

}