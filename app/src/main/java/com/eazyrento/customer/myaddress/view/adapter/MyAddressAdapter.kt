package com.eazyrento.customer.myaddress.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.R
import com.eazyrento.customer.dashboard.model.modelclass.Data
import com.eazyrento.customer.myaddress.model.modelclass.AddressListResModelItem
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.RecyclerViewItemClick
import kotlinx.android.synthetic.main.row_my_address.view.*
import org.greenrobot.eventbus.EventBus

class MyAddressAdapter(val items:List<AddressListResModelItem>, val context: Context):
    RecyclerView.Adapter<MyAddressAdapter.ViewHolder>() {


    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvAddressType=view.tv_add_type
        val tvAddressLine=view.tv_add_line
        val tvCountry=view.tv_add_country
        var imgEdit=view.img_edit.setOnClickListener {
            MoveToAnotherComponent.moveToAddNewAddressActivity(it.context)
//            RxBus.publish(EventModel("EditAddress"))
            EventBus.getDefault().postSticky("EditAddress")
        }

        fun bind(data: Data, clickListener: RecyclerViewItemClick)
        {
            itemView.setOnClickListener {
                clickListener.onItemClick(data)
            }
        }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.row_my_address, parent, false)
        )
    }

    override fun getItemCount(): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        holder.tvAddressLine?.text=items.get(position).address_line
        holder.tvAddressType?.text=items.get(position).address_type
        holder.tvCountry?.text=items.get(position).country
//        holder.bind(items.get(position),recyclerViewItemClick)
//        Picasso.with(context).load(items.get(position).avatar)
//            .into(holder.imgVeichle );
    }
}