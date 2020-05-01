package com.eazyrento.common.view.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.common.model.modelclass.AcceptanceDeclineReqModel
import com.eazyrento.common.model.modelclass.Booking
import com.eazyrento.common.model.modelclass.BookingDashboardResModel
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.common.view.adapter.AcceptDecline
import com.eazyrento.common.view.adapter.DashboardBookingCardAdapter
import com.eazyrento.common.viewmodel.AcceptanceDeleteViewModel
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.booking_dashboard_adapter_view.*
import kotlinx.android.synthetic.main.fragment_customer_bookings.*

open abstract class ShowAllBookingActivity :BaseActivity(),AcceptDecline {

    lateinit  var bookingDashboardResModel:BookingDashboardResModel
    protected lateinit var bookingList:List<Booking>
    protected var positionAccetDecline:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.fragment_customer_bookings)
        setBookingAdapterDashboard()
    }


    override fun <T> moveOnSelecetedItem(type: T) {
    }

    protected fun setBookingAdapterDashboard() {

        bookingDashboardResModel= intent.getParcelableExtra<BookingDashboardResModel>(Constant.INTENT_BOOKING_LIST)

        // recycle view params

        /* rec_customer_bookings.layoutManager = LinearLayoutManager(
             this,
             LinearLayoutManager.HORIZONTAL, false
         )

         (rec_customer_bookings.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
             1,
             1
         )*/

        // booking adapter

        val recyleAdapterAgentHomeCard =
            DashboardBookingCardAdapter(
                bookingDashboardResModel.bookings,
                this,this
            )

        rec_customer_bookings.adapter = recyleAdapterAgentHomeCard
    }

    protected fun callAPIAcceptanceDecline(acceptanceDeclineReqModel: AcceptanceDeclineReqModel,value:Int){

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<AcceptanceDeleteViewModel>(this).accptanceDecline(acceptanceDeclineReqModel,value)
                , this, this
            )
        }
    }

    fun acceptBooking(type: Booking, position: Int,flagCustomerType:Int) {
        positionAccetDecline = position
        callAPIAcceptanceDecline(AcceptanceDeclineReqModel(type.id,true),flagCustomerType )
    }

    fun declineBooking(type: Booking, position: Int,flagCustomerType:Int) {
        positionAccetDecline = position
        callAPIAcceptanceDecline(AcceptanceDeclineReqModel(type.id,false), flagCustomerType)
    }

    override fun <T> onSuccessApiResult(data: T) {

        if (data is JsonElement){

            (bookingList as MutableList).removeAt(positionAccetDecline)
            rec_customer_bookings.adapter?.notifyDataSetChanged()

            if (bookingList.isEmpty()) {
                btn_home_view_all.visibility = View.GONE
                showToast(ValidationMessage.NO_DATA_FOUND)
            }

            showToast(ValidationMessage.REQUEST_SUCCESSED)
            return
        }
    }


}