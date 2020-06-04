package com.eazyrento.customer.dashboard.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.eazyrento.Constant
import com.eazyrento.EazyRantoApplication
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.model.modelclass.ProductDetailsResModel
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.dashboard.model.modelclass.CustomerCreateBookingReqModelItem
import com.eazyrento.customer.myaddress.view.MyAddressListActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.login.model.modelclass.AddressInfo
import com.eazyrento.supporting.*
import kotlinx.android.synthetic.main.activity_booking_details.*
import kotlinx.android.synthetic.main.template_product_main_view.*

class CustomerBookingDetailsActivity : BaseActivity() {

    private val objBookingReqModelItem = CustomerCreateBookingReqModelItem()
    private var mProductIDByIntent:Int =-1
    private val commonDatePiker = CommonDatePiker(this)
    private val mCommonTimePiker = CommonTimePiker(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_booking_details)

        topBarWithBackIconAndTitle(getString(R.string.booking_details))

        val addresss = EazyRantoApplication.profileData?.address_info

        addresss?.let { setAddressOnUI(it) }

        val prodDetailsObj = intent.getParcelableExtra<ProductDetailsResModel>(Constant.BOOKING_PRODECT_DETAILS)
        objBookingReqModelItem.projectDetails =prodDetailsObj
        mProductIDByIntent = prodDetailsObj.id

        prodDetailsObj?.let{setData(it)}
        clickListenerOnViews()

    }

    fun addAnotherBooking(view: View){
        MoveToAnotherComponent.moveToActivityNormal<CustomerMainActivity>(this)
    }

    private fun setData(prodDetailsObj: ProductDetailsResModel) {

        pro_booking_price.text = Constant.DOLLAR+prodDetailsObj.base_price
        pro_name.text = prodDetailsObj.name.capitalize()
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
        st_booking_date.setOnClickListener {
          //Common.dateSelector(this,tv_st_date_book)
            commonDatePiker.currentDate()
            bookingDatePicker(tv_st_date_book,EnumDateType.BOOKING_START_DATE)
        }

        end_booking_date.setOnClickListener {
//            Common.dateSelector(this,tv_end_date_book)
            try {

                val list = tv_st_date_book.tag.toString().split("-")

                commonDatePiker.bookingEndDateTime(list[0].toInt(),list[1].toInt(),list[2].toInt())
                bookingDatePicker(tv_end_date_book,EnumDateType.BOOKING_END_DATE)

            }catch (e:java.lang.Exception){
                showToast(ValidationMessage.START_DATE)
                e.printStackTrace()
            }
        }

        // time set
        st_booking_time.setOnClickListener {
//            Common.timeSelector(this,tv_st_time_book)
            mCommonTimePiker.currentTime()
            bookingTimePicker(tv_st_time_book,TimeConstant.TimeTypeEnum.START_TIME)
        }

        end_booking_time.setOnClickListener {
//            Common.timeSelector(this,tv_end_time_book)
            mCommonTimePiker.currentTime()
            bookingTimePicker(tv_end_time_book,TimeConstant.TimeTypeEnum.END_TIME)
        }

        add_quantity.setOnClickListener {

            item_quantity.text= ""+(++objBookingReqModelItem.quantity)
        }

        minus_quantity.setOnClickListener {
            item_quantity.text= ""+(--objBookingReqModelItem.quantity)
        }


    }

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    fun isNotRequiredValidation():Boolean{

        when{
            tv_st_date_book.tag.toString().isEmpty()->showToast(ValidationMessage.START_DATE)
            tv_st_time_book.tag.toString().isEmpty()->showToast(ValidationMessage.START_TIME)
            tv_end_date_book.tag.toString().isEmpty()->showToast(ValidationMessage.END_DATE)
            tv_end_time_book.tag.toString().isEmpty()->showToast(ValidationMessage.END_TIME)


            // end date can not be less then start date and calclulate days
            commonDatePiker.calculateDatesDiffWithString(tv_st_date_book.tag.toString(),tv_end_date_book.tag.toString()).let { objBookingReqModelItem.booking_days=it
                objBookingReqModelItem.booking_days}<0->{showToast(ValidationMessage.DATE_VALIDATION)}

            //same time and end time diffrance calculation
            objBookingReqModelItem.booking_days==0L && mCommonTimePiker.
            calculateTimeDiff(tv_st_time_book.tag.toString(),tv_end_time_book.tag.toString())/*<TimeConstant.TIME_GAP_BETWEEN_SAME_DATE*/ ->{
                showToast(ValidationMessage.SAME_DATE_TIME_VALIDATION)
            }

            item_quantity.text.toString().toInt()<=0 ->showToast(ValidationMessage.FILL_QUANTITY)
            tv_work_location.text.isEmpty() ->showToast(ValidationMessage.SELECT_ADRESS)


            else-> {
                return false
            }

        }
        return true
    }

    fun onChangeAddressClick(view:View){
       MoveToAnotherComponent.startActivityForResult<MyAddressListActivity>(this,Constant.ADDRESS_REQUECT_CODE,Constant.INTENT_ADDR_LIST,mProductIDByIntent)
    }
    fun moveToAddAnOther(view: View){

        if (isNotRequiredValidation())
            return
        prepareBookingObjectList()
        //HOME
        MoveToAnotherComponent.moveToActivityWithIntentValue<CustomerMainActivity>(this,Constant.INTENT_ADD_ANOTHER,1)

    }
// next button click
    fun moveTOFinalReview(view: View){
        if (isNotRequiredValidation())
            return
        prepareBookingObjectList()
        //final review
        MoveToAnotherComponent.moveToActivityWithIntentValue<CustomerBookingSubmitReviewActivity>(this,"",1)
    }

    private fun prepareBookingObjectList() {

        objBookingReqModelItem.start_date = tv_st_date_book.tag.toString()
        objBookingReqModelItem.start_time = tv_st_time_book.tag.toString()
        objBookingReqModelItem.end_date = tv_end_date_book.tag.toString()
        objBookingReqModelItem.end_time = tv_end_time_book.tag.toString()

        objBookingReqModelItem.quantity = item_quantity.text.toString().toInt()
        objBookingReqModelItem.with_driver = checkbox_with_driver.isChecked

        if (isSameProductHavingSameAdressContains()) {
            showToast(ValidationMessage.ITEM_IS_IN_LIST)
        }
        else {
            objBookingReqModelItem.product_id = mProductIDByIntent
            CustomerBookingSubmitReviewActivity.setBookingItem(objBookingReqModelItem)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode== Activity.RESULT_OK && requestCode ==Constant.ADDRESS_REQUECT_CODE){

            val address =data!!.getParcelableExtra<AddressInfo>(Constant.KEY_ADDRESS)

            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,address.toString())

            setAddressOnUI(address)

        }
    }

    private fun setAddressOnUI(address:AddressInfo){

        tv_work_location.text = address.address_line+","+address.address_type
        objBookingReqModelItem.address_id = address.id
        objBookingReqModelItem.address =address
    }
    private fun isSameProductHavingSameAdressContains():Boolean{

        for (obj in CustomerBookingSubmitReviewActivity.objListBookingItem) {
            if (mProductIDByIntent== obj.product_id && obj.address_id == objBookingReqModelItem.address_id) {
                return true
            }
        }
        return false
    }

}