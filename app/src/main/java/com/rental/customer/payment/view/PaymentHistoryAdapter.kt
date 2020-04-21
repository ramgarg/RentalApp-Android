package com.rental.customer.payment.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rental.R
import com.rental.customer.payment.model.modelclass.PaymentListResModelItem
import kotlinx.android.synthetic.main.row_category.view.*

class PaymentHistoryAdapter(val items:List<PaymentListResModelItem>, val context: Context):
    RecyclerView.Adapter<PaymentHistoryAdapter.ViewHolder>() {


    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvVeichleName=view.vehicle_name
        val imgVeichle=view.vehicle_image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.row_payment_history, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvVeichleName?.text=items.get(position).mode_of_payment
    }
}