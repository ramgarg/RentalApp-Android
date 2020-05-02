package com.eazyrento.common.view

import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.customer.dashboard.viewmodel.CustomerOrderDetailsViewModel
import com.eazyrento.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.order_summary_template.*
import kotlinx.android.synthetic.main.toolbar.*


open abstract class OrderBaseSummaryActivity : BaseActivity() {

    private fun clickListenerOnViews(){
    }

    private fun setResponseViews(){
        tv_st_date_sel.text="12 Jan 2020"
        tv_st_time_sel.text="4:00pm"
        tv_end_date_sel.text="12 Feb 2020"
        tv_end_time_sel.text="3:00pm"
       // checkbox_with_driver.isClickable=false


       // callAPIOrderList(intent.extras?.getInt(Constant.ORDER_SUMMERY_KEY)!!)
    }


    override fun onStart() {
        super.onStart()
        ViewVisibility.isVisibleOrNot(this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.order_summary))
        setResponseViews()

        clickListenerOnViews()

    }

    override fun onPause() {
        super.onPause()
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
    override fun <T> moveOnSelecetedItem(type: T) {
    }
}