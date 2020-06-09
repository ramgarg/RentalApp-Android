package com.eazyrento.agent.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.agent.model.modelclass.Merchants
import com.eazyrento.customer.utils.Common
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.agent_assign_merchant.view.*
import kotlinx.android.synthetic.main.phone_view.view.*
import kotlinx.android.synthetic.main.row_customer_bookings.view.*
import java.lang.Exception

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

        val itemMerchant = merchantListItem[position]
        
        holder.assign_merchant_name.text =itemMerchant.details.full_name
        holder.booking_price.text = Constant.BOOKING_PRICE.plus(itemMerchant.details.price)
//        holder.booking_total_prcie = merchantListItem
//        holder.layout_truck_quantity.text =Constant.QUANTITY+itemMerchant.details.quantity_available
        holder.merchant_distance.text=itemMerchant.details.distance.toString().plus(Constant.KM)

        holder.__quantity.text = context.resources.getString(R.string.truck_quantity).plus(itemMerchant.details.quantity_available)

        holder.img_call.setOnClickListener {
            Common.phoneCallWithNumber(itemMerchant.details.mobile_number, context) }

        holder.plusIcon.setOnClickListener {

            val value = convertToInt( holder.item_quantity.text.toString())

            if (value == convertToInt(holder.__quantity.text.removePrefix(context.resources.getString(R.string.truck_quantity)).toString())){
                Common.showToast(context,ValidationMessage.QUANTITY_SET_LIMIT)
            }
            else {
                holder.item_quantity.text = (value + 1).toString()
                itemMerchant.details.quantity_available = value+1
            }
         }

        holder.minusIcon.setOnClickListener {

            val value = convertToInt( holder.item_quantity.text.toString())

            if (value-1<1){
                Common.showToast(context,ValidationMessage.FILL_QUANTITY)

            }else {

                holder.item_quantity.text = (value-1).toString()
                itemMerchant.details.quantity_available = value-1
            }
        }

        Picasso.with(context).load(itemMerchant.details.profile_image).into(holder.customer_profile_pic)

    }
    private fun convertToInt(value:String):Int{
        try {
            return value.toInt()
        }catch (e:Exception){
            e.printStackTrace()
        }
        return 0
    }

    class CardViewHolder(view: View):RecyclerView.ViewHolder(view){
        //val layout_truck_quantity = view.layout_truck_quantity
        val booking_price = view.booking_price
        val customer_profile_pic = view.customer_profile_pic
        val chkbox_assign_merchnat = view.chkbox_assign_merchnat
        val assign_merchant_name = view.assign_merchant_name
        val merchant_distance = view.merchant_distatnce
        val item_quantity = view.item_quantity
        val __quantity = view.__quantity
        val plusIcon = view.add_quantity
        val minusIcon =view.minus_quantity
        val img_call= view.phone_view
    }
}

interface BookingDataHolderBinder{
    fun setDataHolder(holder: AssignMerchnatAdapter.CardViewHolder, position: Int)
}