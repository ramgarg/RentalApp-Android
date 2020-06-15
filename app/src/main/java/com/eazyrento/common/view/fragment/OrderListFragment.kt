package com.eazyrento.common.view.fragment

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.activity.OrderFilter
import com.eazyrento.common.view.adapter.OrderListBaseAdapter
import com.eazyrento.common.view.adapter.ViewInflaterAndBinder
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderListResModel
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderListResModelItem
import com.eazyrento.customer.dashboard.viewmodel.CustomerOrderListViewModel
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.fragment_order_list_tamplate.*
import kotlinx.android.synthetic.main.fragment_order_list_tamplate.view.*

 abstract class OrderListFragment : BaseFragment(), ViewInflaterAndBinder {
    lateinit var listOrderItems:CustomerOrderListResModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        callAPIOrderList(Constant.OPEN_ORDER)
        img_filter.setOnClickListener {
            MoveToAnotherComponent.moveToActivityNormal<OrderFilter>(requireContext())
        }

    }

  protected fun callAPIOrderList(value: Int){
      callAPI()?.let {
          it.observeApiResult(
              it.callAPIFragment<CustomerOrderListViewModel>(this).getOrderList(value)
              , viewLifecycleOwner, requireActivity()
          )
      }

    }

    protected fun viewVisibility(view: View){


       view.layout_open_inactive.setOnClickListener {

           Common.showGroupViews(layout_open_active,layout_close_inactive)
           Common.hideGroupViews(layout_open_inactive,layout_close_active)

           callAPIOrderList(Constant.OPEN_ORDER)

        }

        view.layout_close_inactive.setOnClickListener {
            Common.showGroupViews(layout_open_inactive,layout_close_active)
            Common.hideGroupViews(layout_close_inactive,layout_open_active)

            callAPIOrderList(Constant.CLOSE_ORDER)

        }
    }

    override fun <T> onSuccessApiResult(data: T) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        listOrderItems = data as CustomerOrderListResModel
        rec_order.adapter=
            OrderListBaseAdapter(
                listOrderItems,
                requireActivity(),
                this
            )

    }


    override fun setDataHolderBinder(holder: OrderListBaseAdapter.ViewHolder, position: Int) {

        holder.tvOrderProductName?.text=listOrderItems.get(position).product_detail?.product_name?.capitalize()
        holder.tvBookingPrice?.text= Constant.DOLLAR+listOrderItems.get(position).product_detail?.starting_price
        holder.tvOrderQuantity?.text=Constant.QUANTITY+listOrderItems.get(position).product_detail?.quantity

        holder.tvOrderId?.text= Constant.ORDER_ID+listOrderItems.get(position).order_id

        changeOrderStatusUI(holder,position)

        holder.itemView.setOnClickListener {
            onViewClick<CustomerOrderListResModelItem,Int>(listOrderItems.get(position),1)
        }
    }

    private fun changeOrderStatusUI(
        holder:OrderListBaseAdapter.ViewHolder,
        position: Int
    ) {
        val status = listOrderItems.get(position).status
        var imageSrc:Int

       when(status){
           Constant.PENDING-> imageSrc =R.drawable.payment_pending
           Constant.FAILED ->imageSrc = R.drawable.payment_failed

           Constant.COMPLETED->{ imageSrc = R.drawable.payment_success
               holder.tvOrderRateReview?.visibility=View.VISIBLE
               holder.tvPaymentStatus?.visibility=View.INVISIBLE
           }
           else-> imageSrc =R.drawable.payment_success
       }

        holder.tvPaymentStatus?.text = status
        holder.tvPaymentStatus?.setBackgroundResource(imageSrc)

       /* if(listOrderItems.get(position).status== Constant.PENDING){
            holder.tvPaymentStatus?.text=listOrderItems.get(position).status
            holder.tvPaymentStatus?.setBackgroundResource(R.drawable.payment_pending)
        }

        else if(listOrderItems.get(position).status== Constant.FAILED){
            holder.tvPaymentStatus?.text=listOrderItems.get(position).status
            holder.tvPaymentStatus?.setBackgroundResource(R.drawable.payment_failed)
        }
        else if(listOrderItems.get(position).status== Constant.COMPLETED){
            holder.tvPaymentStatus?.text=listOrderItems.get(position).status
            holder.tvPaymentStatus?.setBackgroundResource(R.drawable.payment_success)
            holder.tvOrderRateReview?.visibility=View.VISIBLE
            holder.tvPaymentStatus?.visibility=View.INVISIBLE
        }

        else{
            holder.tvPaymentStatus?.text=listOrderItems.get(position).status
            holder.tvPaymentStatus?.setBackgroundResource(R.drawable.payment_success)
        }*/

    }

}
