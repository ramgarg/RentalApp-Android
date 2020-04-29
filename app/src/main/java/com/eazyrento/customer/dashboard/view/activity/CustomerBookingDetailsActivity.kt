package com.eazyrento.customer.dashboard.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.dashboard.model.modelclass.CustomerCreateBookingReqModelItem
import com.eazyrento.customer.myaddress.model.modelclass.AddressListResModelItem
import com.eazyrento.customer.myaddress.view.MyAddressListActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_booking_details.*
import kotlinx.android.synthetic.main.toolbar.*

class CustomerBookingDetailsActivity : BaseActivity() {
    private var count:Int=1
    private val customerCreateBookingReqModelItem = CustomerCreateBookingReqModelItem()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_booking_details)

        ViewVisibility.isVisibleOrNot(
            this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.booking_details))

        val id = intent.getIntExtra(Constant.BOOKING_PRODECT_DETAILS,-1)

        customerCreateBookingReqModelItem.product_id =id

        clickListenerOnViews()

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

        add_new_add_img.setOnClickListener {
            MoveToAnotherComponent.moveToHomeActivity(this)
        }

        add_quantity.setOnClickListener {
             count += 1
            item_quantity.text= (count).toString()
        }

        minus_quantity.setOnClickListener {
            if(count>1) {
                count -= 1
                item_quantity.text = (count).toString()
            }
        }

    }

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    fun isRequiredValidation(){

        when{
            tv_st_date_book.text.isEmpty()->Toast.makeText(this,"Please select start date",Toast.LENGTH_SHORT).show()
            tv_st_time_book.text.isEmpty()->Toast.makeText(this,"Please select start time",Toast.LENGTH_SHORT).show()
            tv_end_date_book.text.isEmpty()->Toast.makeText(this,"Please select end date",Toast.LENGTH_SHORT).show()
            tv_end_time_book.text.isEmpty()->Toast.makeText(this,"Please select end time",Toast.LENGTH_SHORT).show()

            else-> {
            }

        }
    }

    fun onChangeAddressClick(view:View){
       MoveToAnotherComponent.startActivityForResult<MyAddressListActivity>(this,Constant.ADDRESS_REQUECT_CODE,Constant.INTENT_ADDR_LIST,customerCreateBookingReqModelItem.product_id)
    }

    fun moveTOFinalReview(){
        MoveToAnotherComponent.moveToActivity<CustomerBookingSubmitReviewActivity>(this,"",1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode== Activity.RESULT_OK && requestCode ==Constant.ADDRESS_REQUECT_CODE){

            val address =data!!.getParcelableExtra<AddressListResModelItem>(Constant.KEY_ADDRESS)

            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,address.toString())

            tv_work_location.text = address.address_line+","+address.address_type
        }
    }


}