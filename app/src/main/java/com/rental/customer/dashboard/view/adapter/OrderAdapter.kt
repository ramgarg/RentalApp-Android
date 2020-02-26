package com.rental.customer.dashboard.view.adapter

import android.R.attr.name
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.rental.R
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.dashboard.model.modelclass.HomeResponse
import com.rental.customer.utils.RecyclerViewItemClick
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_dashboard.view.*


class OrderAdapter(val items:List<Data>, val context: Context, val recyclerViewItemClick:RecyclerViewItemClick):
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {



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
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.row_order, parent, false)
        )
    }

    override fun getItemCount(): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        holder.tvVeichleName?.text=items.get(position).first_name
        holder.bind(items.get(position),recyclerViewItemClick)

    }

}