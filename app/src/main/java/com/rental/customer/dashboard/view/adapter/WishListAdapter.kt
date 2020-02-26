package com.rental.customer.dashboard.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rental.R
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.dashboard.model.modelclass.HomeResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_dashboard.view.*
import kotlinx.android.synthetic.main.row_dashboard.view.vehicle_name
import kotlinx.android.synthetic.main.row_wishlist.view.*

class WishListAdapter(val items:List<Data>, val context: Context):
    RecyclerView.Adapter<WishListAdapter.ViewHolder>() {


    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvVeichleName=view.vehicle_name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.row_wishlist, parent, false)
        )
    }

    override fun getItemCount(): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        holder.tvVeichleName?.text=items.get(position).first_name

    }
}