package com.rental.customer.dashboard.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.data.DataHolder
import com.rental.R
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.utils.RecyclerViewItemClick
import kotlinx.android.synthetic.main.row_category.view.*


class CategoryAdapter(val items:List<Data>, val context: Context,var
recyclerViewItemClick: RecyclerViewItemClick):
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvVeichleName=view.vehicle_name
        val imgVeichle=view.vehicle_image



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
            ).inflate(R.layout.row_category, parent, false)
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
//        Picasso.with(context).load(items.get(position).avatar)
//            .into(holder.imgVeichle );
    }

}