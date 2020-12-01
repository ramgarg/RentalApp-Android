package com.eazyrento.customer.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.webkit.*
import android.widget.Toast
import androidx.databinding.library.BuildConfig
import com.eazyrento.*
import com.eazyrento.common.view.UserInfoAPP
import com.eazyrento.login.model.modelclass.AddressInfo
import com.eazyrento.login.view.ChoseUserRole
import com.eazyrento.supporting.LocalManager
import kotlinx.android.synthetic.main.rating_review.img_close
import kotlinx.android.synthetic.main.rental_dialog.*
import kotlinx.android.synthetic.main.thank_you_pop.*
import kotlinx.android.synthetic.main.view_map_top_location_card.view.*
import okhttp3.internal.userAgent
import java.util.*


class Common {
    //date format  Use one of these formats instead: YYYY-MM-DD.
    //time format hh:mm[:ss[.uuuuuu]]

    companion object {

        fun openWebPage(webView: WebView, url:String){
           // webView.setWebViewClient(WebViewClient())
            webView.getSettings().setJavaScriptEnabled(true)
            webView.getSettings().setDomStorageEnabled(true)
            webView.setOverScrollMode(WebView.OVER_SCROLL_NEVER)
//            webView.loadUrl("https://www.google.com")

            webViewWitheader(webView,url)
           // webView.loadUrl(url)
        }

        fun webViewWitheader(webView: WebView,url: String) {

            val mapHeader = HashMap<String, String>()

            val language = Session.getInstance(EazyRantoApplication.context)?.getLocalLanguage()

            mapHeader[PrefKey.LOCAL_LANGUAGE] = language?: LocalManager.english_lang_code

            webView.settings.userAgentString = String.format("%s [%s/%s]", webView.settings.userAgentString, "App Android", BuildConfig.VERSION_NAME)

            WebView.setWebContentsDebuggingEnabled(true)

            webView.webViewClient = object : WebViewClient() {
                override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest): WebResourceResponse? {
                    CookieManager.getInstance().removeAllCookies(null)
                    return super.shouldInterceptRequest(view, request)
                }
            }

            webView.loadUrl(url,mapHeader)
        }

        fun initDailog(context: Context,layout: Int):Dialog{
            val dialog = Dialog(context)
            dialog .requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent);
            dialog .setCancelable(true)
            dialog .setContentView(layout)
            return dialog
        }
        fun showDialog(title: String,msg:String,context: Activity,layout:Int) {

            val dialog = initDailog(context,layout)

            if(title.equals("payment_noti"))
                thankYou(
                    dialog,
                    msg
                )
            else if(title.equals("UserType"))
                userDialog(
                    context,
                    layout,
                    null
                )
            else if(title.equals("UserDay"))
                userDayDialog(
                    context,
                    dialog
                )
            else
                rating(dialog)

            dialog .show()

        }

        private fun userDayDialog(context: Activity, dialog: Dialog) {
            //dialog.img_close.setOnClickListener { dialog.cancel() }
           

        }

         fun userDialog(context: Context,layout: Int,choseUserRole: ChoseUserRole?):Dialog{

             val dialog = initDailog(context,layout)

            dialog.img_close.setOnClickListener { dialog.cancel() }

            dialog.btn_agent_inactive.setOnClickListener {
                dialog.btn_agent_active.visibility = View.VISIBLE
                dialog.btn_customer_active.visibility = View.INVISIBLE
                dialog.btn_merchant_active.visibility = View.INVISIBLE

                choseUserRole?.onChose(UserInfoAPP.AGENT)

               /* MoveToAnotherComponent.moveToAgentHomeActivity(
                    context
                )*/


            }
            dialog.btn_customer_inactive.setOnClickListener {
                dialog.btn_customer_active.visibility = View.VISIBLE
                dialog.btn_merchant_active.visibility = View.INVISIBLE
                dialog.btn_agent_active.visibility = View.INVISIBLE

                choseUserRole?.onChose(UserInfoAPP.CUSTOMER)
               /* MoveToAnotherComponent.moveToHomeActivity(
                    context
                )*/
            }
            dialog.btn_merchant_inactive.setOnClickListener {
                dialog.btn_merchant_active.visibility = View.VISIBLE
                dialog.btn_agent_active.visibility = View.INVISIBLE
                dialog.btn_customer_active.visibility = View.INVISIBLE

                choseUserRole?.onChose(UserInfoAPP.MERCHANT)

               /* MoveToAnotherComponent.moveToMerchantMainActivity(
                    context
                )*/
            }
             return dialog
        }

        private fun rating(dialog: Dialog){
            dialog.img_close.setOnClickListener { dialog.cancel() }
        }

        private fun thankYou(dialog: Dialog,msg: String){
            dialog.btn_ok.setOnClickListener {
                dialog.cancel()
            }
            dialog.tv_msg.text=msg
        }

        fun hideSoftKeyBoard(context: Context, view: View) {
            try {
                val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            } catch (e: Exception) {
                // TODO: handle exception
                e.printStackTrace()
            }

        }
        fun hideGroupViews(vararg view: View) {
            view.forEach {
                it.visibility=View.GONE
            }
        }

        fun invisibleGroupViews(vararg view: View) {
            view.forEach {
                it.visibility=View.INVISIBLE
            }
        }

        fun showGroupViews(vararg view: View) {
            view.forEach {
                it.visibility=View.VISIBLE
            }
        }

        fun showToast(context: Context,msg: Int){
            Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
        }

        fun showToastString(context: Context,msg: String){
            Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
        }

        fun phoneCallWithNumber(number:String?,context: Context){
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:"+number)
            context.startActivity(intent)
        }

        fun getUserType(context: Context):String{
            val type = Session.getInstance(context)?.getUserRole()
           return when(type){
               UserInfoAPP.CUSTOMER -> context.getString(R.string.customer)
               UserInfoAPP.MERCHANT -> context.getString(R.string.merchant)
               UserInfoAPP.AGENT -> context.getString(R.string.agent)
               else->{
                   ""
               }
           }

        }

        fun View.setCurrentAddressOnTopInMap(context: Context,lat:Double,lang:Double){
            val addressInfo = AddressInfo("","","","","",
                -1,false,0.0,0.0,"")

            // custom Address filter made by appbiz
            val addressFilter = AddressFilter(context,addressInfo)
            addressFilter.getAddressByLocation(lat,lang,1)

            tv_address_line_map.text = addressInfo.address_line
        }

        fun roundOfDouble(number:Double?):Double{
            try {
                return  String.format("%.3f", number).toDouble()
            }catch (e:Exception){

            }
            return number!!
        }

    }



}