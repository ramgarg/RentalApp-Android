package com.eazyrento.common.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.common.model.modelclass.BookingListItem
import com.eazyrento.common.model.modelclass.BookingListResModel
import com.eazyrento.common.viewmodel.MyBookingViewModel
import com.eazyrento.customer.dashboard.model.modelclass.BaseUserRoleDetail
import com.eazyrento.customer.dashboard.view.adapter.BookingDataHolderBinder
import com.eazyrento.customer.dashboard.view.adapter.RecycleAdapterCustomerBookings
import com.eazyrento.customer.utils.Common
import com.eazyrento.supporting.convertToDisplayDate
import com.eazyrento.supporting.splitDateServerFormat
import com.squareup.picasso.Picasso
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

//        (rec_customer_bookings.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(1,1)

        val recyleAdapterCustomerBookings=
            RecycleAdapterCustomerBookings(this,
                listCustomerBooking,
                requireActivity()
            )

        rec_customer_bookings.adapter = recyleAdapterCustomerBookings
    }

     protected fun setBaseDataHolder(
        holder: RecycleAdapterCustomerBookings.CardViewHolder,
        pos: Int,
        obj:BaseUserRoleDetail

    ) {
        //val obj = listCustomerBooking.get(pos) as BaseUserRoleDetail
        //agent details
        holder.tv_booking__name.text=obj.full_name.capitalize()
        holder.tv_booking_type.text= Constant.AGENT
        Picasso.with(requireContext()).load(obj.profile_image).into(holder.img_booking__pic)
        holder.img_booking__call.setOnClickListener {
            Common.phoneCallWithNumber(obj.mobile_number, requireContext())
        }

        holder.tv_customer_order_id.text = Constant.ORDER_ID + listCustomerBooking.get(pos).order_id

        // product details
        holder.tv_customer_date_show.text = convertToDisplayDate(splitDateServerFormat(listCustomerBooking.get(pos).product_detail.start_date))
//        holder?.tv_customer_product_quantity.text = listCustomerBooking.get(position).product_detail.product_name + "-" + listCustomerBooking.get(position).product_detail.quantity
        holder.tv_customer_product_quantity.text = resources.getString(R.string.quantity)+listCustomerBooking.get(pos).product_detail.quantity
        holder.tv_status.let {
            it.visibility = View.VISIBLE
            it.text = listCustomerBooking.get(pos).status
            val bg = when(listCustomerBooking.get(pos).status){
                Constant.PENDING-> R.drawable.payment_pending
                Constant.FAILED -> R.drawable.payment_failed
                Constant.REJECTED-> R.drawable.payment_failed
                else-> R.drawable.payment_success
            }
            it.setBackgroundResource(bg)
        }


    }
}