package com.rental.customer.dashboard.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.rental.R
import com.rental.appbiz.AppBizLogger
import com.rental.common.view.BaseActivity
import com.rental.customer.dashboard.model.modelclass.CustomerCreateBookingReqModel
import com.rental.customer.dashboard.viewmodel.CustomerCreateBookingViewModel
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.Common
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_booking_details.*
import kotlinx.android.synthetic.main.toolbar.*

class CustomerBookingDetailsActivity :BaseActivity() {
    private var count:Int=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_booking_details)

        ViewVisibility.isVisibleOrNot(
            this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.booking_details))

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

        change_add.setOnClickListener {
            MoveToAnotherComponent.moveToMyAddressActivity(this)
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

        btn_next.setOnClickListener {
           when{
               tv_st_date_book.text.isEmpty()->Toast.makeText(this,"Please select start date",Toast.LENGTH_SHORT).show()
               tv_st_time_book.text.isEmpty()->Toast.makeText(this,"Please select start time",Toast.LENGTH_SHORT).show()
               tv_end_date_book.text.isEmpty()->Toast.makeText(this,"Please select end date",Toast.LENGTH_SHORT).show()
               tv_end_time_book.text.isEmpty()->Toast.makeText(this,"Please select end time",Toast.LENGTH_SHORT).show()

               else-> {

                   MoveToAnotherComponent.moveToOrderReviewActivity(this)
               }

           }


        }

    }

    override fun <T> moveOnSelecetedItem(type: T) {
    }


}