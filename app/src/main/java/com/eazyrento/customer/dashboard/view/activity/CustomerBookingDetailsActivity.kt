package com.eazyrento.customer.dashboard.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.model.modelclass.ProductDetailsResModel
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.dashboard.model.modelclass.CustomerCreateBookingReqModelItem
import com.eazyrento.customer.myaddress.model.modelclass.AddressListResModelItem
import com.eazyrento.customer.myaddress.view.MyAddressListActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_booking_details.*
import kotlinx.android.synthetic.main.template_product_main_view.*
import kotlinx.android.synthetic.main.toolbar.*

class CustomerBookingDetailsActivity : BaseActivity() {

    private val objBookingReqModelItem = CustomerCreateBookingReqModelItem()
    private var defaultID:Int =-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_booking_details)

        ViewVisibility.isVisibleOrNot(
            this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.booking_details))

        val prodDetailsObj = intent.getParcelableExtra<ProductDetailsResModel>(Constant.BOOKING_PRODECT_DETAILS)
        objBookingReqModelItem.projectDetails =prodDetailsObj
        defaultID = prodDetailsObj.id

        prodDetailsObj?.let{setData(it)}
        clickListenerOnViews()

    }

    fun addAnotherBooking(view: View){
        MoveToAnotherComponent.moveToActivityNormal<CustomerMainActivity>(this)
    }

    private fun setData(prodDetailsObj: ProductDetailsResModel) {

        pro_booking_price.text = ""+prodDetailsObj.base_price
        pro_name.text = prodDetailsObj.name
    }

    private fun clickListenerOnViews(){
        st_booking_date.setOnClickListener {
          Common.dateSelector(this,tv_st_date_book)
        }
        st_booking_time.setOnClickListener {
            Common.timeSelector(this,tv_st_time_book)
        }
        end_booking_date.setOnClickListener {
            Common.dateSelector(this,tv_end_date_book)
        }
        end_booking_time.setOnClickListener {
            Common.timeSelector(this,tv_end_time_book)
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
            tv_st_date_book.text.isEmpty()->showToast(ValidationMessage.START_DATE)
            tv_st_time_book.text.isEmpty()->showToast(ValidationMessage.START_TIME)
            tv_end_date_book.text.isEmpty()->showToast(ValidationMessage.END_DATE)
            tv_end_time_book.text.isEmpty()->showToast(ValidationMessage.END_TIME)
            item_quantity.text.toString().toInt()<=0 ->showToast(ValidationMessage.FILL_QUANTITY)
            tv_work_location.text.isEmpty() ->showToast(ValidationMessage.SELECT_ADRESS)

            Common.calculateDatesWithString(tv_st_date_book.text.toString(),tv_end_date_book.text.toString()).let { objBookingReqModelItem.booking_days=it
                objBookingReqModelItem.booking_days}<0->showToast(ValidationMessage.DATE_VALIDATION)

            else-> {
                return false
            }

        }
        return true
    }

    fun onChangeAddressClick(view:View){
       MoveToAnotherComponent.startActivityForResult<MyAddressListActivity>(this,Constant.ADDRESS_REQUECT_CODE,Constant.INTENT_ADDR_LIST,defaultID)
    }
    fun moveToAddAnOther(view: View){

        if (isNotRequiredValidation())
            return
        prepareBookingObjectList()
        //HOME
        MoveToAnotherComponent.moveToActivityWithIntentValue<CustomerMainActivity>(this,Constant.INTENT_ADD_ANOTHER,1)

    }

    fun moveTOFinalReview(view: View){
        if (isNotRequiredValidation())
            return
        prepareBookingObjectList()
        //final review
        MoveToAnotherComponent.moveToActivityWithIntentValue<CustomerBookingSubmitReviewActivity>(this,"",1)
    }

    private fun prepareBookingObjectList() {

        objBookingReqModelItem.start_date = tv_st_date_book.text.toString()
        objBookingReqModelItem.start_time = tv_st_time_book.text.toString()
        objBookingReqModelItem.end_date = tv_end_date_book.text.toString()
        objBookingReqModelItem.end_time = tv_end_time_book.text.toString()

        objBookingReqModelItem.quantity = item_quantity.text.toString().toInt()
        objBookingReqModelItem.with_driver = checkbox_with_driver.isChecked

        if (isContains()) {
            objBookingReqModelItem.product_id = defaultID
            CustomerBookingSubmitReviewActivity.setBookingItem(objBookingReqModelItem)
        }
        else
            showToast(ValidationMessage.ITEM_IS_IN_LIST)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode== Activity.RESULT_OK && requestCode ==Constant.ADDRESS_REQUECT_CODE){

            val address =data!!.getParcelableExtra<AddressListResModelItem>(Constant.KEY_ADDRESS)

            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,address.toString())

            tv_work_location.text = address.address_line+","+address.address_type
            objBookingReqModelItem.address_id = address.id
        }
    }
    fun isContains():Boolean{
        for (obj in CustomerBookingSubmitReviewActivity.objListBookingItem) {
            if (defaultID== obj.product_id)
                return false
        }
        return true
    }

}