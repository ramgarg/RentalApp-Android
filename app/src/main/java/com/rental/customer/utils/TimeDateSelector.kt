package com.rental.customer.utils

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.rental.R
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.rating_review.*
import kotlinx.android.synthetic.main.thank_you_pop.*
import java.sql.Time
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

class TimeDateSelector {

    companion object {
        val MONTHS =
            arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
        val c = Calendar.getInstance()
        fun dateSelector(context: Context,txt:TextView) {


            val datePickerDialog = DatePickerDialog(
                context, R.style.TimePickerTheme,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    txt.setText(dayOfMonth.toString() + " " + MONTHS[(monthOfYear)] + " " + year) },
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
            datePickerDialog.show()
        }

        fun timeSelector(context: Context,txt:TextView){
            val timePickerDialog = TimePickerDialog(
                context,R.style.TimePickerTheme,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->

                    val tme = Time(hourOfDay, minute, 0) //seconds by default set to zero
                    val formatter: Format
                    formatter = SimpleDateFormat("h:mm a")

                    txt.setText(formatter.format(tme)) },
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),
                false
            )
            timePickerDialog.show()
        }
        fun showDialog(title: String,context: Activity,layout:Int) {
            val dialog = Dialog(context)
            dialog .requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent);
            dialog .setCancelable(false)
            dialog .setContentView(layout)

            if(title.equals("Payment"))
                thankYou(dialog)
            else
                rating(dialog)

            dialog .show()

        }

        private fun rating(dialog: Dialog){
            dialog.img_close.setOnClickListener { dialog.cancel() }
        }

        private fun thankYou(dialog: Dialog){
            dialog.btn_ok.setOnClickListener { dialog.cancel() }
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
    }


}