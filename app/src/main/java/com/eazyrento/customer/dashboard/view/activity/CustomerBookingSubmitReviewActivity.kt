package com.eazyrento.customer.dashboard.view.activity

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.dashboard.model.modelclass.BookingDetailsModel
import com.eazyrento.customer.dashboard.model.modelclass.CustomerCreateBookingReqModel
import com.eazyrento.customer.dashboard.model.modelclass.CustomerCreateBookingReqModelItem
import com.eazyrento.customer.dashboard.view.adapter.OrderReviewAdapter
import com.eazyrento.customer.dashboard.viewmodel.CustomerCreateBookingViewModel
import com.eazyrento.customer.utils.Common.Companion.bookingDetailsModel
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_order_review.*
import kotlinx.android.synthetic.main.toolbar.*


class CustomerBookingSubmitReviewActivity : BaseActivity() {
    companion object Factory {
        val objListBookingItem = CustomerCreateBookingReqModel()

        fun setBookingItem( obj:CustomerCreateBookingReqModelItem){
            objListBookingItem.add(obj)
        }



       /* fun makeCar(horsepowers: Int): Car {
            val car = Car(horsepowers)
            cars.add(car)
            return car
        }*/
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_order_review)

        ViewVisibility.isVisibleOrNot(this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.order_review))

        //creating our adapter
        val adapter =
            OrderReviewAdapter(
                bookingDetailsModel,
                this
            )

        //now adding the adapter to recyclerview
        rec_order_review.adapter = adapter
    }

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun <T> onSuccessApiResult(data: T) {
        data?.let {
            showToast(it.toString())

            MoveToAnotherComponent.moveToActivity<CustomerMainActivity>(this,
                Constant.INTENT_SUCCESS_ORDER_BOOKING,1)
        }
    }

    override fun onClickDailog(int: Int) {
        // create customer creating booking model
        // need to build it


        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<CustomerCreateBookingViewModel>(this)
                    .createBooking(objListBookingItem)
                , this, this
            )

        }

    }

    fun onSubmitButtonClick(view: View){
        showDialog(getString(R.string.payment),getString(R.string.order_submit),this,R.layout.thank_you_pop)
    }
}