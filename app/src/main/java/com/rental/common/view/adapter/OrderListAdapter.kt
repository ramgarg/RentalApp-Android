package com.rental.common.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rental.R
import com.rental.common.view.fragment.BaseFragment
import com.rental.customer.dashboard.model.modelclass.CustomerOrderListResModelItem
import kotlinx.android.synthetic.main.row_category.view.*


class OrderListAdapter(val customerOrdrListItems:List<CustomerOrderListResModelItem>, val context: Context, val baseFragment: BaseFragment):
    RecyclerView.Adapter<OrderListAdapter.ViewHolder>() {

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvVeichleName=view.vehicle_name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.row_open_order, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return customerOrdrListItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvVeichleName?.text=customerOrdrListItems.get(position).product_detail.product_name
        holder.itemView.setOnClickListener { baseFragment.onViewClick<CustomerOrderListResModelItem,Int>(customerOrdrListItems.get(position),1) }
    }



}