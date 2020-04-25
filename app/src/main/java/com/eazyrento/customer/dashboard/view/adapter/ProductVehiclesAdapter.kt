package com.eazyrento.customer.dashboard.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.common.model.modelclass.ProductSubCategoriesModelResItem
import com.eazyrento.common.model.modelclass.ProductCateItem
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.merchant.model.modelclass.MerchantProductItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_product_template.view.*
import kotlinx.android.synthetic.main.row_merchant_add_vehicle.view.*
import kotlinx.android.synthetic.main.row_merchant_home_vehicle.view.*


class ProductVehiclesAdapter<T>(val listProductCatg:List<T>, val context: Context,val infalterViewAdapter: InfalterViewAdapter):

    RecyclerView.Adapter<ProductVehiclesAdapter.ViewHolder>() {


    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvVeichleName=view.vehicle_name
        val imgVeichle=view.vehicle_image
        val btnAdd = view.btn_add_product
        val btn_edit = view.btn_edit_product
        val btn_delete=view.btn_delete_product

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(infalterViewAdapter.getInflaterViewIDAdapter(), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listProductCatg.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var itemName :String?=""
        var imageUrl:String? = ""

        val any  = listProductCatg[position]
        any.let { if (it is ProductCateItem){itemName=it.display_name
                                    imageUrl =it.category_image_url}
                   if (it is ProductSubCategoriesModelResItem)
                                { itemName = it.subcategory_name
                                 imageUrl = it.subcategory_image_url}

                    if (it is MerchantProductItem)
                        { itemName = it.product_name
                            imageUrl = it.product_image_url}
        }

        holder.tvVeichleName?.text=itemName

        if(imageUrl!=null && !imageUrl.equals("")) {
            Picasso.with(context).load(imageUrl)
                .into(holder.imgVeichle)
        }

        // click on whole view
        holder.itemView.setOnClickListener {
            context.let { if(it is BaseActivity)it.moveOnSelecetedItem(any) }
        }
        //add product click merchnat add vehile
        holder.btnAdd?.setOnClickListener {
            context.let { if(it is BaseActivity)it.moveOnSelecetedItem(any) }
        }

        //delete product merchant home
        holder.btn_delete?.setOnClickListener {
            context.let { if(it is BaseActivity)it.moveOnSelecetedItem(any) }
        }
        //edit product merchnat home
        holder.btn_edit?.setOnClickListener {
            context.let { if(it is BaseActivity)it.moveOnSelecetedItem(any) }
        }
    }

}

interface InfalterViewAdapter{
    fun getInflaterViewIDAdapter():Int
}