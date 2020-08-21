package com.eazyrento.customer.utils

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.eazyrento.R
import com.eazyrento.common.view.UserInfoAPP
import com.eazyrento.login.view.ChoseUserRole
import kotlinx.android.synthetic.main.rating_review.img_close
import kotlinx.android.synthetic.main.rental_dialog.*
import kotlinx.android.synthetic.main.thank_you_pop.*
import java.sql.Time
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class Common {
    //date format  Use one of these formats instead: YYYY-MM-DD.
    //time format hh:mm[:ss[.uuuuuu]]

    companion object {

        //val c = Calendar.getInstance()

        /*fun timeSelector(context: Context,txt:TextView){
            val timePickerDialog = TimePickerDialog(
                context,R.style.TimePickerTheme,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->

                    val tme = Time(hourOfDay, minute, 0) //seconds by default set to zero
                    val formatter: Format
                    //old format
//                    formatter = SimpleDateFormat("h:mm a")
                    //server format
                    formatter = SimpleDateFormat("hh:mm:ss")

                    txt.setText(formatter.format(tme)) },

                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),
                false
            )
            timePickerDialog.show()
        }*/

       /* fun calculateDatesDiffWithString(startDate:String, endDate:String):Long{
            val myFormat = SimpleDateFormat("yyyy-MM-dd")

            try {
                val date1 = myFormat.parse(startDate)
                val date2 = myFormat.parse(endDate)
                val diff = date2.time - date1.time

                    return(TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS))

            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            return -1
        }*/


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

    }




}