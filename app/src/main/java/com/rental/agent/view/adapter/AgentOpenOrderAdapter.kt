package com.rental.agent.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rental.R
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.dashboard.view.adapter.OrderOpenAdapter
import com.rental.customer.utils.RecyclerViewItemClick
import kotlinx.android.synthetic.main.agent_open_order_row.view.*

class AgentOpenOrderAdapter (val items: List<Data>, val context: Context, val recyclerViewItemClick: RecyclerViewItemClick):
       RecyclerView.Adapter<AgentOpenOrderAdapter.ViewHolder>(){

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvVeichleName=view.agent_order_vehicle_name


        fun bind(data: Data,clickListener: RecyclerViewItemClick)
        {
            itemView.setOnClickListener {
                clickListener.onItemClick(data)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentOpenOrderAdapter.ViewHolder {
        return AgentOpenOrderAdapter.ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.agent_open_order_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AgentOpenOrderAdapter.ViewHolder, position: Int) {
        holder.tvVeichleName?.text=items.get(position).first_name
        holder.bind(items.get(position),recyclerViewItemClick)
    }
}