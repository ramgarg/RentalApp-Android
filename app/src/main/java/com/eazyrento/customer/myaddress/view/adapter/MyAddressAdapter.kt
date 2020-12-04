package com.eazyrento.customer.myaddress.view.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.myaddress.view.AddNewAddressActivity
import com.eazyrento.customer.myaddress.view.MyAddressListActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.login.model.modelclass.AddressInfo
import kotlinx.android.synthetic.main.row_my_address.view.*

class MyAddressAdapter(val items:List<AddressInfo>, val context: Activity):
    RecyclerView.Adapter<MyAddressAdapter.ViewHolder>() {

    private val homeTypeString = "Home,الصفحة الرئيسية"
    private val workTypeString = "Work,عمل"
    private val otherTypeString = "Other,آخر"


    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvAddressType=view.tv_add_type
        val tvAddressLine=view.tv_add_line
        val tvCountry=view.tv_add_country
        val imgSelected=view.img_selected
        var img_delete=view.img_delete
        val img_edit =view.img_edit

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

        holder.tvAddressLine?.text= items[position].address_line

        // converting localization
        val addessType = items[position].address_type

        when(addessType){
            context.getString(R.string.home) -> holder.tvAddressType?.text= context.getString(R.string.home)
            context.getString(R.string.work) -> holder.tvAddressType?.text= context.getString(R.string.work)
            context.getString(R.string.other) -> holder.tvAddressType?.text= context.getString(R.string.other)

        }

        /*if(addessType!!.contains(homeTypeString)){
            holder.tvAddressType?.text= context.getString(R.string.home)
        }else if(addessType.contains(workTypeString)){
            holder.tvAddressType?.text= context.getString(R.string.work)
        }else{
            holder.tvAddressType?.text= context.getString(R.string.other)
        }*/


        holder.tvCountry?.text= items[position].country

        if (items[position].is_default)
             holder.imgSelected?.visibility = View.VISIBLE
        else
            holder.imgSelected?.visibility = View.INVISIBLE

        holder.itemView.setOnClickListener {
            /*if (holder.imgSelected?.visibility!=View.INVISIBLE) {
                holder.imgSelected?.visibility = View.INVISIBLE
            }else{
                holder.imgSelected?.visibility=View.VISIBLE
            }*/
            (context as BaseActivity).moveOnSelecetedItem(items[position])
        }
        holder.img_delete.setOnClickListener {

            //(context as MyAddressListActivity).deleteAddress(position)

        }

        holder.img_edit.setOnClickListener {

//            (context as BaseActivity).moveOnSelecetedItem(items.get(position))

            (context as MyAddressListActivity).editUpdateAddress(items[position])

        }

    }
}