package com.eazyrento.agent.view.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.agent.viewmodel.AgentUpdateOrderViewModel
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.OrderBaseSummaryActivity
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderDetailsResModel
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.template_order_summery_top_view.*

class AgentUpdateOrderActivity : OrderBaseSummaryActivity() {
    private lateinit var mOrdersDetailsReqResModel:CustomerOrderDetailsResModel

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_update_order_summary)

        customer_payment_button.text =resources.getString(R.string.update)
        customer_payment_button.setOnClickListener{
            onUpdateClick()
        }

        setDataAndCallOrderDetailsAPI(intent.getIntExtra(Constant.KEY_ORDER_DETAILS_ID,-1))

        topBarWithBackIconAndTitle(resources.getString(R.string.update_order))

        updateStatusSpinnerData()

    }

     private fun onUpdateClick(){
         updateOrderByID(intent.getIntExtra(Constant.KEY_ORDER_DETAILS_ID,-1))
     }
    private fun updateOrderByID(orderID:Int){
        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<AgentUpdateOrderViewModel>(this)
                    .updateOrder(orderID,mOrdersDetailsReqResModel)
                , this, this
            )
        }

    }

    private fun setDataOnUI(){

    }

    private fun updateStatusSpinnerData() {
        val update_Status = resources.getStringArray(R.array.UpdateStatus)

        val spinner = findViewById<Spinner>(R.id.status_spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, update_Status)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if(position==0){

                    }
                    else{

                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }
    override fun <T> onSuccessApiResult(data: T) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())
        //updated data
        if (data is JsonElement){
            showToast(ValidationMessage.REQUEST_SUCCESSED)
            return
        }
        super.onSuccessApiResult(data)
        mOrdersDetailsReqResModel = orderRes
    }
}