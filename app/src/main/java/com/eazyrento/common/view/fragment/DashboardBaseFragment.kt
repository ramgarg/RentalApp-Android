package com.eazyrento.common.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.adapter.DashboardBookingCardAdapter
import com.eazyrento.customer.utils.Common
import com.eazyrento.common.model.modelclass.Booking
import com.eazyrento.common.model.modelclass.BookingDashboardResModel
import com.eazyrento.common.viewmodel.BookingDashboardViewModel
import kotlinx.android.synthetic.main.booking_dashboard_adapter_view.*
import kotlinx.android.synthetic.main.booking_deshboard_bottom_view.*

abstract class DashboardBaseFragment:
    BaseFragment() {

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

    override fun <T> onSuccessApiResult(data: T) {

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        val agentDashboardResponse = data as BookingDashboardResModel

        if(agentDashboardResponse.bookings.isEmpty()) {
            Common.showToast(requireContext(), ValidationMessage.NO_DATA_FOUND)
            btn_home_view_all.visibility = View.GONE
            return
        }
        else{
//            MoveToAnotherComponent.moveToActivity<My>()
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
        recycle_booking_home_adpter.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.HORIZONTAL, false
        )
        (recycle_booking_home_adpter.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
            1,
            1
        )

        val recyleAdapterAgentHomeCard =
            DashboardBookingCardAdapter(
                agentDashboardResponse.bookings as MutableList<Booking>,
                requireActivity()
            )

        recycle_booking_home_adpter.adapter = recyleAdapterAgentHomeCard
    }


}