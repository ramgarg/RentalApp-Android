package com.rental.customer.dashboard.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rental.Constant
import com.rental.R
import com.rental.common.model.modelclass.MasterResModelItem
import com.rental.common.view.BaseActivity
import com.rental.common.view.fragment.BaseFragment
import com.rental.customer.dashboard.view.activity.ProductCategoryActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_product_template.view.*

class CustomerHomeAdapter(val listMasterData: ArrayList<MasterResModelItem>, val context: Context,val baseFragment: BaseFragment):
    RecyclerView.Adapter<CustomerHomeAdapter.ViewHolder>() {


    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvVeichleName=view.vehicle_name
        val imgVeichle=view.vehicle_image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                context).inflate(R.layout.row_home, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listMasterData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvVeichleName?.text=listMasterData.get(position).name

        holder.itemView.setOnClickListener {

            baseFragment.onViewClick(listMasterData.get(position),1)
           /* val intent = Intent(context, ProductCategoryActivity::class.java).putExtra(Constant.MASTER_DATA_ITEM,listMasterData.get(position))
            context.startActivity(intent)*/
        }
        Picasso.with(context).load(listMasterData.get(position).master_image_url)
            .into(holder.imgVeichle );
    }
}