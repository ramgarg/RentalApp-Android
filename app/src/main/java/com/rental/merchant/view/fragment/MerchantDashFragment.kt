package com.rental.merchant.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rental.R
import com.rental.ValidationMessage
import com.rental.appbiz.AppBizLogger
import com.rental.common.view.fragment.BaseFragment
import com.rental.customer.utils.Common
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.merchant.model.modelclass.Booking
import com.rental.merchant.model.modelclass.MerchantDashboardResModel
import com.rental.common.view.adapter.DashboardBookingCardAdapter
import com.rental.merchant.viewModel.MerchantDashboardViewModel
import kotlinx.android.synthetic.main.merchant_fragment_dash.*

class MerchantDashFragment : BaseFragment() {

   // private lateinit var merchantDashboardViewModel: MerchantDashboardViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.merchant_fragment_dash, container, false)

      //  merchantDashboardViewModel = ViewModelProviders.of(this).get(MerchantDashboardViewModel::class.java)

   //     merchantDashboardViewModel.getmerchantHomeOrderList().observe(this, Observer {

            /*recycle_view_merchant_home.layoutManager = LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.HORIZONTAL, false
            )
            (recycle_view_merchant_home.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                1,
                1
            )*/

            /*val recycleAdapterMerchantHomeCard = RecycleAdapterMerchantHomeCard(
                it.order_listing as MutableList<Order_listing>,
                requireActivity()
            )

            recycle_view_merchant_home.adapter = recycleAdapterMerchantHomeCard*/

      //  })

        return view
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIFragment<MerchantDashboardViewModel>(this).getMerchantDashboard()
                , viewLifecycleOwner, requireActivity()
            )
        }

    }

    override fun <T> onSuccessApiResult(data: T) {

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        val merchantDashboardResponse = data as MerchantDashboardResModel

        if(merchantDashboardResponse.bookings.isEmpty()) {
            Common.showToast(requireContext(),ValidationMessage.NO_DATA_FOUND)
            btn_merchant_home_view_all.visibility = View.GONE
            return
        }
        setBookingAdapterDashboard(merchantDashboardResponse)
        setBookingStatus(merchantDashboardResponse)


    }

   private fun setBookingAdapterDashboard(merchantDashboardResponse: MerchantDashboardResModel) {
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
               merchantDashboardResponse.bookings as MutableList<Booking>,
               requireActivity()
           )

       recycle_view_merchant_home.adapter = recycleAdapterMerchantHomeCard
    }
    private fun setBookingStatus(merchantDashboardResponse: MerchantDashboardResModel) {
        complete_value.text = ""+merchantDashboardResponse.completed_orders_count
        in_progress_value.text = ""+merchantDashboardResponse.in_progress_orders_count
        reject_value.text = ""+merchantDashboardResponse.rejected_orders_count
    }

    override fun <T, K> onViewClick(type: T, where: K) {
        MoveToAnotherComponent.moveToCategoryActivity(requireContext())
    }

}