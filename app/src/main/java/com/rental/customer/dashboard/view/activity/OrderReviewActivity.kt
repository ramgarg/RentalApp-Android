package com.rental.customer.dashboard.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rental.R
import com.rental.customer.dashboard.model.modelclass.BookingDetailsModel
import com.rental.customer.dashboard.view.adapter.OrderReviewAdapter
import com.rental.customer.utils.Common
import com.rental.customer.utils.Common.Companion.bookingDetailsModel
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_order_review.*
import kotlinx.android.synthetic.main.toolbar.*

class OrderReviewActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_order_review)

        ViewVisibility.isVisibleOrNot(this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.order_review))

        //adding some dummy data to the list
        bookingDetailsModel.add(BookingDetailsModel("","Volvo VH Truck","15",1,5))

        //creating our adapter
        val adapter = OrderReviewAdapter(bookingDetailsModel,this)

        //now adding the adapter to recyclerview
        rec_order_review.adapter = adapter

        btn_submit.setOnClickListener {
            Common.showDialog(getString(R.string.payment),getString(R.string.order_submit),this,R.layout.thank_you_pop)
        }

    }
}