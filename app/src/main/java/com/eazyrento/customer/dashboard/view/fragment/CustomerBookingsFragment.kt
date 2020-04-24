package com.eazyrento.customer.dashboard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eazyrento.Constant
import com.eazyrento.common.view.fragment.MyBookingBaseFragment

class CustomerBookingsFragment: MyBookingBaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
//        return inflater.inflate(R.layout.fragment_customer_bookings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        callMyBookingAPI(Constant.BOOKING_LIST_CUSTOMER)

       /* callAPI()?.let {
            it.observeApiResult(
                it.callAPIFragment<MyBookingViewModel>(this).getBookingList(Constant.BOOKING_LIST_CUSTOMER)
                , viewLifecycleOwner, requireActivity()
            )
        }*/

    }

    /*override fun <T> onSuccessApiResult(data: T) {
        val bookingListResModel = data as BookingListResModel

        rec_customer_bookings.layoutManager = LinearLayoutManager(requireActivity(),
            LinearLayoutManager.VERTICAL,false)
        (rec_customer_bookings.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(1,1)

        val recyleAdapterCustomerBookings= RecycleAdapterCustomerBookings(bookingListResModel as MutableList<BookingListItem>, requireActivity())

        rec_customer_bookings.adapter = recyleAdapterCustomerBookings
    }*/
}