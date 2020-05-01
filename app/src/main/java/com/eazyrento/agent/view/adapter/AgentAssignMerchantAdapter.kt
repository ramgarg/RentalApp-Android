package com.eazyrento.agent.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.R
import com.eazyrento.common.model.modelclass.Order_listing
import kotlinx.android.synthetic.main.card_view_orders.view.*
import kotlinx.android.synthetic.main.card_product_template.view.*

class AgentAssignMerchantAdapter (val orderListing: MutableList<Order_listing>, val context: Context) : RecyclerView.Adapter<AgentAssignMerchantAdapter.CardViewHolder>() {

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvVeichleName=view.vehicle_name
        val imgVeichle=view.vehicle_image
    }

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
        return orderListing.size
    }


    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val order_listing_obj =  orderListing.get(position)

        //customer details
        holder?.tv__name.text = order_listing_obj.customer_detail.full_name
        holder?.tv__type.text = order_listing_obj.customer_detail.mobile_number

        // prodect details
        holder?.tv__product_quantity.text = order_listing_obj.product_detail.product_name+
                "-"+order_listing_obj.product_detail.quantity
        holder.tv__date_show.text = order_listing_obj.product_detail.start_date
        holder.tv__order.text = order_listing_obj.order_id
    }
    class CardViewHolder(view: View):RecyclerView.ViewHolder(view){
        val img__pic = view.img__pic
        val tv__name = view.tv__name
        val tv__type = view.tv__type
        val tv__order = view.tv__order
        val tv__product_quantity = view.tv__quantiity
        val tv__date_show = view.tv_show_date

        val btn__accept = view.btn__accept
        val btn__decline = view.btn__decline

    }
}