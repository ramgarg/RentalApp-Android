package com.eazyrento.agent.view.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
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

        required_quantity.text = getString(R.string.req_quantity).plus(bookingITem.product_detail.quantity.toString())

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
            MoveToAnotherComponent.moveToActivityWithIntentValue<AgentMainActivity>(this,Constant.INTENT_SUCCESS_ADDED_PRODUCT,1)
            showToast(ValidationMessage.REQUEST_SUCCESSED)
            return
        }
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())
        val data = data as AgentMerchantFindNearByResModel
        merchantListItem = data.merchants_list

        rec_agent_assign_merchant.adapter =AssignMerchnatAdapter(this,data.merchants_list,this)

        rec_agent_assign_merchant.layoutManager = GridLayoutManager(this,2)
    }

    override fun setDataHolder(holder: AssignMerchnatAdapter.CardViewHolder, position: Int) {
        holder.assign_merchant_name.text =merchantListItem.get(position).details.full_name
        holder.booking_price.text = merchantListItem.get(position).details.price.toString()
//        holder.booking_total_prcie = merchantListItem
//        holder.layout_truck_quantity.text =""+merchantListItem.get(position).details.quantity_available

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
        assignMerchantsReqModel.merchant_list.add(Merchant(details.price,details.merchant_id,details.price,details.assign_quantity))

    }
    private fun isQuantitiyCorrect():Boolean{
        var tempQuantity = 0
          for (item in assignMerchantsReqModel.merchant_list){
              tempQuantity+=item.quantity
          }
        return when{
            bookingITem.product_detail.quantity == tempQuantity ->true
            else ->false
        }
    }
    private fun isvalidationCorrect():Boolean{
        if (assignMerchantsReqModel.merchant_list.isEmpty())
        {
            showToast(ValidationMessage.SELECT_MERCHANT)
            return false
        }else if (isQuantitiyCorrect().not()){
            showToast(ValidationMessage.QUANTITY_LIMIT)
              return false
        }else
           return true
    }
    //
    fun assignMerchant(view:View) {

        if (isvalidationCorrect()) {

            callAPI()?.let {
                it.observeApiResult(
                    it.callAPIActivity<AgentAssignMerchantViewModel>(this)
                        .assignMerchants(assignMerchantsReqModel)
                    , this, this
                )
            }
        }
    }
}