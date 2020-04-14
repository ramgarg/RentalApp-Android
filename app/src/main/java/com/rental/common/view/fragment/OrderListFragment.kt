package com.rental.common.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rental.Constant
import com.rental.R
import com.rental.appbiz.AppBizLogger
import com.rental.customer.dashboard.model.modelclass.CustomerOrderListResModel
import com.rental.customer.dashboard.model.modelclass.CustomerOrderListResModelItem
import com.rental.customer.dashboard.view.activity.OrderSummaryActivity
import com.rental.common.view.adapter.OrderListAdapter
import com.rental.customer.dashboard.viewmodel.CustomerOrderListViewModel
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.Common
import kotlinx.android.synthetic.main.fragment_order_tamplate.*
import kotlinx.android.synthetic.main.fragment_order_tamplate.view.*
import org.greenrobot.eventbus.EventBus

open abstract class OrderListFragment : BaseFragment() {

    /*override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

       val  view=inflater.inflate(R.layout.fragment_order_tamplate, container, false)

        viewVisibility(view)

        return  view
    }*/

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        //default for order open
        EventBus.getDefault().postSticky("OPEN_ACTIVE")
        callAPIOrderList(Constant.OPEN_ORDER)

    }

  private fun callAPIOrderList(value: Int){
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
           EventBus.getDefault().postSticky("OPEN_ACTIVE")

           callAPIOrderList(Constant.OPEN_ORDER)

        }

        view.layout_close_inactive.setOnClickListener {
            Common.showGroupViews(layout_open_inactive,layout_close_active)
            Common.hideGroupViews(layout_close_inactive,layout_open_active)
            EventBus.getDefault().postSticky("CLOSE_ACTIVE")

            callAPIOrderList(Constant.CLOSE_ORDER)


        }
    }



    override fun <T> onSuccessApiResult(data: T) {
        //CustomerOrderListResModelItem

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

          rec_order.adapter= OrderListAdapter(
              data as CustomerOrderListResModel,
              requireActivity(),
              this
          )

        //default set view

    }

    override fun <T, K> onViewClick(type: T, where: K) {
       val item =  type as CustomerOrderListResModelItem
        MoveToAnotherComponent.moveToActivity<OrderSummaryActivity>(requireContext(),Constant.ORDER_SUMMERY_KEY,
            item.id)
    }

}
