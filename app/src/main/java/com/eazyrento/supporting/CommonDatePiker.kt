package com.eazyrento.supporting

import android.app.DatePickerDialog
import android.content.Context
import android.widget.TextView
import com.eazyrento.R
import java.text.SimpleDateFormat
import java.util.*

class CommonDatePiker(private val context: Context) {

    private val currentDate = Calendar.getInstance()
    private var currentYear: Int = 0
    private var currentMonthOfYear: Int = 0
    private var currentDayOfMonth: Int = 0

    private lateinit var enumDateType: EnumDateType
    private lateinit var dateListener:OnSelectDate
    private lateinit var datePickerDialog:DatePickerDialog

    init {
        currentDate()
    }

    private val selectDatePiker = DatePickerDialog.OnDateSetListener {

            view, year, month, dayOfMonth ->

        dateListener.onDate(enumDateType, year, month+1, dayOfMonth)

    }

   private fun currentDate():CommonDatePiker {

        currentYear = currentDate.get(Calendar.YEAR)
        currentMonthOfYear = currentDate.get(Calendar.MONTH)
        currentDayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH)

        return this

    }

    fun createDatePicker(enumDateType: EnumDateType, dateListener: OnSelectDate):CommonDatePiker {

        this.enumDateType = enumDateType
        this.dateListener = dateListener

         datePickerDialog = DatePickerDialog(
            context,
            R.style.TimePickerTheme,
            selectDatePiker,
            currentYear,
            currentMonthOfYear,
            currentDayOfMonth
        )
        return this
    }

    fun dobPiker():CommonDatePiker{

        currentDate.add(Calendar.YEAR,-DateConstant.MIN_AGE)
        datePickerDialog.datePicker.maxDate = currentDate.timeInMillis
        currentDate.add(Calendar.YEAR,-DateConstant.MAX_DATE)
        datePickerDialog.datePicker.minDate = currentDate.timeInMillis

        return this
    }
    fun show(){
        datePickerDialog.show()
    }

//set Date in View
    fun getDateInServerFormate(year:Int, monthOfYear:Int, dayOfMonth:Int):String{

        return(
            "$year-" +(if(monthOfYear.compareTo(10)<0){
                "0$monthOfYear"
            }else monthOfYear)+""
                    + "-" + (if(dayOfMonth.compareTo(10)<0){
                "0$dayOfMonth"
            }else dayOfMonth))
    }
    //set 01-jan-1900

    fun getDateInDobFormat(year:Int, monthOfYear:Int, dayOfMonth:Int):String{
         val c = Calendar.getInstance()
         c.set(year,monthOfYear,dayOfMonth)
         val sdf = SimpleDateFormat(DateConstant.DOB_FORMAT)
        val date = sdf.format(c.timeInMillis)
        return date
    }
}



interface OnSelectDate {
    fun onDate(dateType: EnumDateType, year: Int, month: Int, day: Int)
}

enum class EnumDateType {
    DOB, START_DATE, END_DATE
}
interface DateConstant{
    companion object{
        const val MIN_AGE =18
        const val MAX_DATE =70

        const val DOB_FORMAT ="dd-MMM-yyyy"
    }
}