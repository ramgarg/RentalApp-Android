package com.rental.common.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rental.Constant
import com.rental.R
import com.rental.ValidationMessage
import com.rental.common.model.modelclass.BookingListItem
import com.rental.common.model.modelclass.BookingListResModel
import com.rental.common.viewmodel.MyBookingViewModel
import com.rental.customer.dashboard.view.adapter.RecycleAdapterCustomerBookings
import com.rental.customer.utils.Common
import kotlinx.android.synthetic.main.fragment_customer_bookings.*

abstract class MyBookingBaseFragment: BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_customer_bookings, container, false)
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
        val bookingListResModel = data as BookingListResModel

        if (bookingListResModel.size<=0)
        {
            Common.showToast(requireContext(),ValidationMessage.NO_DATA_FOUND)
            return
        }
        rec_customer_bookings.layoutManager = LinearLayoutManager(requireActivity(),
            LinearLayoutManager.VERTICAL,false)
        (rec_customer_bookings.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(1,1)

        val recyleAdapterCustomerBookings= RecycleAdapterCustomerBookings(bookingListResModel as MutableList<BookingListItem>, requireActivity())

        rec_customer_bookings.adapter = recyleAdapterCustomerBookings
    }
}