package com.eazyrento.merchant.view.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.R
import com.eazyrento.customer.dashboard.model.modelclass.Data
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.RecyclerViewItemClick
import kotlinx.android.synthetic.main.card_product_template.view.vehicle_name
import kotlinx.android.synthetic.main.customer_closed_order_row.view.*

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
        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.customer_closed_order_row, parent, false)
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