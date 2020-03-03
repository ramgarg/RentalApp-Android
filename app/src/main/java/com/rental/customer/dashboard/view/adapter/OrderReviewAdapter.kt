package com.rental.customer.dashboard.view.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rental.R
import com.rental.customer.dashboard.model.modelclass.BookingDetailsModel
import kotlinx.android.synthetic.main.row_category.view.*
import kotlinx.android.synthetic.main.row_category.view.vehicle_name
import kotlinx.android.synthetic.main.row_order_review.view.*


class OrderReviewAdapter(val orderReviewList: ArrayList<BookingDetailsModel>, val context: Context):
    RecyclerView.Adapter<OrderReviewAdapter.ViewHolder>() {


    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvVeichleName=view.vehicle_name
        val imgVeichle=view.vehicle_image
        private var count:Int=1

        val add_quantity=view.add_quantity.setOnClickListener {
            count += 1
            view.item_quantity .text= (count).toString()
        }
        val minus_quantity=view.minus_quantity.setOnClickListener {
            if(count>1) {
                count -= 1
                view.item_quantity .text = (count).toString()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.row_order_review, parent, false))
    }

    override fun getItemCount(): Int {
        return orderReviewList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.layout_remove.tag = position;

        holder.itemView.layout_remove.setOnClickListener {
            removeItem(position)
        }
    }

    fun removeItem(position: Int) {
        orderReviewList.removeAt(position)
        notifyDataSetChanged()
    }

}