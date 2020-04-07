package com.rental.customer.dashboard.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rental.Constant
import com.rental.R
import com.rental.common.model.modelclass.ProductCategoriesResModel
import com.rental.common.model.modelclass.Vehicle
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.dashboard.view.activity.ProductSubCategoryActivity
import com.rental.customer.utils.RecyclerViewItemClick
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_category.view.*


class ProductCategoriesAdapter(val listProductCatg:List<Vehicle>, val context: Context):
    RecyclerView.Adapter<ProductCategoriesAdapter.ViewHolder>() {


    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvVeichleName=view.vehicle_name
        val imgVeichle=view.vehicle_image

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.row_category, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listProductCatg.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvVeichleName?.text=listProductCatg.get(position).category_name

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductSubCategoryActivity::class.java).putExtra(Constant.MASTER_DATA_ITEM,listProductCatg.get(position))
            context.startActivity(intent)
        }

        Picasso.with(context).load(listProductCatg.get(position).category_image_url)
            .into(holder.imgVeichle );
    }

}