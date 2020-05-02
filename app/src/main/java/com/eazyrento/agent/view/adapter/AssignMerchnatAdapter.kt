package com.eazyrento.agent.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.R
import com.eazyrento.agent.model.modelclass.Merchants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.agent_assign_merchant.view.*
import kotlinx.android.synthetic.main.row_customer_bookings.view.*

class AssignMerchnatAdapter (val assignMerchantDataHolderBinder:BookingDataHolderBinder, val merchantListItem: List<Merchants>, val context: Context) : RecyclerView.Adapter<AssignMerchnatAdapter.CardViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {

        val card_view =
            CardViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.agent_assign_merchant,
                    parent,
                    false
                )
            )
        return card_view
    }

    override fun getItemCount(): Int {
        return merchantListItem.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {

        assignMerchantDataHolderBinder.setDataHolder(holder,position)

       /* holder.assign_merchant_name.text =merchantListItem.get(position).details.full_name
        holder.booking_price.text = ""+merchantListItem.get(position).details.price
//        holder.booking_total_prcie = merchantListItem
        holder.layout_truck_quantity.text =""+merchantListItem.get(position).details.quantity_available

//        Picasso.with(context).load(merchantListItem.get(position).details.)*/
        
    }

    class CardViewHolder(view: View):RecyclerView.ViewHolder(view){
        val layout_truck_quantity = view.layout_truck_quantity
        val booking_total_prcie = view.booking_total_prcie
        val booking_price = view.booking_price
        val customer_profile_pic = view.customer_profile_pic
        val chkbox_assign_merchnat = view.chkbox_assign_merchnat
        val assign_merchant_name = view.assign_merchant_name
    }
}

interface BookingDataHolderBinder{
    fun setDataHolder(holder: AssignMerchnatAdapter.CardViewHolder, position: Int)
}