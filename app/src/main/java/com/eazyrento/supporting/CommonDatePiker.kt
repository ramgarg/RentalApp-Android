package com.eazyrento.supporting

import android.app.DatePickerDialog
import android.content.Context
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class CommonDatePiker(private val context: Context) {

    private val currentDate = Calendar.getInstance()
    private var mYear: Int = 0
    private var mMonthOfYear: Int = 0
    private var mDayOfMonth: Int = 0

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

    fun currentDate():CommonDatePiker {

        mYear = currentDate.get(Calendar.YEAR)
        mMonthOfYear = currentDate.get(Calendar.MONTH)
        mDayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH)

        return this

    }

    fun createDatePicker(enumDateType: EnumDateType, dateListener: OnSelectDate):CommonDatePiker {

        this.enumDateType = enumDateType
        this.dateListener = dateListener

         datePickerDialog = DatePickerDialog(
            context,
            R.style.TimePickerTheme,
            selectDatePiker,
            mYear,
            mMonthOfYear,
            mDayOfMonth
        )
        return this
    }

    private fun setCalenderLimit(min: Int, max: Int) {

        val c = Calendar.getInstance()

        c.add(Calendar.YEAR,min)
        datePickerDialog.datePicker.minDate = c.timeInMillis

        c.add(Calendar.YEAR,max)
        datePickerDialog.datePicker.maxDate = c.timeInMillis
    }
    fun dobPiker():CommonDatePiker{

        setCalenderLimit(DateConstant.MIN_AGE,DateConstant.MAX_AGE)

        return this
    }

    fun bookingDatePiker():CommonDatePiker{

        if (enumDateType==EnumDateType.BOOKING_END_DATE){

            val c = Calendar.getInstance()

            c.set(mYear,mMonthOfYear,mDayOfMonth)

            datePickerDialog.datePicker.minDate = c.timeInMillis

            c.add(Calendar.YEAR,DateConstant.MAX_BOOKING)
            datePickerDialog.datePicker.maxDate = c.timeInMillis
        }
        else{
            setCalenderLimit(DateConstant.MIN_BOOKING,DateConstant.MAX_BOOKING)
        }

        return this
    }
    fun bookingEndDateTime(year: Int, month: Int, day: Int):CommonDatePiker{

        this.mYear = year
        this.mMonthOfYear =month-1
        this.mDayOfMonth = day

        return this
    }

    fun show(){
        datePickerDialog.show()
    }

//set Date in server format for sending
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

    fun getDateInDisplayFormat(year:Int, monthOfYear:Int, dayOfMonth:Int):String{

        return getDisplayDate(year,monthOfYear,dayOfMonth)
         /*val c = Calendar.getInstance()
         c.set(year,monthOfYear,dayOfMonth)

         val sdf = SimpleDateFormat(DateConstant.DISPLAY_FORMAT)

         val date = sdf.format(c.timeInMillis)

        return date*/
    }

    fun calculateDatesDiffWithString(startDate:String, endDate:String):Long{
        val myFormat = SimpleDateFormat(DateConstant.SEVER_FORMAT)

        try {
            val startDate = myFormat.parse(startDate)
            val endDate = myFormat.parse(endDate)

            val diff = endDate.time - startDate.time

            return(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS))

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return -1
    }

    fun diffranceBetweenCureentTimeAndPreviousTimeSameDate(todayDate:String,previousTime:String): Boolean {
        try {
            val currentDateTime = Calendar.getInstance()
            val todatDatePrevTime = Calendar.getInstance()

            val prev_time_list = previousTime.split(":")
            val todayDate = todayDate.split("-")

            todatDatePrevTime.set(Calendar.HOUR_OF_DAY,prev_time_list[0].toInt())
            //todatDatePrevTime.set(Calendar.MINUTE,prev_time_list[1].toInt())

            todatDatePrevTime.set(Calendar.YEAR,todayDate[0].toInt())
            todatDatePrevTime.set(Calendar.MONTH,todayDate[1].toInt()-1)
            todatDatePrevTime.set(Calendar.DAY_OF_MONTH,todayDate[2].toInt())

            val diff = todatDatePrevTime.timeInMillis-currentDateTime.timeInMillis

            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"same date time diff:"+diff)

            return diff<0

        }catch (e:Exception){
            e.printStackTrace()
        }
        return false
    }

}

 fun splitDateServerFormat(date:String?):List<String>?{
     if (date==null)
         return null
    return date.split("-")
}
fun convertToDisplayDate(list: List<String>?):String{
    if (list==null)
        return ""
    try {
        return getDisplayDate(list[0].toInt(),list[1].toInt()-1,list[2].toInt())
    }catch (e:Exception){
        e.printStackTrace()
    }
    return ""
}


fun getDisplayDate(year:Int, monthOfYear:Int, dayOfMonth:Int):String{

    val c = Calendar.getInstance()
    c.set(year,monthOfYear,dayOfMonth)

    val sdf = SimpleDateFormat(DateConstant.DISPLAY_FORMAT)

    val date = sdf.format(c.timeInMillis)

    return date
}

interface OnSelectDate {
    fun onDate(dateType: EnumDateType, year: Int, month: Int, day: Int)
}

enum class EnumDateType {
    DOB, BOOKING_START_DATE,BOOKING_END_DATE
}
interface DateConstant{
    companion object{
        const val MIN_AGE =-70
        const val MAX_AGE = 52

        const val MIN_BOOKING =0
        const val MAX_BOOKING =1

        const val DISPLAY_FORMAT ="dd-MMM-yyyy"
        const val SEVER_FORMAT ="yyyy-MM-dd"


    }
}