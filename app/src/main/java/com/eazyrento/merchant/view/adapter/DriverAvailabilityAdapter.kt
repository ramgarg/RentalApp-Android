package com.eazyrento.merchant.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.R

class DriverAvailabilityAdapter (val driverListing:Int,val mContext: Context) : RecyclerView.Adapter<DriverAvailabilityAdapter.CardViewHolder>() {

    class CardViewHolder(view: View): RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val card_view =
            CardViewHolder(
                LayoutInflater.from(mContext).inflate(
                    R.layout.adapter_users_order_summary,
                    parent,
                    false
                )
            )
        return card_view
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return 5
    }
}