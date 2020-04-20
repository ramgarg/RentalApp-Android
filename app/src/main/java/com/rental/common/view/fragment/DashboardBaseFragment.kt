package com.rental.common.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rental.R
import com.rental.ValidationMessage
import com.rental.agent.model.modelclass.AgentDashboardResModel
import com.rental.agent.viewmodel.AgentDashboardViewModel
import com.rental.appbiz.AppBizLogger
import com.rental.common.view.adapter.DashboardBookingCardAdapter
import com.rental.customer.utils.Common
import com.rental.merchant.model.modelclass.Booking
import kotlinx.android.synthetic.main.fragment_agent_dashboard.*
import kotlinx.android.synthetic.main.merchant_fragment_dash.*

abstract open class DashboardBaseFragment:BaseFragment() {

    /*override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_agent_dashboard, container, false)

        return view
    }*/

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIFragment<AgentDashboardViewModel>(this).getAgentDashboard()
                , viewLifecycleOwner, requireActivity()
            )
        }

    }

    override fun <T> onSuccessApiResult(data: T) {

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        val agentDashboardResponse = data as AgentDashboardResModel

        if(agentDashboardResponse.bookings.isEmpty()) {
            Common.showToast(requireContext(), ValidationMessage.NO_DATA_FOUND)
            btn_agent_home_view_all.visibility = View.GONE
            return
        }
        setBookingAdapterDashboard(agentDashboardResponse)
        setBookingStatus(agentDashboardResponse)

    }

    private fun setBookingStatus(merchantDashboardResponse: AgentDashboardResModel) {
        complete_value.text = ""+merchantDashboardResponse.completed_orders_count
        in_progress_value.text = ""+merchantDashboardResponse.in_progress_orders_count
        reject_value.text = ""+merchantDashboardResponse.rejected_orders_count
    }

    private fun setBookingAdapterDashboard(agentDashboardResponse: AgentDashboardResModel) {
        recycle_view_agent_home.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.HORIZONTAL, false
        )
        (recycle_view_agent_home.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
            1,
            1
        )

        val recyleAdapterAgentHomeCard =
            DashboardBookingCardAdapter(
                agentDashboardResponse.bookings as MutableList<Booking>,
                requireActivity()
            )

        recycle_view_agent_home.adapter = recyleAdapterAgentHomeCard
    }


}