package com.rental.agent.view.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rental.R
import com.rental.common.model.modelclass.MasterResModelItem
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.utils.Common
import com.rental.customer.utils.RecyclerViewItemClick
import kotlinx.android.synthetic.main.agent_closed_order_row.view.*
import kotlinx.android.synthetic.main.row_closed_order.view.tv_rate

class AgentCloseOrderAdapter(val items:List<Data> , val context: Context, val recyclerViewItemClick: RecyclerViewItemClick):
    RecyclerView.Adapter<AgentCloseOrderAdapter.ViewHolder>(){

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvVeichleName=view.agent_order_vehicle_name

        val tvRating=view.tv_rate.setOnClickListener {
            Common.showDialog("Rating","", it.context as Activity, R.layout.rating_review)
        }

        fun bind(data: MasterResModelItem,clickListener: RecyclerViewItemClick)
        {
           /* itemView.setOnClickListener {
                clickListener.onItemClick(data)
            }*/
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentCloseOrderAdapter.ViewHolder {
        return AgentCloseOrderAdapter.ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.agent_closed_order_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AgentCloseOrderAdapter.ViewHolder, position: Int) {
        holder.tvVeichleName?.text=items.get(position).first_name
        //holder.bind(items.get(position),recyclerViewItemClick)
    }
}