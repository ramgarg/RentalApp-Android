package com.rental.customer.utils

import android.content.Context
import android.content.Intent
import android.webkit.WebView
import android.webkit.WebViewClient
import com.rental.customer.dashboard.view.activity.HomeActivity
import com.rental.customer.login.view.ForgotPasswordActivity
import com.rental.customer.login.view.OTPActivity
import com.rental.customer.login.view.RegistrationActivity
import com.rental.customer.myaddress.view.AddNewAddressActivity
import com.rental.customer.myaddress.view.EditAddressActivity
import com.rental.customer.myaddress.view.MyAddressActivity
import com.rental.customer.payment.view.PaymentHistoryActivity
import com.rental.customer.profile.ProfileActivity
import com.rental.customer.webpages.AboutActivity
import com.rental.customer.webpages.TermsConditionActivity


class MoveToActivity {

    companion object{

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

        fun moveToEditAddressActivity(context: Context){
            context.startActivity(Intent(context, EditAddressActivity::class.java))
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
            context.startActivity(Intent(context, HomeActivity::class.java))
        }

        fun moveToProfileActivity(context: Context){
            context.startActivity(Intent(context, ProfileActivity::class.java))
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