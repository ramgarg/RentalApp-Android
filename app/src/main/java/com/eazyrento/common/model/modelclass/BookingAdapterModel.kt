package com.eazyrento.common.model.modelclass

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.customer.dashboard.model.modelclass.BaseUserRoleDetail
import com.eazyrento.customer.utils.Common
import com.eazyrento.supporting.convertToDisplayDate
import com.eazyrento.supporting.splitDateServerFormat
import com.squareup.picasso.Picasso


data class BookingAdapterModel(
    //user details
    val tv_user_name :TextView,
    val tv_user_role :TextView,
    val img_profile_pic:ImageView,
    val img_phone_call:ImageView,
    //product details
    val tv_product_quantity:TextView,
    val tv_date_show:TextView,
    val tv_order_id:TextView,

    //order status
    val tv_status :TextView?,
    val btn_accept_booking:Button,
    val btn_decline_booking:Button
)

fun bookingModelHolder(
    pos: Int,
    obj: BaseUserRoleDetail,
    modelBooking: BookingAdapterModel,
    bookingListItem:BaseBooking,
    context: Context
) {
    //val obj = listCustomerBooking.get(pos) as BaseUserRoleDetail
    //agent details
    modelBooking.tv_user_name.text=obj.full_name.capitalize()
    modelBooking.tv_user_role.text= obj.userRole
    Picasso.with(context).load(obj.profile_image).into(modelBooking.img_profile_pic)
    modelBooking.img_phone_call.setOnClickListener {
        Common.phoneCallWithNumber(obj.mobile_number, context)
    }

    modelBooking.tv_order_id.text = Constant.BOOKING_ID.plus(bookingListItem.order_id)

    // product details
    bookingListItem.product_detail?.let{
    modelBooking.tv_date_show.text = convertToDisplayDate(splitDateServerFormat(it.start_date))
    modelBooking.tv_product_quantity.text = context.resources.getString(R.string.quantity).plus(it.quantity)
    }

    modelBooking.tv_status?.let {
        it.visibility = View.VISIBLE
        it.text = bookingListItem.status
        val bg = when(bookingListItem.status){
            Constant.PENDING-> R.drawable.payment_pending
            Constant.FAILED -> R.drawable.payment_failed
            Constant.REJECTED-> R.drawable.payment_failed
            else-> R.drawable.payment_success
        }
        it.setBackgroundResource(bg)
    }


}