package com.eazyrento.common.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.common.model.modelclass.BookingListItem
import com.eazyrento.common.model.modelclass.BookingListResModel
import com.eazyrento.common.viewmodel.MyBookingViewModel
import com.eazyrento.customer.dashboard.view.adapter.BookingDataHolderBinder
import com.eazyrento.customer.dashboard.view.adapter.RecycleAdapterCustomerBookings
import com.eazyrento.customer.utils.Common
import kotlinx.android.synthetic.main.fragment_customer_bookings.*

abstract class MyBookingBaseFragment: BaseFragment(), BookingDataHolderBinder {
    protected lateinit  var listCustomerBooking:BookingListResModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_customer_bookings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar_view_all?.visibility =View.GONE
    }

    protected fun callMyBookingAPI(value: Int){

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIFragment<MyBookingViewModel>(this).getBookingList(value)
                , viewLifecycleOwner, requireActivity()
            )
        }

    }

    override fun <T> onSuccessApiResult(data: T) {
        listCustomerBooking = data as BookingListResModel

        if (listCustomerBooking.size<=0)
        {
            Common.showToast(requireContext(),
                ValidationMessage.NO_DATA_FOUND)
            return
        }
        rec_customer_bookings.layoutManager = LinearLayoutManager(requireActivity(),
            LinearLayoutManager.VERTICAL,false)
        (rec_customer_bookings.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(1,1)

        val recyleAdapterCustomerBookings=
            RecycleAdapterCustomerBookings(this,
                listCustomerBooking,
                requireActivity()
            )

        rec_customer_bookings.adapter = recyleAdapterCustomerBookings
    }
}