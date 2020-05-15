package com.eazyrento.common.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.model.modelclass.AcceptanceDeclineReqModel
import com.eazyrento.common.view.adapter.DashboardBookingCardAdapter
import com.eazyrento.customer.utils.Common
import com.eazyrento.common.model.modelclass.Booking
import com.eazyrento.common.model.modelclass.BookingDashboardResModel
import com.eazyrento.common.view.adapter.AcceptDecline
import com.eazyrento.common.viewmodel.AcceptanceDeleteViewModel
import com.eazyrento.common.viewmodel.BookingDashboardViewModel
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.booking_dashboard_adapter_view.*
import kotlinx.android.synthetic.main.booking_deshboard_bottom_view.*

abstract class DashboardBaseFragment:
    BaseFragment(),AcceptDecline {

    protected lateinit var bookingList:List<Booking>
    protected var positionAccetDecline:Int = -1
    protected lateinit var agentDashboardResponse:BookingDashboardResModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_booking_dashboard, container, false)

        return view
    }

    protected fun callAPIDashboard(value: Int) {

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIFragment<BookingDashboardViewModel>(this).getDashboard(value)
                , viewLifecycleOwner, requireActivity()
            )
        }

    }

    protected fun callAPIAcceptanceDecline(acceptanceDeclineReqModel: AcceptanceDeclineReqModel,value:Int){

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIFragment<AcceptanceDeleteViewModel>(this).accptanceDecline(acceptanceDeclineReqModel,value)
                , viewLifecycleOwner, requireActivity()
            )
        }
    }

    override fun <T> onSuccessApiResult(data: T) {

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        if (data is JsonElement){

            (bookingList as MutableList).removeAt(positionAccetDecline)
            recycle_booking_home_adpter.adapter?.notifyDataSetChanged()

            if (bookingList.isEmpty()) {
                btn_home_view_all.visibility = View.GONE
                tv_no_booking_available.visibility =View.VISIBLE

            }

            Common.showToast(requireContext(),ValidationMessage.REQUEST_SUCCESSED)
            return
        }

        agentDashboardResponse= data as BookingDashboardResModel

        if(agentDashboardResponse.bookings.isEmpty()) {

            btn_home_view_all.visibility = View.GONE
            tv_no_booking_available.visibility =View.VISIBLE
            return
        }

        setBookingAdapterDashboard(agentDashboardResponse)
        setBookingStatus(agentDashboardResponse)


    }

    private fun setBookingStatus(bookingDashboardResponse: BookingDashboardResModel) {
        complete_value.text = ""+bookingDashboardResponse.completed_orders_count
        in_progress_value.text = ""+bookingDashboardResponse.in_progress_orders_count
        reject_value.text = ""+bookingDashboardResponse.rejected_orders_count
    }

    private fun setBookingAdapterDashboard(agentDashboardResponse: BookingDashboardResModel) {

        // recycle view params

        recycle_booking_home_adpter.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.HORIZONTAL, false
        )

        (recycle_booking_home_adpter.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
            1,
            1
        )

        // booking adapter
        bookingList =agentDashboardResponse.bookings

        val recyleAdapterAgentHomeCard =
            DashboardBookingCardAdapter(
                bookingList,
                requireActivity(),this
            )

        recycle_booking_home_adpter.adapter = recyleAdapterAgentHomeCard
    }

     fun acceptBooking(type: Booking, position: Int,flagCustomerType:Int) {
        positionAccetDecline = position
        callAPIAcceptanceDecline(AcceptanceDeclineReqModel(type.id,true),flagCustomerType )
    }

     fun declineBooking(type: Booking, position: Int,flagCustomerType:Int) {
        positionAccetDecline = position
        callAPIAcceptanceDecline(AcceptanceDeclineReqModel(type.id,false), flagCustomerType)
    }


}