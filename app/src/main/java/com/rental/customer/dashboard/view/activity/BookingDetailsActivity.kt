package com.rental.customer.dashboard.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rental.R
import com.rental.customer.utils.MoveToActivity
import com.rental.customer.utils.Common
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_booking_details.*
import kotlinx.android.synthetic.main.toolbar.*

class BookingDetailsActivity :AppCompatActivity() {
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
            MoveToActivity.moveToHomeActivity(this)
        }

        change_add.setOnClickListener {
            MoveToActivity.moveToMyAddressActivity(this)
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
            MoveToActivity.moveToOrderReviewActivity(this)

        }

    }


    
}