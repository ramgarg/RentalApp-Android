package com.eazyrento.customer.myaddress.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.myaddress.model.modelclass.AddressListResModelItem
import com.eazyrento.customer.myaddress.view.AddNewAddressActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.row_my_address.view.*

class MyAddressAdapter(val items:List<AddressListResModelItem>, val context: Context):
    RecyclerView.Adapter<MyAddressAdapter.ViewHolder>() {


    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvAddressType=view.tv_add_type
        val tvAddressLine=view.tv_add_line
        val tvCountry=view.tv_add_country
        val imgSelected=view.img_selected
        var imgEdit=view.img_edit

       /* fun bind(data: Data, clickListener: RecyclerViewItemClick)
        {
            itemView.setOnClickListener {
                clickListener.onItemClick(data)
            }
        }*/




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.row_my_address, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvAddressLine?.text=items.get(position).address_line
        holder.tvAddressType?.text=items.get(position).address_type
        holder.tvCountry?.text=items.get(position).country

        holder.itemView.setOnClickListener {
            if (holder.imgSelected?.visibility!=View.INVISIBLE) {
                holder.imgSelected?.visibility = View.INVISIBLE
            }else{
                holder.imgSelected?.visibility=View.VISIBLE
            }
            (context as BaseActivity).moveOnSelecetedItem(items.get(position))
        }
        holder.imgEdit.setOnClickListener {
            MoveToAnotherComponent.moveToActivityWithIntentValue<AddNewAddressActivity>(context,
                Constant.INTENT_ADDRESS_EDIT,items[position].id)
        }
//        holder.bind(items.get(position),recyclerViewItemClick)
//        Picasso.with(context).load(items.get(position).avatar)
//            .into(holder.imgVeichle );
    }
}