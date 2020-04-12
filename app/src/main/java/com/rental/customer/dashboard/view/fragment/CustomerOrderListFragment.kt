package com.rental.customer.dashboard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rental.Constant
import com.rental.R
import com.rental.appbiz.AppBizLogger
import com.rental.common.view.BaseFragment
import com.rental.customer.dashboard.model.modelclass.CustomerOrderListResModel
import com.rental.customer.dashboard.model.modelclass.CustomerOrderListResModelItem
import com.rental.customer.dashboard.view.activity.OrderSummaryActivity
import com.rental.customer.dashboard.view.adapter.OrderListAdapter
import com.rental.customer.dashboard.viewmodel.CustomerOrderListViewModel
import com.rental.customer.dashboard.viewmodel.OrderViewModel
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.Common
import kotlinx.android.synthetic.main.fragment_order.*
import kotlinx.android.synthetic.main.fragment_order.view.*
import org.greenrobot.eventbus.EventBus

open class CustomerOrderListFragment : BaseFragment() {

   private lateinit var orderViewModel: OrderViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val  view=inflater.inflate(R.layout.fragment_order, container, false)

        viewVisibility(view)

        /*orderViewModel= ViewModelProviders.of(this).get(OrderViewModel::class.java)
        orderViewModel.getOrderResponse().observe(this, Observer {
            rec_order.adapter= OrderOpenAdapter(it.data,requireActivity(),this)
            EventBus.getDefault().postSticky("OPEN_ACTIVE")
//            (activity as MainActivity).layout_loading.visibility=View.GONE
        })*/

        return  view
    }

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




    private fun viewVisibility(view: View){


       view.layout_open_inactive.setOnClickListener {

           Common.showGroupViews(layout_open_active,layout_close_inactive)
           Common.hideGroupViews(layout_open_inactive,layout_close_active)
           EventBus.getDefault().postSticky("OPEN_ACTIVE")

           callAPIOrderList(Constant.OPEN_ORDER)

            /*this.orderViewModel.getOrderResponse().observe(viewLifecycleOwner, Observer {
                rec_order.adapter= OrderOpenAdapter(it.data,requireActivity(),this)
            })*/
        }

        view.layout_close_inactive.setOnClickListener {
            Common.showGroupViews(layout_open_inactive,layout_close_active)
            Common.hideGroupViews(layout_close_inactive,layout_open_active)
            EventBus.getDefault().postSticky("CLOSE_ACTIVE")

            callAPIOrderList(Constant.CLOSE_ORDER)

           /* this.orderViewModel.getOrderResponse().observe(viewLifecycleOwner, Observer {
                rec_order.adapter= OrderClosedAdapter(it.data,requireActivity(),this)
            })*/

        }
    }



    override fun <T> onSuccessApiResult(data: T) {
        //CustomerOrderListResModelItem

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

          rec_order.adapter= OrderListAdapter(data as CustomerOrderListResModel,requireActivity(),this)

        //default set view

    }

    override fun <T, K> onViewClick(type: T, where: K) {
       val item =  type as CustomerOrderListResModelItem
        MoveToAnotherComponent.moveToActivity<OrderSummaryActivity>(requireContext(),Constant.ORDER_SUMMERY_KEY,
            type.id)
    }

}
