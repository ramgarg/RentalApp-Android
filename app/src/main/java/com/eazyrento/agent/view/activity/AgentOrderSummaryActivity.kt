package com.eazyrento.agent.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.agent.model.modelclass.AgentFeedbackReqModel
import com.eazyrento.agent.model.modelclass.AgentUpdateOrderReqModel
import com.eazyrento.agent.view.SupportinAgentUpdateOrder
import com.eazyrento.agent.view.UpdateOrderInterface
import com.eazyrento.agent.viewmodel.AgentUpdateOrderViewModel
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.MaintanceUserRoleView
import com.eazyrento.common.view.OrderBaseSummaryActivity
import com.eazyrento.customer.dashboard.model.modelclass.SubOrderReqResModel
import com.eazyrento.customer.payment.view.PaymentHistoryActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_base_order_summary.*
import kotlinx.android.synthetic.main.adapter_suborder_row.view.*
import kotlinx.android.synthetic.main.adapter_users_order_summary.*
import kotlinx.android.synthetic.main.maintance_layout.*
import kotlinx.android.synthetic.main.phone_view.*
import kotlinx.android.synthetic.main.template_order_summery_top_view.*

open class AgentOrderSummaryActivity : OrderBaseSummaryActivity() {
    private var supportinAgentUpdateOrder:SupportinAgentUpdateOrder?=null
    private var mBookingID:Int = -1

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        agent_asign_merchant_and_request_payment.visibility = View.VISIBLE
        payment_view_history.visibility=View.VISIBLE
        agent_update_order_btn.visibility=View.VISIBLE
        customer_payment_button.visibility = View.GONE

        mBookingID = intent.getIntExtra(Constant.ORDER_SUMMERY_KEY, -1)

        // order details
        if (mBookingID != -1)
            setDataAndCallOrderDetailsAPI(mBookingID)
        clickListenerOnViews()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"onNewIntent")
        if (mBookingID != -1)
            setDataAndCallOrderDetailsAPI(mBookingID)
    }
    private fun clickListenerOnViews() {

        agent_asign_merchant_and_request_payment.setOnClickListener {
            MoveToAnotherComponent.moveToActivityWithIntentValue<AgentPaymentActivity>(this,Constant.KEY_ORDER_DETAILS_ID,
                intent.extras?.getInt(Constant.ORDER_SUMMERY_KEY, -1)!!)
        }

        payment_view_history.setOnClickListener {

            MoveToAnotherComponent.moveToActivityWithIntentValue<PaymentHistoryActivity>(this,
                Constant.KEY_PAYMENT_HISTORY,orderRes.order_id)
        }
        agent_update_order_btn.setOnClickListener {

            /*MoveToAnotherComponent.moveToActivityWithIntentValue<AgentUpdateOrderActivity>(this,Constant.KEY_ORDER_DETAILS_ID,
                intent.extras?.getInt(Constant.ORDER_SUMMERY_KEY, -1)!!)*/

            //changes in order flow..
            val id = intent.getIntExtra(Constant.ORDER_SUMMERY_KEY, -1)

            supportinAgentUpdateOrder?.onUpdateClick(object :UpdateOrderInterface{
                override fun onOrderUpdate(orderUpdate: AgentUpdateOrderReqModel) {
                    updateOrderByID(id,orderUpdate)
                }

            })

        }


    }

    private fun updateOrderByID(orderID:Int,orderUpdate: AgentUpdateOrderReqModel){
        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<AgentUpdateOrderViewModel>(this)
                    .updateOrder(orderID,orderUpdate)
                , this, this
            )
        }

    }

    override fun <T> onSuccessApiResult(data: T) {

//  update oder when user update any data of booking order
        if (data is JsonElement){
            showToast(ValidationMessage.REQUEST_SUCCESSED)
            MoveToAnotherComponent.moveToActivityWithIntentValue<AgentMainActivity>(this,Constant.INTENT_UPDATE_ORDER_AGENT,1)
            return
        }

        super.onSuccessApiResult(data)

        if (agent_update_order_btn.isVisible) {
            supportinAgentUpdateOrder = SupportinAgentUpdateOrder(this)
            supportinAgentUpdateOrder?.init(orderRes)
        }

//        before changes
//        setMaintanceUserRoleAdapter(orderRes.customer_detail,null,orderRes.merchant_detail)

        // after changes in agent update flow on 19 jun 2020.....now added sub order here
          setMaintanceUserRoleAdapter(orderRes.customer_detail,null,null)

        // sub order horizontal list
        orderRes.sub_orders?.let { recycle_sub_order.adapter = AdapterSubOrder(this,it) }

        }
}

class AdapterSubOrder(val context: Context,val subOrderList:List<Int>):RecyclerView.Adapter<AdapterSubOrder.HolderSubOrder>(){

    class HolderSubOrder(view: View):RecyclerView.ViewHolder(view){
          val tv_suborder = view.tv_suborder
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderSubOrder {
        return HolderSubOrder(LayoutInflater.from(context).inflate(R.layout.adapter_suborder_row,parent,false))
    }

    override fun getItemCount(): Int {
        return subOrderList.size
    }

    override fun onBindViewHolder(holder: HolderSubOrder, position: Int) {
        holder.tv_suborder.text =context.resources.getString(R.string.sub_order).plus(" ").plus(position+1)
        holder.tv_suborder.setOnClickListener{
            // sub order activity call
             MoveToAnotherComponent.moveToActivityWithIntentValue<AgentSubOrderActivity>(context,Constant.INTENT_AGENT_SUB_ORDER,subOrderList[position])
        }
    }

}


