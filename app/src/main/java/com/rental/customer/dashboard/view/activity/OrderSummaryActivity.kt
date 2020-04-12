package com.rental.customer.dashboard.view.activity

import android.os.Bundle
import com.rental.Constant
import com.rental.R
import com.rental.appbiz.AppBizLogger
import com.rental.common.view.BaseActivity
import com.rental.customer.dashboard.viewmodel.CustomerOrderDetailsViewModel
import com.rental.customer.dashboard.viewmodel.CustomerOrderListViewModel
import com.rental.customer.dashboard.viewmodel.OrderSummaryViewModel
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.Common.Companion.hideGroupViews
import com.rental.customer.utils.Common.Companion.invisibleGroupViews
import com.rental.customer.utils.Common.Companion.showGroupViews
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.order_summary_activity.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class OrderSummaryActivity : BaseActivity() {
//     private var productID:Int?=-1

    lateinit var orderSummaryViewModel :OrderSummaryViewModel
    override fun <T> moveOnSelecetedItem(type: T) {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.order_summary_activity)
//       productID = intent.extras?.getInt(Constant.ORDER_SUMMERY_KEY)

//        orderSummaryViewModel=ViewModelProviders.of(this).get(OrderSummaryViewModel::class.java)
//        orderSummaryViewModel.getOrderSummaryResponse().observe(this, Observer {
//            //Here response will come form api
        setResponseViews()
//        })

        ViewVisibility.isVisibleOrNot(this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.order_summary))

        clickListenerOnViews()

    }

    private fun clickListenerOnViews(){
        tv_view_history.setOnClickListener { MoveToAnotherComponent.moveToPaymentHistoryActivity(this) }
        tv_pay_now.setOnClickListener { MoveToAnotherComponent.moveToPaymentActivity(this) }
    }

    private fun setResponseViews(){
        tv_st_date_sel.text="12 Jan 2020"
        tv_st_time_sel.text="4:00pm"
        tv_end_date_sel.text="12 Feb 2020"
        tv_end_time_sel.text="3:00pm"
        checkbox_with_driver.isClickable=false

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
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

            tv_give_tip.setOnClickListener {
                MoveToAnotherComponent.moveToPaymentActivity(this)
            }
        }

        callAPIOrderList(intent.extras?.getInt(Constant.ORDER_SUMMERY_KEY)!!)

    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onPause() {
        super.onPause()
        EventBus.getDefault().unregister(this)
    }

    private fun callAPIOrderList(id: Int){

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<CustomerOrderDetailsViewModel>(this)
                    .getOrderDetails(id!!)
                , this, this
            )

        }

    }

    override fun <T> onSuccessApiResult(data: T) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())
    }

}