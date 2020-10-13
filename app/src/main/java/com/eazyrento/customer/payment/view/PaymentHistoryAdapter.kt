package com.eazyrento.customer.payment.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.payment.model.modelclass.PaymentListResModelItem
import kotlinx.android.synthetic.main.row_payment_history.view.*

class PaymentHistoryAdapter(val items:List<PaymentListResModelItem>, val context: Context):
    RecyclerView.Adapter<PaymentHistoryAdapter.ViewHolder>() {


    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvPaymentStatus=view.payment_status
        val tvPayment=view.tv_payment
        val tvOrderId=view.tv_order_id
        val tvDate=view.tv_date
        val imgPaymentMode=view.img_payment_mode
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        if(item.status != null) {
            when (item.status) {
                Constant.PENDING -> {
                    holder.tvPaymentStatus?.text = item.status
                    holder.tvPaymentStatus?.setBackgroundResource(R.drawable.payment_pending)

                    // if module agent and payment is panding then agent can confirm if paymnet is recvied or not..
                    holder.itemView.setOnClickListener {
                        (context as BaseActivity).moveOnSelecetedItem<PaymentListResModelItem>(item)
                    }
                }
                Constant.FAILED -> {
                    holder.tvPaymentStatus?.text = item.status
                    holder.tvPaymentStatus?.setBackgroundResource(R.drawable.payment_failed)
                }
                else -> {
                    holder.tvPaymentStatus?.text = item.status
                    holder.tvPaymentStatus?.setBackgroundResource(R.drawable.payment_success)
                }

            }
        }

      /*  if(items.get(position).status== Constant.PENDING){
            holder.tvPaymentStatus?.text=items.get(position).status
            holder.tvPaymentStatus?.setBackgroundResource(R.drawable.payment_pending)
        }*/
        /*else if(items.get(position).status== Constant.FAILED){
            holder.tvPaymentStatus?.text=items.get(position).status
            holder.tvPaymentStatus?.setBackgroundResource(R.drawable.payment_failed)
        }else{
            holder.tvPaymentStatus?.text=items.get(position).status
            holder.tvPaymentStatus?.setBackgroundResource(R.drawable.payment_success)
        }*/

        holder.tvPayment?.text= Constant.DOLLAR.plus(item.amount_paid)
        holder.tvOrderId?.text= context.resources.getString(R.string.orderid).plus(item.order_id)

        holder.tvDate?.text=item.added_on

        if(item.mode_of_payment== Constant.CASH){
            holder.imgPaymentMode?.setImageResource(R.mipmap.cash)
        }
        else{
            holder.imgPaymentMode?.setImageResource(R.mipmap.online_payment)
        }
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


}