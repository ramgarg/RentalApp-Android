package com.eazyrento.merchant.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.eazyrento.R
import com.eazyrento.common.view.adapter.OrderListBaseAdapter
import com.eazyrento.common.view.fragment.BaseFragment
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderListResModelItem

class MerchantOrderStatusAdapter (val items: List<CustomerOrderListResModelItem>, override  val context: Context, override val baseFragment: BaseFragment):
       OrderListBaseAdapter(items,context,baseFragment){

    /*class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
//        val tvVeichleName=view.agent_order_vehicle_name


        fun bind(data: Data,clickListener: RecyclerViewItemClick)
        {
            itemView.setOnClickListener {
                clickListener.onItemClick(data)
            }
        }

    }*/


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.merchant_order_status_adapter, parent, false)
        )
    }

   /* override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AgentOrderStatusAdapter.ViewHolder, position: Int) {
        holder.tvVeichleName?.text=items.get(position).first_name
        holder.bind(items.get(position),recyclerViewItemClick)
    }*/
}