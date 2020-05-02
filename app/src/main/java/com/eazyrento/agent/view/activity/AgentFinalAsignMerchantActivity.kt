package com.eazyrento.agent.view.activity

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.agent.model.modelclass.*
import com.eazyrento.agent.view.adapter.AssignMerchnatAdapter
import com.eazyrento.agent.view.adapter.BookingDataHolderBinder
import com.eazyrento.agent.viewmodel.AgentAssignMerchantViewModel
import com.eazyrento.agent.viewmodel.AgentMerchantNeearByViewModel
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.model.modelclass.BookingListItem
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_agent_assign_merchant.*
import kotlinx.android.synthetic.main.toolbar.*

class AgentFinalAsignMerchantActivity : BaseActivity(), BookingDataHolderBinder {

    lateinit  var merchantListItem:List<Merchants>
    val assignMerchantsReqModel = AssignMerchantsReqModel()
    lateinit var  bookingITem:BookingListItem

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_assign_merchant)

        ViewVisibility.isVisibleOrNot(this,img_back,img_menu,img_notification,
            toolbar_title,"Assign merchants")

         bookingITem = intent.getParcelableExtra<BookingListItem>(Constant.BOOKING_SUMMERY_KEY)

        assignMerchantsReqModel.booking_id =bookingITem.id
        assignMerchantsReqModel.order_id =bookingITem.order_id

        callNearByMerchantAPI(bookingITem.id)

    }

    private fun callNearByMerchantAPI(id:Int) {
        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<AgentMerchantNeearByViewModel>(this)
                    .nearMerchantBy(id)
                , this, this
            )
        }
    }

    override fun <T> onSuccessApiResult(data: T) {
        if (data is JsonElement){
            MoveToAnotherComponent.moveToActivity<AgentMainActivity>(this,Constant.INTENT_SUCCESS_ADDED_PRODUCT,1)
            showToast(ValidationMessage.REQUEST_SUCCESSED)
            return
        }
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())
        val data = data as AgentMerchantFindNearByResModel
        merchantListItem = data.merchants_list

        rec_agent_assign_merchant.adapter =AssignMerchnatAdapter(this,data.merchants_list,this)
    }

    override fun setDataHolder(holder: AssignMerchnatAdapter.CardViewHolder, position: Int) {
        holder.assign_merchant_name.text =merchantListItem.get(position).details.full_name
        holder.booking_price.text = ""+merchantListItem.get(position).details.price
//        holder.booking_total_prcie = merchantListItem
        holder.layout_truck_quantity.text =""+merchantListItem.get(position).details.quantity_available

//        Picasso.with(context).load(merchantListItem.get(position).details.)
        holder.chkbox_assign_merchnat.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
                fillAssignMerchantReqModel(merchantListItem.get(position).details)
            else
                removeMerchantFromList(merchantListItem.get(position).details)

        }

    }

    private fun removeMerchantFromList(details: Details) {
       for (obj in assignMerchantsReqModel.merchant_list){
           if (obj.merchant_id.equals(details.merchant_id)){
               assignMerchantsReqModel.merchant_list.remove(obj)
               return
           }
       }
    }

    private fun fillAssignMerchantReqModel(details: Details) {


        //merchantdetails

        assignMerchantsReqModel.merchant_list.add(Merchant(details.price,details.merchant_id,details.price,details.quantity_available))

    }

    //
    fun assignMerchant(view:View){

        if (assignMerchantsReqModel.merchant_list.size==0)
        {
            showToast(ValidationMessage.SELECT_MERCHANT)
            return
        }

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<AgentAssignMerchantViewModel>(this).assignMerchants(assignMerchantsReqModel)
                , this, this
            )
        }
    }
}