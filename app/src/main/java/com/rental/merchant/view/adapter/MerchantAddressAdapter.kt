package com.rental.merchant.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rental.R
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.RecyclerViewItemClick
import kotlinx.android.synthetic.main.row_my_address.view.*
import org.greenrobot.eventbus.EventBus

class MerchantAddressAdapter (val items:List<Data>, val context: Context, val recyclerViewItemClick: RecyclerViewItemClick):
    RecyclerView.Adapter<MerchantAddressAdapter.ViewHolder>() {

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvVeichleName = view.tv_add_type
        var imgEdit = view.img_edit.setOnClickListener {
            MoveToAnotherComponent.moveToAddNewAddressActivity(it.context)
//            RxBus.publish(EventModel("EditAddress"))
            EventBus.getDefault().postSticky("EditAddress")
        }

        fun bind(data: Data,clickListener: RecyclerViewItemClick) {
            itemView.setOnClickListener {
                clickListener.onItemClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.row_merchant_address, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MerchantAddressAdapter.ViewHolder, position: Int) {
        holder.tvVeichleName?.text=items.get(position).first_name
        holder.bind(items.get(position),recyclerViewItemClick)
    }
}