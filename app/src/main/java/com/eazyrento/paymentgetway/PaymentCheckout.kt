package com.eazyrento.paymentgetway

import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.eazyrento.Env
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.customer.dashboard.view.activity.CustomerPaymentActivity
import com.eazyrento.customer.payment.view.PaymentBaseActivity
import com.eazyrento.webservice.PathURL
import java.net.URLDecoder
import java.net.URLEncoder

class PaymentCheckout(obj:PaymentBaseActivity,webView: WebView,url:String,order_id:Int){

      init{
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,url)

        webView.run {
            visibility = View.VISIBLE
            loadUrl(url)
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                   AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,url)

                    if (url.contains(PathURL.PAYMENT_GETWAY_CALLBACK_URL)){
                        val res = url.removePrefix(Env.BASE_URL.plus(PathURL.PAYMENT_GETWAY_CALLBACK_URL).plus("$order_id/"))


                        visibility =View.GONE
                        obj.paymentGetwayCallback( URLDecoder.decode(res,"UTF-8"))

                    }
                }
            }

        }

      }

}