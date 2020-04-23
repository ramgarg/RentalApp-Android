package com.rental.customer.dashboard.view.activity

import android.os.Bundle
import android.view.View
import com.rental.Constant
import com.rental.R
import com.rental.appbiz.AppBizLogger
import com.rental.common.view.OrderBaseSummaryActivity
import com.rental.customer.dashboard.model.modelclass.CustomerOrderDetailsResModel
import com.rental.customer.utils.Common
import com.rental.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.activity_customer_order_summary.*
import kotlinx.android.synthetic.main.adapter_user_order_summery.*
import kotlinx.android.synthetic.main.adapter_users_order_summary.*
import kotlinx.android.synthetic.main.order_summary_template.tv_end_date_sel
import kotlinx.android.synthetic.main.order_summary_template.tv_end_time_sel
import kotlinx.android.synthetic.main.order_summary_template.tv_st_date_sel
import kotlinx.android.synthetic.main.order_summary_template.tv_st_time_sel
import kotlinx.android.synthetic.main.template_order_summery_top_view.*
import kotlinx.android.synthetic.main.template_work_info.*


class CustomerOrderSummaryActivity : OrderBaseSummaryActivity() {
//     private var productID:Int?=-1

//    lateinit var orderSummaryViewModel :OrderSummaryViewModel
    override fun <T> moveOnSelecetedItem(type: T) {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_customer_order_summary)
//       productID = intent.extras?.getInt(Constant.ORDER_SUMMERY_KEY)

//        orderSummaryViewModel=ViewModelProviders.of(this).get(OrderSummaryViewModel::class.java)
//        orderSummaryViewModel.getOrderSummaryResponse().observe(this, Observer {
//            //Here response will come form api
       /* setResponseViews()
//        })

        ViewVisibility.isVisibleOrNot(this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.order_summary))
*/
        clickListenerOnViews()

    }

    private fun clickListenerOnViews(){
       payment_view_history.setOnClickListener { MoveToAnotherComponent.moveToPaymentHistoryActivity(this) }
        order_rate_review.setOnClickListener { Common.showDialog(getString(R.string.rating),getString(R.string.thank_you),this,R.layout.rating_review) }
        customer_payment_button.setOnClickListener { MoveToAnotherComponent.moveToPaymentActivity(this) }
    }

       // checkbox_with_driver.isClickable=false


   /* @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun messageReceive(customEvent: String?) {
        if (customEvent.equals("OPEN_ACTIVE")) {
            hideGroupViews(tv_order_id,tv_delver_date,tv_days,tv_coupon,coupon_applied,
                tv_happy,tv_happy_with, tv_give_tip)

            showGroupViews(tv_pending_amount,tv_pay_now)

        } else {
            invisibleGroupViews(tv_pending_amount,tv_pay_now)
            showGroupViews(tv_order_id,tv_delver_date,tv_days,tv_coupon,coupon_applied,tv_happy,tv_happy_with,
                tv_give_tip)
            hideGroupViews(layout_driver)

            tv_c.setOnClickListener {
                MoveToAnotherComponent.moveToPaymentActivity(this)
            }
        }

        callAPIOrderList(intent.extras?.getInt(Constant.ORDER_SUMMERY_KEY)!!)

    }*/

    /*override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onPause() {
        super.onPause()
        EventBus.getDefault().unregister(this)
    }*/



    override fun <T> onSuccessApiResult(data: T) {
        val orderRes = data as CustomerOrderDetailsResModel
        tv_order_product_name.text=orderRes.product_detail.product_name
        tv_booking_price.text=Constant.DOLLAR+orderRes.product_detail.starting_price
        tv_order_id.text=Constant.ORDER_ID+orderRes.order_id
        order_product_quantity.text=orderRes.product_detail.product_name+"-"+orderRes.product_detail.quantity
        tv_st_date_sel.text=orderRes.product_detail.start_date
        tv_st_time_sel.text=orderRes.product_detail.start_time
        tv_end_date_sel.text=orderRes.product_detail.end_date
        tv_end_time_sel.text=orderRes.product_detail.end_time
        checkbox_with_driver.isChecked=orderRes.product_detail.with_driver
        tv_work_location.text=orderRes.product_detail.work_location
        tv_user_name.text=orderRes.agent_detail.full_name
        tv_user_tag.text=Constant.AGENT
        img_user_call.contentDescription=orderRes.agent_detail.mobile_number
        if(orderRes.order_status==Constant.COMPLETED)
        {
          if(orderRes.merchant_detail.isNotEmpty()){
              lyt_middle_view2.visibility=View.VISIBLE
              tv_users_name.text=""+orderRes.merchant_detail.get(1).full_name
              tv_users_tag.text=Constant.MERCHANT
              img_users_call.visibility=View.INVISIBLE
          }
          else{
              lyt_middle_view2.visibility=View.INVISIBLE
          }
            customer_payment_button.visibility=View.INVISIBLE
            payment_view_history.visibility=View.VISIBLE
            order_rate_review.visibility=View.VISIBLE

        }
        else if(orderRes.order_status==Constant.PENDING)
        {
            if(orderRes.merchant_detail.isNotEmpty()){
                lyt_middle_view2.visibility=View.VISIBLE
                tv_users_name.text=""+orderRes.merchant_detail.get(1).full_name
                tv_users_tag.text=Constant.MERCHANT
                img_users_call.visibility=View.INVISIBLE
            }
            else{
                lyt_middle_view2.visibility=View.INVISIBLE
            }
        }

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())
    }

}