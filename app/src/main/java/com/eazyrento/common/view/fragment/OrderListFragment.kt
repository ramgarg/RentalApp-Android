package com.eazyrento.common.view.fragment

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.customer.dashboard.viewmodel.CustomerOrderListViewModel
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.adapter_order_status_template.*
import kotlinx.android.synthetic.main.customer_closed_order_row.*
import kotlinx.android.synthetic.main.fragment_order_list_tamplate.*
import kotlinx.android.synthetic.main.fragment_order_list_tamplate.view.*
import org.greenrobot.eventbus.EventBus

open abstract class OrderListFragment : BaseFragment() {

    /*override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

       val  view=inflater.inflate(R.layout.fragment_order_list_tamplate, container, false)

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
            //tv_payment_pending.visibility=View.INVISIBLE
           // tv_rate.visibility= View.VISIBLE
           // tv_rate.setOnClickListener { MoveToAnotherComponent.moveToOrderReviewActivity(requireContext()) }

            callAPIOrderList(Constant.CLOSE_ORDER)


        }
    }


   /* override fun <T, K> onViewClick(type: T, where: K) {
       val item =  type as CustomerOrderListResModelItem
        MoveToAnotherComponent.moveToActivity<OrderSummaryActivity>(requireContext(),Constant.ORDER_SUMMERY_KEY,
            item.id)
    }*/

}
