package com.eazyrento.agent.view.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.agent.viewmodel.AgentUpdateOrderViewModel
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.common.view.OrderBaseSummaryActivity
import com.eazyrento.customer.dashboard.model.modelclass.OrderDetailsResModel
import com.eazyrento.customer.dashboard.model.modelclass.MerchantDetail
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.supporting.*
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_agent_update_order_summary.*
import kotlinx.android.synthetic.main.order_summary_template.*
import kotlinx.android.synthetic.main.template_order_summery_top_view.*
import kotlinx.android.synthetic.main.template_update_quantity.view.*
import kotlinx.android.synthetic.main.template_update_quantity.view.add_quantity
import kotlinx.android.synthetic.main.template_update_quantity.view.item_quantity
import kotlinx.android.synthetic.main.template_update_quantity.view.minus_quantity
import kotlinx.android.synthetic.main.template_work_info.*
import kotlinx.android.synthetic.main.template_work_info.checkbox_with_driver
import kotlinx.android.synthetic.main.template_work_info.layout_end_date
import kotlinx.android.synthetic.main.template_work_info.layout_end_time
import kotlinx.android.synthetic.main.template_work_info.layout_st_date
import kotlinx.android.synthetic.main.template_work_info.layout_st_time
import kotlinx.android.synthetic.main.template_work_info.tv_end_date_sel
import kotlinx.android.synthetic.main.template_work_info.tv_end_time_sel
import kotlinx.android.synthetic.main.template_work_info.tv_st_date_sel
import kotlinx.android.synthetic.main.template_work_info.tv_st_time_sel

class AgentUpdateOrderActivity : OrderBaseSummaryActivity() {
   // private var mOrdersDetailsReqResModel:OrderDetailsResModel?=null
   private val commonDatePiker = CommonDatePiker(this)
   private val mCommonTimePiker = CommonTimePiker(this)

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_update_order_summary)

        customer_payment_button.text =resources.getString(R.string.update)

        customer_payment_button.visibility = View.INVISIBLE

        btn_update_agent_booking.setOnClickListener {
            onUpdateClick()
        }
        clickListenerOnViews()

        setDataAndCallOrderDetailsAPI(intent.getIntExtra(Constant.KEY_ORDER_DETAILS_ID,-1))

        topBarWithBackIconAndTitle(resources.getString(R.string.update_order))

        updateStatusSpinnerData()

        checkbox_with_driver.isClickable = true

    }

     private fun onUpdateClick(){
         if (validationCorrect()) {
             updateOrderBookingObject()
             updateOrderByID(intent.getIntExtra(Constant.KEY_ORDER_DETAILS_ID, -1))
         }
     }
    private fun updateOrderBookingObject(){
        orderRes.product_detail.start_date = tv_st_date_sel.tag.toString()
        orderRes.product_detail.start_time = tv_st_time_sel.tag.toString()

        orderRes.product_detail.end_date = tv_end_date_sel.tag.toString()
        orderRes.product_detail.end_time = tv_end_time_sel.tag.toString()

        orderRes.product_detail.with_driver = checkbox_with_driver.isSelected

    }
    private fun validationCorrect():Boolean{
        // end date can not be less then start date and calclulate days
        var bookingDays = 0L;
       return(when {

           tv_st_date_sel.tag.toString().isEmpty()->{showToast(ValidationMessage.START_DATE)
               false }
           tv_st_time_sel.tag.toString().isEmpty()->{ showToast(ValidationMessage.START_TIME)
               false }
           tv_end_date_sel.tag.toString().isEmpty()->{ showToast(ValidationMessage.END_DATE)
               false }
           tv_end_time_sel.tag.toString().isEmpty()->{ showToast(ValidationMessage.END_TIME)
               false }

            commonDatePiker.calculateDatesDiffWithString(
                tv_st_date_sel.tag.toString(),
                tv_end_date_sel.tag.toString()
            ).let {
                bookingDays = it
                bookingDays
            } < 0L -> {
                showToast(ValidationMessage.DATE_VALIDATION)
                false
            }

            //same time and end time diffrance calculation
            bookingDays == 0L && mCommonTimePiker.calculateTimeDiff(
                tv_st_time_sel.tag.toString(),
                tv_end_time_sel.tag.toString()
            ) -> {
                showToast(ValidationMessage.SAME_DATE_TIME_VALIDATION)
               false
            }
            else ->true
        })

    }

    private fun updateOrderByID(orderID:Int){
        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<AgentUpdateOrderViewModel>(this)
                    .updateOrder(orderID,orderRes)
                , this, this
            )
        }

    }


    private fun updateStatusSpinnerData() {
        val update_Status = resources.getStringArray(R.array.UpdateStatus)

        val spinner = findViewById<Spinner>(R.id.status_spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, update_Status)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if(position==0){

                    }
                    else{
                        orderRes.order_status = update_Status[position]
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }
    override fun <T> onSuccessApiResult(data: T) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())
        //updated data
        if (data is JsonElement){
            showToast(ValidationMessage.REQUEST_SUCCESSED)
            MoveToAnotherComponent.moveToActivityWithIntentValue<AgentMainActivity>(this,Constant.INTENT_UPDATE_ORDER_AGENT,1)
            return
        }
        super.onSuccessApiResult(data)

        tv_st_date_sel.tag = orderRes.product_detail.start_date
        tv_end_date_sel.tag = orderRes.product_detail.end_date
        tv_st_time_sel.tag = orderRes.product_detail.start_time
        tv_end_time_sel.tag = orderRes.product_detail.end_time

        merchatDataUpdate()
    }

    private fun merchatDataUpdate() {

        lyt_middle_view.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false
        )

        orderRes.let { lyt_middle_view.adapter = MerchantUpdateAdapter(this, it.merchant_detail,it)  }
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

    private fun clickListenerOnViews(){
        layout_st_date.setOnClickListener {
            commonDatePiker.currentDate()
            bookingDatePicker(tv_st_date_sel, EnumDateType.BOOKING_START_DATE)
        }

        layout_end_date.setOnClickListener {
            try {

                val list = tv_st_date_sel.tag.toString().split("-")

                commonDatePiker.bookingEndDateTime(list[0].toInt(),list[1].toInt(),list[2].toInt())
                bookingDatePicker(tv_end_date_sel, EnumDateType.BOOKING_END_DATE)

            }catch (e:java.lang.Exception){
                showToast(ValidationMessage.START_DATE)
                e.printStackTrace()
            }
        }

        // time set
        layout_st_time.setOnClickListener {
            mCommonTimePiker.currentTime()
            bookingTimePicker(tv_st_time_sel, TimeConstant.TimeTypeEnum.START_TIME)
        }

        layout_end_time.setOnClickListener {
            mCommonTimePiker.currentTime()
            bookingTimePicker(tv_end_time_sel, TimeConstant.TimeTypeEnum.END_TIME)
        }

    }
}

class MerchantUpdateAdapter(val activity:BaseActivity, val list:List<MerchantDetail>, val objMerc:OrderDetailsResModel):RecyclerView.Adapter<MerchantUpdateAdapter.CardViewHolder>(){

    class CardViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tv_name_user = view.tv_name_user
        val item_quantity = view.item_quantity
        val tv_total_booking_price = view.tv_total_booking_price
        val img_minus= view.minus_quantity
        val img_plus= view.add_quantity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
      return CardViewHolder(LayoutInflater.from(activity).inflate(R.layout.template_update_quantity,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = list[position]

        holder.tv_name_user.text = item.full_name
        holder.item_quantity.text = ""+item.quantity
        holder.tv_total_booking_price.text = ""+item.amount

        holder.img_minus.setOnClickListener{

            if (item.quantity-1 <=0){
                Common.showToast(activity,ValidationMessage.FILL_QUANTITY)
            }
           else {
                item.quantity = item.quantity-1
                holder.item_quantity.text = ""+item.quantity
            }
        }
        holder.img_plus.setOnClickListener{
            item.quantity = item.quantity+1
            holder.item_quantity.text = ""+item.quantity
        }
    }


}