package com.eazyrento.customer.dashboard.view.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.OrderBaseSummaryActivity
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderDetailsResModel
import com.eazyrento.customer.dashboard.model.modelclass.MerchantDetail
import com.eazyrento.customer.dashboard.view.adapter.CustomerOrderSummaryUsersAdapter
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
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

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_customer_order_summary)

        setDataAndCallAPI(intent.extras?.getInt(Constant.ORDER_SUMMERY_KEY)!!)
    }

    private fun clickListenerOnViews(){
       payment_view_history.setOnClickListener { MoveToAnotherComponent.moveToPaymentHistoryActivity(this) }
        order_rate_review.setOnClickListener { Common.showDialog(getString(R.string.rating),getString(R.string.thank_you),this,R.layout.rating_review) }
        customer_payment_button.setOnClickListener { MoveToAnotherComponent.moveToPaymentActivity(this) }
    }

    override fun <T> onSuccessApiResult(data: T) {
        super.onSuccessApiResult(data)
        orderStatus(orderRes)
    }

     fun orderStatus(orderRes: CustomerOrderDetailsResModel) {

        if(orderRes.order_status== Constant.COMPLETED)
        {
            if(orderRes.merchant_detail.isNotEmpty()){
                rec_user_order_summary.visibility=View.VISIBLE
                img_user_call.visibility=View.INVISIBLE
                setUsersAdapter(orderRes)
            }
            else{
                rec_user_order_summary.visibility=View.INVISIBLE
            }

        }
        else if(orderRes.order_status== Constant.PENDING)
        {
            if(orderRes.merchant_detail.isNotEmpty()){
                rec_user_order_summary.visibility=View.VISIBLE
                setUsersAdapter(orderRes)
            }
            else{
                rec_user_order_summary.visibility= View.INVISIBLE
            }
        }
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

    private fun setUsersAdapter(customerOderDetailsResponse: CustomerOrderDetailsResModel) {
        rec_user_order_summary.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false
        )
        (rec_user_order_summary.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
            1,
            1
        )

        val recycleAdapterUsersHomeCard =
            CustomerOrderSummaryUsersAdapter(
                customerOderDetailsResponse.merchant_detail as MutableList<MerchantDetail>,this)

        rec_user_order_summary.adapter = recycleAdapterUsersHomeCard
    }





}