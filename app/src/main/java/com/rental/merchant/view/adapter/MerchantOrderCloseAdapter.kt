package com.rental.merchant.view.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rental.R
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.utils.Common
import com.rental.customer.utils.RecyclerViewItemClick
import kotlinx.android.synthetic.main.row_category.view.vehicle_name
import kotlinx.android.synthetic.main.row_closed_order.view.*

class MerchantOrderCloseAdapter (val items:List<Data>, val context: Context, val recyclerViewItemClick: RecyclerViewItemClick):
    RecyclerView.Adapter<MerchantOrderCloseAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvVeichleName = view.vehicle_name

        val tvRating = view.tv_rate.setOnClickListener {
            Common.showDialog("Rating", "", it.context as Activity, R.layout.rating_review)
        }


        fun bind(data: Data, clickListener: RecyclerViewItemClick) {
            /* itemView.setOnClickListener {
                 clickListener.onItemClick(data)
             }*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return MerchantOrderCloseAdapter.ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.row_closed_order, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvVeichleName?.text=items.get(position).first_name
        holder.bind(items.get(position),recyclerViewItemClick)
    }
}