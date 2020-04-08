package com.rental.customer.dashboard.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rental.R
import com.rental.common.model.modelclass.ProductSubCategoriesModelResItem
import com.rental.common.model.modelclass.Vehicle
import com.rental.customer.dashboard.view.activity.ProductBaseActitvity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_category.view.*


class ProductVehiclesAdapter<T>(val listProductCatg:List<T>, val context: Context):

    RecyclerView.Adapter<ProductVehiclesAdapter.ViewHolder>() {


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
        var itemName :String?=""
        var imageUrl:String? = ""

        val any  = listProductCatg[position]
        any.let { if (it is Vehicle){itemName=it.category_name
                                    imageUrl =it.category_image_url}
                if (it is ProductSubCategoriesModelResItem)
                                { itemName = it.subcategory_name
                                 imageUrl = it.subcategory_image_url}}

        holder.tvVeichleName?.text=itemName

        Picasso.with(context).load(imageUrl)
            .into(holder.imgVeichle )


        holder.itemView.setOnClickListener {
            context.let { if(it is ProductBaseActitvity)it.moveOnSelecetedItem(any) }
        }
    }

}