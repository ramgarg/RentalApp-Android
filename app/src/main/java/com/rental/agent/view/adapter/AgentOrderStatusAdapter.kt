package com.rental.agent.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rental.R
import com.rental.common.view.adapter.OrderListBaseAdapter
import com.rental.common.view.fragment.BaseFragment
import com.rental.customer.dashboard.model.modelclass.CustomerOrderListResModelItem
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.utils.RecyclerViewItemClick

class AgentOrderStatusAdapter (val items: List<CustomerOrderListResModelItem>, override  val context: Context, override val baseFragment: BaseFragment):
       OrderListBaseAdapter(items,context,baseFragment){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.agent_order_status_adapter, parent, false)
        )
    }

}