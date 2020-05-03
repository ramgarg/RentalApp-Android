package com.eazyrento.common.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderListResModelItem
import kotlinx.android.synthetic.main.adapter_order_status_template.view.*


open class OrderListBaseAdapter(val customerOrdrListItems:List<CustomerOrderListResModelItem>, open val context: Context, open val infalterBinderInterface: ViewInflaterAndBinder):
    RecyclerView.Adapter<OrderListBaseAdapter.ViewHolder>() {

     class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        val tvOrderProductName=view.tv_order_product_name
         val tvBookingPrice=view.tv_order_booking_price
         val tvOrderQuantity=view.tv_quantity
         val tvOrderId=view.tv_order_id
         val tvPaymentStatus=view.tv_payment_status
         val tvOrderRateReview=view.order_rate_review
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        infalterBinderInterface.setDataHolderBinder(holder,position)
    }

    override fun getItemCount(): Int {
        return customerOrdrListItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return infalterBinderInterface.getInflaterView(parent,viewType)
    }



}

interface ViewInflaterAndBinder{
    fun getInflaterView(parent: ViewGroup, viewType: Int): OrderListBaseAdapter.ViewHolder
    fun setDataHolderBinder(holder: OrderListBaseAdapter.ViewHolder, position: Int)
}