package com.rental.merchant.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rental.R
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.utils.RecyclerViewItemClick
import kotlinx.android.synthetic.main.row_category.view.*

class MerchantOrderOpenAdapter (val items:List<Data>, val context: Context, val recyclerViewItemClick: RecyclerViewItemClick):
               RecyclerView.Adapter<MerchantOrderOpenAdapter.ViewHolder>() {

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvVeichleName=view.vehicle_name


        fun bind(data: Data,clickListener: RecyclerViewItemClick)
        {
            itemView.setOnClickListener {
                clickListener.onItemClick(data)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return MerchantOrderOpenAdapter.ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.row_open_order, parent, false)
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