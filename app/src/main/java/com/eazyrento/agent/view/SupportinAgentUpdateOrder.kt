package com.eazyrento.agent.view

import android.content.res.Resources
import android.widget.TextView
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.agent.model.modelclass.AgentUpdateOrderReqModel
import com.eazyrento.agent.view.activity.AgentOrderSummaryActivity
import com.eazyrento.customer.dashboard.model.modelclass.OrderDetailsResModel
import com.eazyrento.customer.utils.Common.Companion.showToast
import com.eazyrento.supporting.*
import kotlinx.android.synthetic.main.order_summary_template.layout_end_date
import kotlinx.android.synthetic.main.order_summary_template.layout_end_time
import kotlinx.android.synthetic.main.order_summary_template.layout_st_date
import kotlinx.android.synthetic.main.order_summary_template.layout_st_time
import kotlinx.android.synthetic.main.order_summary_template.tv_end_date_sel
import kotlinx.android.synthetic.main.order_summary_template.tv_end_time_sel
import kotlinx.android.synthetic.main.order_summary_template.tv_st_date_sel
import kotlinx.android.synthetic.main.order_summary_template.tv_st_time_sel
import kotlinx.android.synthetic.main.template_work_info.*

class SupportinAgentUpdateOrder(val activity: AgentOrderSummaryActivity) {

    private val commonDatePiker = CommonDatePiker(activity)
    private val mCommonTimePiker = CommonTimePiker(activity)

    private var mIsClickedUpdateButton = false

    fun init(orderRes:OrderDetailsResModel) {

        clickListenerOnViews()

        activity.checkbox_with_driver.isClickable = false

        orderRes.product_detail?.let {
            activity.tv_st_date_sel.tag = it.start_date
            activity.tv_end_date_sel.tag = it.end_date
            activity.tv_st_time_sel.tag = it.start_time
            activity.tv_end_time_sel.tag = it.end_time
        }
    }

    private fun clickListenerOnViews(){
        activity.layout_st_date.setOnClickListener {

            isClickedOnUpdateButton(true)

            commonDatePiker.currentDate()
            bookingDatePicker(activity.tv_st_date_sel, EnumDateType.BOOKING_START_DATE)
        }

        activity.layout_end_date.setOnClickListener {
            try {
                isClickedOnUpdateButton(true)

                val list = activity.tv_st_date_sel.tag.toString().split("-")

                commonDatePiker.bookingEndDateTime(list[0].toInt(),list[1].toInt(),list[2].toInt())
                bookingDatePicker(activity.tv_end_date_sel, EnumDateType.BOOKING_END_DATE)

            }catch (e:java.lang.Exception){
                showToast(activity,R.string.START_DATE)
                e.printStackTrace()
            }
        }

        // time set
        activity.layout_st_time.setOnClickListener {
            isClickedOnUpdateButton(true)
            mCommonTimePiker.currentTime()
            bookingTimePicker(activity.tv_st_time_sel, TimeConstant.TimeTypeEnum.START_TIME)
        }

        activity.layout_end_time.setOnClickListener {
            isClickedOnUpdateButton(true)
            mCommonTimePiker.currentTime()
            bookingTimePicker(activity.tv_end_time_sel, TimeConstant.TimeTypeEnum.END_TIME)
        }

    }

    private fun isClickedOnUpdateButton(boolean: Boolean){
        mIsClickedUpdateButton =boolean
    }

    private fun bookingDatePicker(
        textView: TextView,
        bookingDate: EnumDateType
    ){
        commonDatePiker.createDatePicker(bookingDate, object : OnSelectDate {
            override fun onDate(dateType: EnumDateType, year: Int, month: Int, day: Int) {

                textView.tag = commonDatePiker.getDateInServerFormate(year,month,day)
                textView.text = commonDatePiker.getDateInDisplayFormat(year,month-1,day)
            }
        }).bookingDatePiker().show()
    }

    private fun bookingTimePicker(
        textView: TextView,
        time: TimeConstant.TimeTypeEnum
    ){
        mCommonTimePiker.createTimePiker(time, object : TimeListener {

            override fun onTime(timeType: TimeConstant.TimeTypeEnum, hour_of_day: Int, min: Int) {

                textView.tag = mCommonTimePiker.getServerTimeFormat(hour_of_day,min,0)
                textView.text = mCommonTimePiker.getDisplayTimeFormat(hour_of_day,min,0)
            }
        }).show()
    }

    fun onUpdateClick(updateOrder:UpdateOrderInterface){
        if (mIsClickedUpdateButton.not()){
            showToast(activity,R.string.ORDER_UPDATE_BUTTON_CLICK_CONFIRM)
            return
        }
        if (validationCorrect()) {

            //updateOrderByID(intent.getIntExtra(Constant.KEY_ORDER_DETAILS_ID, -1))
            updateOrder.onOrderUpdate(updateOrderBookingObject())
        }
    }

    private fun validationCorrect():Boolean{
        // end date can not be less then start date and calclulate days
        var bookingDays = 0L;
        return(when {

            activity.tv_st_date_sel.tag.toString().isEmpty()->{showToast(activity,R.string.START_DATE)
                false }
            activity.tv_st_time_sel.tag.toString().isEmpty()->{ showToast(activity,R.string.START_TIME)
                false }
            activity.tv_end_date_sel.tag.toString().isEmpty()->{ showToast(activity,R.string.END_DATE)
                false }
            activity.tv_end_time_sel.tag.toString().isEmpty()->{ showToast(activity,R.string.END_TIME)
                false }

            commonDatePiker.calculateDatesDiffWithString(
                activity.tv_st_date_sel.tag.toString(),
                activity.tv_end_date_sel.tag.toString()
            ).let {
                bookingDays = it
                bookingDays
            } < 0L -> {
                showToast(activity,R.string.DATE_VALIDATION)
                false
            }

            //same time and end time diffrance calculation
            bookingDays == 0L && mCommonTimePiker.isValidatedForXHour(
                activity.tv_st_date_sel.tag.toString(),activity.tv_st_time_sel.tag.toString(),
                activity.tv_end_date_sel.tag.toString(),activity.tv_end_time_sel.tag.toString()
            ).not() -> {
               // showToast(activity,ValidationMessage.SAME_DATE_TIME_VALIDATION)
                false
            }
            else ->true
        })

    }

    private fun updateOrderBookingObject(): AgentUpdateOrderReqModel{
        return AgentUpdateOrderReqModel(activity.tv_st_date_sel.tag.toString(),activity.tv_st_time_sel.tag.toString(),
            activity.tv_end_date_sel.tag.toString(),activity.tv_end_time_sel.tag.toString())

        //it.with_driver = checkbox_with_driver.isSelected

    }
}

interface UpdateOrderInterface{
    fun onOrderUpdate(orderUpdate: AgentUpdateOrderReqModel)
}