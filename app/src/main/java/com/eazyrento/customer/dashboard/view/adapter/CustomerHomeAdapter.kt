package com.eazyrento.customer.dashboard.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.R
import com.eazyrento.common.model.modelclass.MasterResModelItem
import com.eazyrento.common.view.fragment.BaseFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_product_template.view.*

class CustomerHomeAdapter(val listMasterData: ArrayList<MasterResModelItem>, val context: Context, val baseFragment: BaseFragment):
    RecyclerView.Adapter<CustomerHomeAdapter.ViewHolder>() {


    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvVeichleName=view.vehicle_name
        val imgVeichle=view.vehicle_image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.row_home, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listMasterData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvVeichleName?.text=listMasterData.get(position).display_name.capitalize()

        holder.itemView.setOnClickListener {

            baseFragment.onViewClick(listMasterData.get(position),1)
        }

        Picasso.with(context).load(listMasterData.get(position).master_image_url)
            .into(holder.imgVeichle )
    }
}