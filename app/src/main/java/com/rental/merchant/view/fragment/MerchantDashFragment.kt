package com.rental.merchant.view.fragment

import android.os.Bundle
import com.rental.Constant
import com.rental.common.view.fragment.DashboardBaseFragment

class MerchantDashFragment : DashboardBaseFragment() {

   // private lateinit var merchantDashboardViewModel: MerchantDashboardViewModel

   /* override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.merchant_fragment_dash, container, false)

        return view
    }*/



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        callAPIDashboard(Constant.BOOKING_DASHBOARD_MERCHANT)


    }

    /*override fun <T> onSuccessApiResult(data: T) {

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        val merchantDashboardResponse = data as BookingDashboardResModel

        if(merchantDashboardResponse.bookings.isEmpty()) {
            Common.showToast(requireContext(),ValidationMessage.NO_DATA_FOUND)
            btn_merchant_home_view_all.visibility = View.GONE
            return
        }
        setBookingAdapterDashboard(merchantDashboardResponse)
        setBookingStatus(merchantDashboardResponse)


    }

   private fun setBookingAdapterDashboard(bookingDashboardResponse: BookingDashboardResModel) {
       recycle_view_merchant_home.layoutManager = LinearLayoutManager(
           requireActivity(),
           LinearLayoutManager.HORIZONTAL, false
       )
       (recycle_view_merchant_home.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
           1,
           1
       )

       val recycleAdapterMerchantHomeCard =
           DashboardBookingCardAdapter(
               bookingDashboardResponse.bookings as MutableList<Booking>,
               requireActivity()
           )

       recycle_view_merchant_home.adapter = recycleAdapterMerchantHomeCard
    }
    private fun setBookingStatus(bookingDashboardResponse: BookingDashboardResModel) {
        complete_value.text = ""+bookingDashboardResponse.completed_orders_count
        in_progress_value.text = ""+bookingDashboardResponse.in_progress_orders_count
        reject_value.text = ""+bookingDashboardResponse.rejected_orders_count
    }

    override fun <T, K> onViewClick(type: T, where: K) {
        MoveToAnotherComponent.moveToCategoryActivity(requireContext())
    }*/

}