package com.eazyrento.common.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.common.model.modelclass.Booking
import kotlinx.android.synthetic.main.card_view_orders.view.*

class DashboardBookingCardAdapter (val orderListing: MutableList<Booking>, val context: Context) : RecyclerView.Adapter<DashboardBookingCardAdapter.CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val card_view =
            CardViewHolder(
                LayoutInflater.from(context).inflate(R.layout.card_view_orders, parent, false)
            )
        return card_view
    }

    override fun getItemCount(): Int {
        return orderListing.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {


        val order_listing_obj =  orderListing.get(position)

        //customer details
         if (order_listing_obj.customer_detail!=null) {
             holder?.tv_agent_name.text = order_listing_obj.customer_detail.full_name
             holder?.tv_agent_type.text = Constant.CUSTOMER
         }else {
             holder?.tv_agent_name.text=Constant.CUSTOMER
             holder?.tv_agent_type.text=Constant.CUSTOMER
         }
          if (order_listing_obj.product_detail!=null) {
              // prodect details
              holder?.tv_agent_product_quantity.text = order_listing_obj.product_detail.product_name + "-" + order_listing_obj.product_detail.quantity
              holder.tv_agent_date_show.text = order_listing_obj.product_detail.start_date
              holder.tv_agent_order.text = order_listing_obj.order_id
          }else{

          }

    }
    class CardViewHolder(view: View):RecyclerView.ViewHolder(view){
        val img_agent_pic = view.img_agent_pic
        val tv_agent_name = view.tv_agent_name
        val tv_agent_type = view.tv_agent_type
        val tv_agent_order = view.tv_agent_order
        val tv_agent_product_quantity = view.tv_agent_quantiity
        val tv_agent_date_show = view.tv_show_date

        val btn_agent_accept = view.btn_agent_accept
        val btn_agent_decline = view.btn_agent_decline

    }

}