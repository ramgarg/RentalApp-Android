package com.eazyrento.supporting

import android.app.TimePickerDialog
import android.content.Context
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import java.lang.Exception
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CommonTimePiker(private val context: Context) {

    private var mHourOfDay:Int =0
    private var mMinutes:Int = 0
    private lateinit var mTimePickerDialog :TimePickerDialog
    private lateinit var mTimeListenerInterface:TimeListener
    private lateinit var mTimeType: TimeConstant.TimeTypeEnum

  private val mTimeListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->

        /*val tme = Time(hourOfDay, minute, 0) //seconds by default set to zero
        //val formatter: Format
      val  formatter = SimpleDateFormat(TimeConstant.TIME_FORMAT)
        val time = formatter.format(tme)*/
      mTimeListenerInterface.onTime(mTimeType,hourOfDay,minute)
    }

    fun createTimePiker(timeTypeEnum: TimeConstant.TimeTypeEnum,time: TimeListener):CommonTimePiker{

        mTimeListenerInterface = time
        mTimeType = timeTypeEnum

        mTimePickerDialog = TimePickerDialog(
            context, R.style.TimePickerTheme,
            mTimeListener,
            mHourOfDay,
            mMinutes,
            TimeConstant.is24HourView
        )
        return this
     }
    fun show(){
        mTimePickerDialog.show()
    }

    fun currentTime():CommonTimePiker{
        val c = Calendar.getInstance()


        mHourOfDay = c.get(Calendar.HOUR_OF_DAY)
        mMinutes = c.get(Calendar.MINUTE)

        return this
    }

    fun getTimeFormatByPattern(hourOfDay:Int,minute:Int,second:Int,pattern:String): String {
        return getTimeByPattern(hourOfDay,minute,second,pattern)
        /*val tme = Time(hourOfDay, minute, second) //seconds by default set to zero
        val  formatter = SimpleDateFormat(pattern)
        val time = formatter.format(tme)
        return time*/
    }
    fun getDisplayTimeFormat(hourOfDay:Int,minute:Int,second:Int):String{
        return getTimeFormatByPattern(hourOfDay,minute,second,TimeConstant.TIME_FORMAT_DISPLAY)
    }
    fun getServerTimeFormat(hourOfDay:Int,minute:Int,second:Int):String{
        return getTimeFormatByPattern(hourOfDay,minute,second,TimeConstant.TIME_FORMAT_SERVER)
    }
    fun isValidatedForXHour(startDate:String,startTime: String,endDate:String, endTime: String):Boolean{
        val startC = Calendar.getInstance()
        val endC = Calendar.getInstance()

        try {
            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"startTime-".plus(startTime).plus("-endTime-").plus(endTime))

            val st_list_time = startTime.split(":")
            val st_date_list = startDate.split("-")

            val end_list_time = endTime.split(":")
            val end_date_list  = endDate.split("-")


            startC.set(Calendar.YEAR,st_date_list[0].toInt())
            startC.set(Calendar.MONTH,st_date_list[1].toInt()-1)
            startC.set(Calendar.DAY_OF_MONTH,st_date_list[2].toInt())

            startC.set(Calendar.HOUR_OF_DAY,st_list_time[0].toInt())
            startC.set(Calendar.MINUTE,st_list_time[1].toInt())

            endC.set(Calendar.YEAR,end_date_list[0].toInt())
            endC.set(Calendar.MONTH,end_date_list[1].toInt()-1)
            endC.set(Calendar.DAY_OF_MONTH,end_date_list[2].toInt())

            endC.set(Calendar.HOUR_OF_DAY,end_list_time[0].toInt())
            endC.set(Calendar.MINUTE,end_list_time[1].toInt())

             val diff = endC.timeInMillis-startC.timeInMillis

            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"diff:".plus(diff))

             val diffESInMin = (diff/(1000*60))
             val gapInMin = TimeConstant.TIME_GAP_BETWEEN_SAME_DATE *60


            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"diff:".plus(diff).plus(",diffESInMin:")
                .plus(diffESInMin).plus(",gapInMin:".plus(gapInMin)))

            return diffESInMin>=gapInMin
            //return end_list[0].toInt() - st_list[0].toInt() >=1 && end_list[1].toInt() - st_list[1].toInt()>=0

        }catch (e:Exception){
            e.printStackTrace()
        }
        return false
    }

}
fun splitTimeServerFormat(time:String?):List<String>?{
    if (time==null)
        return null
    return time.split(":")
}

fun convertToDisplayTime(list: List<String>?):String{
    if (list==null)
        return ""
    try {

        return getTimeByPattern(list[0].toInt(),list[1].toInt()-1,list[1].toInt(),TimeConstant.TIME_FORMAT_DISPLAY)
    }catch (e:Exception){
        e.printStackTrace()
    }
    return ""
}

fun getTimeByPattern(hourOfDay: Int,minute: Int,second: Int,pattern: String): String {

    val tme = Time(hourOfDay, minute, second)
    val  formatter = SimpleDateFormat(pattern)
    val time = formatter.format(tme)
    return time

}

interface TimeConstant{
enum class TimeTypeEnum{
    START_TIME,END_TIME
}
    companion object{
        const val TIME_GAP_BETWEEN_SAME_DATE = 1  // hour
        const val TIME_FORMAT_SERVER="HH:mm:ss"
        const val TIME_FORMAT_DISPLAY = "HH:mm a"

        const val is24HourView = true
    }
}
interface TimeListener{
    fun onTime(timeTypeEnum: TimeConstant.TimeTypeEnum,hourOfDay:Int, minute:Int)
}