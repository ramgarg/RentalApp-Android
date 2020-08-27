package com.eazyrento.common.view.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.activity.FilterKeyValue.Companion.ORDER_ID
import com.eazyrento.common.view.activity.FilterKeyValue.Companion.PRODUCT_NAME
import com.eazyrento.common.view.activity.FilterKeyValue.Companion.START_DATE
import com.eazyrento.common.view.activity.FilterKeyValue.Companion.STATUS
import com.eazyrento.common.view.activity.OrderFilter
import com.eazyrento.common.view.adapter.OrderListBaseAdapter
import com.eazyrento.common.view.adapter.ViewInflaterAndBinder
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderListResModel
import com.eazyrento.customer.dashboard.model.modelclass.OrderDetailsResModel
import com.eazyrento.customer.dashboard.viewmodel.CustomerOrderListViewModel
import com.eazyrento.customer.utils.Common
import kotlinx.android.synthetic.main.fragment_order_list_tamplate.*
import kotlinx.android.synthetic.main.fragment_order_list_tamplate.view.*

abstract class OrderListFragment : BaseFragment(), ViewInflaterAndBinder {
    lateinit var listOrderItems : CustomerOrderListResModel
    lateinit var filterListItems:MutableList<OrderDetailsResModel>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        callAPIOrderList(Constant.OPEN_ORDER)


        img_filter.setOnClickListener {

            val intent = Intent(requireActivity(),OrderFilter::class.java)
            intent.putParcelableArrayListExtra(Constant.INTENT_FILTER_LIST,listOrderItems)
            startActivityForResult(intent,Constant.REQUEST_CODE_FILTER_ORDER_LIST)
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
        filterListItems = listOrderItems.toMutableList()

        setAdapter(filterListItems)
    }
    private fun setAdapter(listOrderItemsFilter: List<OrderDetailsResModel>) {

        rec_order.adapter=
            OrderListBaseAdapter(
                listOrderItemsFilter,
                requireActivity(),
                this
            )

    }


    override fun setDataHolderBinder(holder: OrderListBaseAdapter.ViewHolder, position: Int) {

        val item = filterListItems[position]

        holder.tvOrderProductName?.text=item.product_detail?.product_name?.capitalize()
        holder.tvBookingPrice?.text= Constant.DOLLAR.plus(item.order_amount_with_commission)
        holder.tvOrderQuantity?.text=resources.getString(R.string.quantity).plus(item.product_detail?.quantity)

        holder.tvOrderId?.text= resources.getString(R.string.orderid).plus(item.order_id)

        changeOrderStatusUI(holder,position)

        holder.itemView.setOnClickListener {
            onViewClick(item,1)
        }
    }

    private fun changeOrderStatusUI(
        holder:OrderListBaseAdapter.ViewHolder,
        position: Int
    ) {
       val item =  filterListItems[position]
        val status = item.status
        val imageSrc:Int

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

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode== Activity.RESULT_OK && requestCode==Constant.REQUEST_CODE_FILTER_ORDER_LIST) {

            val map = data?.getSerializableExtra(Constant.INTENT_FILTER_APPLY)

            AppBizLogger.log(
                AppBizLogger.LoggingType.DEBUG,
                "requestCode:".plus(requestCode).plus(" data:").plus(map?. toString ()
            ))

            map?.let {
                  filterApply(map as HashMap<String,String>)
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
    private fun filterApply(hashMapFilterData: HashMap<String, String>) {
          val list = listOrderItems.filter {
              it.order_id==hashMapFilterData[ORDER_ID] ||
                      it.status ==hashMapFilterData[STATUS]||
                      it.product_detail?.product_name ==hashMapFilterData[PRODUCT_NAME]||
                      it.product_detail?.start_date ==hashMapFilterData[START_DATE]
          }



        if (list.isNotEmpty()) {
            // filtered list
            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"Filtered List:".plus(list.toString()))
            resetListAndAdapter(list)
        }
        else
            //reset filter
            resetListAndAdapter(listOrderItems)
    }
  private fun resetListAndAdapter(list: List<OrderDetailsResModel>){

      filterListItems.clear()
      filterListItems = list.toMutableList()
      setAdapter(filterListItems)

  }
}
