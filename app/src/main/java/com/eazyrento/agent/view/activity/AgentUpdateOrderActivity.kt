package com.eazyrento.agent.view.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.agent.viewmodel.AgentUpdateOrderViewModel
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.common.view.OrderBaseSummaryActivity
import com.eazyrento.customer.dashboard.model.modelclass.OrderDetailsResModel
import com.eazyrento.customer.dashboard.model.modelclass.MerchantDetail
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_agent_update_order_summary.*
import kotlinx.android.synthetic.main.template_order_summery_top_view.*
import kotlinx.android.synthetic.main.template_update_quantity.view.*

class AgentUpdateOrderActivity : OrderBaseSummaryActivity() {
    private var mOrdersDetailsReqResModel:OrderDetailsResModel?=null

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_update_order_summary)

        customer_payment_button.text =resources.getString(R.string.update)

        customer_payment_button.visibility = View.INVISIBLE

        btn_update_agent_booking.setOnClickListener {
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
                    .updateOrder(orderID,mOrdersDetailsReqResModel!!)
                , this, this
            )
        }

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
                        mOrdersDetailsReqResModel?.order_status = update_Status[position]
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
            MoveToAnotherComponent.moveToActivityWithIntentValue<AgentMainActivity>(this,Constant.INTENT_UPDATE_ORDER_AGENT,1)
            return
        }
        super.onSuccessApiResult(data)
        mOrdersDetailsReqResModel = orderRes

        setMerchatDataOnUI()
    }

    private fun setMerchatDataOnUI() {

        lyt_middle_view.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false
        )
        (lyt_middle_view.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
            1,
            1
        )
        mOrdersDetailsReqResModel?.let { lyt_middle_view.adapter = MerchantListAdapter(this, it.merchant_detail,it)  }
    }
}

class MerchantListAdapter(val activity:BaseActivity,val list:List<MerchantDetail>,val objMerc:OrderDetailsResModel):RecyclerView.Adapter<MerchantListAdapter.CardViewHolder>(){

    class CardViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tv_name_user = view.tv_name_user
        val item_quantity = view.item_quantity
        val tv_total_booking_price = view.tv_total_booking_price
        val img_minus= view.minus_quantity
        val img_plus= view.add_quantity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
      return CardViewHolder(LayoutInflater.from(activity).inflate(R.layout.template_update_quantity,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = list[position]

        holder.tv_name_user.text = item.full_name
        holder.item_quantity.text = ""+item.quantity
        holder.tv_total_booking_price.text = ""+item.amount

        holder.img_minus.setOnClickListener{
           item.quantity = item.quantity-1
            holder.item_quantity.text = ""+item.quantity
        }
        holder.img_plus.setOnClickListener{
            item.quantity = item.quantity+1
            holder.item_quantity.text = ""+item.quantity
        }
    }
}