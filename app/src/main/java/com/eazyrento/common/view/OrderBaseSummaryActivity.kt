package com.eazyrento.common.view

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderDetailsResModel
import com.eazyrento.customer.dashboard.viewmodel.CustomerOrderDetailsViewModel
import com.eazyrento.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.order_summary_template.tv_end_date_sel
import kotlinx.android.synthetic.main.order_summary_template.tv_end_time_sel
import kotlinx.android.synthetic.main.order_summary_template.tv_st_date_sel
import kotlinx.android.synthetic.main.order_summary_template.tv_st_time_sel
import kotlinx.android.synthetic.main.template_order_summery_top_view.*
import kotlinx.android.synthetic.main.template_work_info.*
import kotlinx.android.synthetic.main.toolbar.*


open abstract class OrderBaseSummaryActivity : BaseActivity() {
    lateinit var orderRes:CustomerOrderDetailsResModel
    override fun <T> moveOnSelecetedItem(type: T) {
    }


    protected fun setDataAndCallOrderDetailsAPI(int: Int){

        callAPIOrderList(int)

        ViewVisibility.isVisibleOrNot(this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.order_summary))

        clickListenerOnViews()
    }

    private fun clickListenerOnViews(){
    }

// order details
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

         orderRes = data as CustomerOrderDetailsResModel

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        tv_order_product_name.text=orderRes.product_detail.product_name
        tv_booking_price.text= Constant.DOLLAR+orderRes.product_detail.starting_price
        tv_order_id.text= Constant.ORDER_ID+orderRes.order_id
        order_product_quantity.text=orderRes.product_detail.product_name+"-"+orderRes.product_detail.quantity
        tv_st_date_sel.text=orderRes.product_detail.start_date
        tv_st_time_sel.text=orderRes.product_detail.start_time
        tv_end_date_sel.text=orderRes.product_detail.end_date
        tv_end_time_sel.text=orderRes.product_detail.end_time
        checkbox_with_driver.isChecked=orderRes.product_detail.with_driver
        tv_work_location.text=orderRes.product_detail.work_location

        setOrderStatus(orderRes)

        /*if(orderRes.agent_detail!=null){
            users_view.visibility=View.VISIBLE
            tv_users_name.text=orderRes.agent_detail.full_name
            tv_users_tag.text= orderRes.agent_detail.mobile_number
            //img_users_call.contentDescription=orderRes.agent_detail.mobile_number
        }
        else{
            users_view.visibility=View.INVISIBLE
        }*/


    }

    fun setOrderStatus(orderRes: CustomerOrderDetailsResModel) {

        when(orderRes.order_status){
            Constant.COMPLETED ->{
                customer_payment_button.visibility=View.INVISIBLE
                payment_view_history.visibility=View.VISIBLE
                order_rate_review.visibility=View.VISIBLE
            }
            Constant.PENDING ->{
            }
        }


        //tv_user_name.text=orderRes.agent_detail.full_name
        //tv_user_tag.text= Constant.AGENT
        //img_user_call.contentDescription=orderRes.agent_detail.mobile_number


    }

//    abstract fun orderStatus(orderRes: CustomerOrderDetailsResModel)

}