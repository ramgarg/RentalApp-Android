package com.rental.customer.dashboard.view.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rental.R
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.utils.RecyclerViewItemClick
import com.rental.customer.utils.TimeDateSelector
import kotlinx.android.synthetic.main.row_closed_order.view.*
import kotlinx.android.synthetic.main.row_category.view.*
import kotlinx.android.synthetic.main.row_category.view.vehicle_name


class OrderClosedAdapter(val items:List<Data>, val context: Context, val recyclerViewItemClick:RecyclerViewItemClick):
    RecyclerView.Adapter<OrderClosedAdapter.ViewHolder>() {



    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvVeichleName=view.vehicle_name

        val tvRating=view.tv_rate.setOnClickListener {
            TimeDateSelector.showDialog("Rating", it.context as Activity,R.layout.rating_review)
        }



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
            ).inflate(R.layout.row_closed_order, parent, false)
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