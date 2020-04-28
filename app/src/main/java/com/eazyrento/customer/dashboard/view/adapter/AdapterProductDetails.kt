package com.eazyrento.customer.dashboard.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.R
import com.eazyrento.common.model.modelclass.DynamicKeyValue
import kotlinx.android.synthetic.main.adapter_product_details.view.*

class AdapterProductDetails(val context: Context,val list: List<DynamicKeyValue>):RecyclerView.Adapter<AdapterProductDetails.ViewHolder>(){


    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val key=view.tv_name_key
        val value=view.tv_value

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.adapter_product_details, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.key.text = list.get(position).key
        holder.value.text =list.get(position).value
    }
}

