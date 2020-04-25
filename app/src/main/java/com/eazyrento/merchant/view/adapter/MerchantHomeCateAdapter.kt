package com.eazyrento.merchant.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.R
import com.eazyrento.common.view.fragment.BaseFragment
import com.eazyrento.customer.dashboard.view.adapter.InfalterViewAdapter
import com.eazyrento.customer.dashboard.view.adapter.ProductVehiclesAdapter
import com.eazyrento.merchant.view.fragment.MerchantCatItem
import kotlinx.android.synthetic.main.fragment_merchant_home_adapter.view.*

class MerchantHomeCateAdapter(val context: Context,val listItem:MutableList<MerchantCatItem>,val baseFragment: BaseFragment):RecyclerView.Adapter<MerchantHomeCateAdapter.ViewHolder>(),
    InfalterViewAdapter {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
       val tv_merchant_cat_name = view.tv_merchant_cat_name
        val tv_merchant_view_all = view.tv_merchant_view_all
        val rec_edit_delete_product = view.rec_edit_delete_product
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_merchant_home_adapter,parent,false))
    }

    override fun getItemCount(): Int {
       return listItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_merchant_cat_name.text = listItem.get(position).key

        holder.tv_merchant_view_all.setOnClickListener {
            baseFragment.onViewClick(listItem.get(position),1)
        }

        holder.rec_edit_delete_product.adapter =
            ProductVehiclesAdapter(
                listItem.get(position).value,
                context,
                this
            )
    }

    override fun getInflaterViewIDAdapter(): Int {
        return R.layout.row_merchant_home_vehicle
    }
}