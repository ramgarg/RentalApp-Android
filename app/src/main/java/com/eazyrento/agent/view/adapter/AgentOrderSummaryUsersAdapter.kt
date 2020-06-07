/*
package com.eazyrento.agent.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.agent.view.activity.AgentOrderSummaryActivity
import com.eazyrento.customer.dashboard.model.modelclass.MerchantDetail
import com.eazyrento.customer.utils.Common
import kotlinx.android.synthetic.main.adapter_users_order_summary.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.phone_view.view.*

class AgentOrderSummaryUsersAdapter (val orderListing: MutableList<MerchantDetail>, val context: Context) : RecyclerView.Adapter<AgentOrderSummaryUsersAdapter.CardViewHolder>() {

    class CardViewHolder(view: View):RecyclerView.ViewHolder(view){
        val img_user_pic = view.img_user_pic
        val tv_user_name = view.tv_user_name
        val tv_user_type = view.tv_user_type
        val img_user_call= view.phone_view

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val card_view =
            CardViewHolder(
                LayoutInflater.from(context).inflate(R.layout.adapter_users_order_summary, parent, false)
            )
        return card_view
    }

    override fun getItemCount(): Int {
        return orderListing.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val order_listing_obj =  orderListing.get(position)

        //user details
        if (order_listing_obj!=null) {

            holder?.tv_user_name.text = order_listing_obj.full_name
            holder?.tv_user_type.text = Constant.MERCHANT
            holder?.img_user_call.visibility=View.VISIBLE
            (context as AgentOrderSummaryActivity).sendMerchantID(order_listing_obj.merchant_id)
            holder?.img_user_call.setOnClickListener {
                Common.phoneCallWithNumber(order_listing_obj?.mobile_number, context)
            }
            //holder?.img_user_pic.setImageResource() = order_listing_obj
//            Picasso.with(context).load(order_listing_obj.)holder.img_user_pic

        }else{
            holder?.tv_user_name!!.text= Constant.MERCHANT
            holder?.tv_user_type!!.text= Constant.MERCHANT
        }



    }
}*/
