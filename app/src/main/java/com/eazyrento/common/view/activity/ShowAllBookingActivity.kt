package com.eazyrento.common.view.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.common.model.modelclass.*
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.common.view.adapter.AcceptDecline
import com.eazyrento.common.view.adapter.DashboardBookingCardAdapter
import com.eazyrento.common.viewmodel.AcceptanceDeleteViewModel
import com.eazyrento.customer.dashboard.model.modelclass.BaseUserRoleDetail
import com.eazyrento.customer.dashboard.view.adapter.RecycleAdapterCustomerBookings
import com.eazyrento.supporting.convertToDisplayDate
import com.eazyrento.supporting.splitDateServerFormat
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.booking_dashboard_adapter_view.*
import kotlinx.android.synthetic.main.fragment_customer_bookings.*

open abstract class ShowAllBookingActivity :BaseActivity(),AcceptDecline {

    lateinit  var bookingDashboardResModel:BookingDashboardResModel
   // protected lateinit var bookingList:List<Booking>
    protected var positionAccetDecline:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.fragment_customer_bookings)
        topBarWithBackIconAndTitle(resources.getString(R.string.booking))
        setBookingAdapterDashboard()
    }


    override fun <T> moveOnSelecetedItem(type: T) {
    }

    protected fun setBookingAdapterDashboard() {

        bookingDashboardResModel= intent.getParcelableExtra<BookingDashboardResModel>(Constant.INTENT_BOOKING_LIST)


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

            (bookingDashboardResModel.bookings as MutableList).removeAt(positionAccetDecline)

            rec_customer_bookings.adapter?.notifyDataSetChanged()

            if (bookingDashboardResModel.bookings.isEmpty()) {
                btn_home_view_all?.visibility = View.GONE
                showToast(ValidationMessage.NO_DATA_FOUND)
            }

            showToast(ValidationMessage.REQUEST_SUCCESSED)
            return
        }
    }

    protected fun setBaseDataHolder(
        pos: Int,
        obj: BaseUserRoleDetail,
        modelBooking: BookingAdapterModel,
        list: List<Booking>
    ) {
        bookingModelHolder(
            pos,
            obj,
            modelBooking,
            list.get(pos),
            this
        )
        list.get(pos).start_date?.let {
            modelBooking.tv_date_show.text = convertToDisplayDate(splitDateServerFormat(it))
        }
    }


}