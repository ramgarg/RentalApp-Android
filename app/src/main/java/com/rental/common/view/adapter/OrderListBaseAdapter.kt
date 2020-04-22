package com.rental.common.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rental.Constant
import com.rental.R
import com.rental.common.view.fragment.BaseFragment
import com.rental.customer.dashboard.model.modelclass.CustomerOrderListResModelItem
import kotlinx.android.synthetic.main.adapter_order_status_template.view.*
import kotlinx.android.synthetic.main.row_category.view.*


open abstract class OrderListBaseAdapter(val customerOrdrListItems:List<CustomerOrderListResModelItem>, open val context: Context, open val baseFragment: BaseFragment):
    RecyclerView.Adapter<OrderListBaseAdapter.ViewHolder>() {

     class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvOrderProductName=view.tv_order_product_name
         val tvBookingPrice=view.tv_order_booking_price
         val tvOrderQuantity=view.tv_order_quantity
         val tvOrderId=view.tv_order_id
         val tvPaymentStatus=view.tv_payment_status
         val tvOrderRateReview=view.order_rate_review
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvOrderProductName?.text=customerOrdrListItems.get(position).product_detail.product_name
        holder.tvBookingPrice?.text=Constant.DOLLAR+customerOrdrListItems.get(position).product_detail.starting_price
        holder.tvOrderQuantity?.text=" "+customerOrdrListItems.get(position).product_detail.quantity
        holder.tvOrderId?.text=Constant.ORDER_ID+customerOrdrListItems.get(position).order_id
        if(customerOrdrListItems.get(position).status== Constant.PENDING){
            holder.tvPaymentStatus?.text=customerOrdrListItems.get(position).status
            holder.tvPaymentStatus?.setBackgroundResource(R.drawable.payment_pending)
        }
        else if(customerOrdrListItems.get(position).status== Constant.FAILED){
            holder.tvPaymentStatus?.text=customerOrdrListItems.get(position).status
            holder.tvPaymentStatus?.setBackgroundResource(R.drawable.payment_failed)
        }else if(customerOrdrListItems.get(position).status== Constant.COMPLETED){
            holder.tvPaymentStatus?.text=customerOrdrListItems.get(position).status
            holder.tvPaymentStatus?.setBackgroundResource(R.drawable.payment_success)
            holder.tvOrderRateReview?.visibility=View.VISIBLE
            holder.tvPaymentStatus?.visibility=View.INVISIBLE
        }else{
            holder.tvPaymentStatus?.text=customerOrdrListItems.get(position).status
            holder.tvPaymentStatus?.setBackgroundResource(R.drawable.payment_success)
        }

        holder.itemView.setOnClickListener { baseFragment.onViewClick<CustomerOrderListResModelItem,Int>(customerOrdrListItems.get(position),1) }
    }

    /*override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.adapter_order_status_template, parent, false)
        )
    }*/

    override fun getItemCount(): Int {
        return customerOrdrListItems.size
    }



}