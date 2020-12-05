package com.eazyrento.merchant.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.R
import com.eazyrento.common.model.modelclass.Drivers
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.utils.Common
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_users_order_summary.view.*

class DriverAvailabilityAdapter (private val mDriverListing:List<Drivers>,private val mBaseActivity: BaseActivity) : RecyclerView.Adapter<DriverAvailabilityAdapter.CardViewHolder>() {

    private var tvUnAssignPrevious:TextView?=null

    class CardViewHolder(view: View): RecyclerView.ViewHolder(view){

        val img_user_pic = view.img_user_pic

        val tv_user_name = view.tv_user_name
        val tv_driver_status = view.tv_users_role

        val tv_driver_assign = view.user_rating
        val include_phone_view = view.include_phone_view

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val card_view =
            CardViewHolder(
                LayoutInflater.from(mBaseActivity).inflate(
                    R.layout.adapter_users_order_summary,
                    parent,
                    false
                )
            )
        return card_view
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {

        val driver = mDriverListing[position]

        driver.details.run {

        holder.tv_user_name.text = fullName

        holder.tv_driver_status.text = status

        holder.tv_driver_assign.text = mBaseActivity.resources.getString(R.string.assign)

        holder.include_phone_view.visibility = View.GONE

        holder.itemView.setOnClickListener {


            holder.tv_driver_assign.let{

                tvUnAssignPrevious?.visibility = View.GONE

                it.visibility = View.VISIBLE

                tvUnAssignPrevious = it

                 if(isAlreadyBooked) {

                    Common.showToastString(mBaseActivity,mBaseActivity.getString(R.string.Driver_booking_reached))

                     it.background =mBaseActivity.getDrawable(R.drawable.button_gray)

                    return@setOnClickListener
                }
                else {
                     it.background =mBaseActivity.getDrawable(R.drawable.button_gradient)
                }

            }

            mBaseActivity.moveOnSelecetedItem(driver)

        }

        Picasso.with(mBaseActivity).load(profileImage).into(holder.img_user_pic)

        }
    }

    override fun getItemCount(): Int {
        return mDriverListing.size
    }
}