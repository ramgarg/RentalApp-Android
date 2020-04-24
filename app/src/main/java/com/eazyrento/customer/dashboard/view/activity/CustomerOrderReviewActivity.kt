package com.eazyrento.customer.dashboard.view.activity

import android.os.Bundle
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.dashboard.model.modelclass.BookingDetailsModel
import com.eazyrento.customer.dashboard.model.modelclass.CustomerCreateBookingReqModel
import com.eazyrento.customer.dashboard.view.adapter.OrderReviewAdapter
import com.eazyrento.customer.dashboard.viewmodel.CustomerCreateBookingViewModel
import com.eazyrento.customer.utils.Common.Companion.bookingDetailsModel
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_order_review.*
import kotlinx.android.synthetic.main.toolbar.*


class CustomerOrderReviewActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_order_review)

        ViewVisibility.isVisibleOrNot(this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.order_review))

        //adding some dummy data to the list
        bookingDetailsModel.add(
            BookingDetailsModel(
                "",
                "Volvo VH Truck",
                "15",
                1,
                5
            )
        )

        //creating our adapter
        val adapter =
            OrderReviewAdapter(
                bookingDetailsModel,
                this
            )

        //now adding the adapter to recyclerview
        rec_order_review.adapter = adapter

        btn_submit.setOnClickListener {
            // create customer creating booking model
            // need to build it
            val createBookingReqModel =
                CustomerCreateBookingReqModel()

            callAPI()?.let {
                it.observeApiResult(
                    it.callAPIActivity<CustomerCreateBookingViewModel>(this)
                        .createBooking(createBookingReqModel)
                    , this, this
                )

            }
            showDialog(getString(R.string.payment),getString(R.string.order_submit),this,R.layout.thank_you_pop)
        }

    }

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun <T> onSuccessApiResult(data: T) {
        data?.let {
            showToast(it.toString())
        }
    }

    override fun onClickDailog(int: Int) {
        MoveToAnotherComponent.moveToActivity<CustomerMainActivity>(this,
            Constant.INTENT_SUCCESS_ORDER_BOOKING,1)

    }
}