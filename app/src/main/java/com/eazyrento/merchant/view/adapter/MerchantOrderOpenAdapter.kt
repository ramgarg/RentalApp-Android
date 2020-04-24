package com.eazyrento.merchant.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.R
import com.eazyrento.customer.dashboard.model.modelclass.Data
import com.eazyrento.customer.utils.RecyclerViewItemClick
import kotlinx.android.synthetic.main.card_product_template.view.*

class MerchantOrderOpenAdapter (val items:List<Data>, val context: Context, val recyclerViewItemClick: RecyclerViewItemClick):
               RecyclerView.Adapter<MerchantOrderOpenAdapter.ViewHolder>() {

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvVeichleName=view.vehicle_name


        fun bind(data: Data, clickListener: RecyclerViewItemClick)
        {
            itemView.setOnClickListener {
                clickListener.onItemClick(data)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.customer_open_order_row, parent, false)
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