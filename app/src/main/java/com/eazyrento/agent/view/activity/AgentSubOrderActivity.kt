package com.eazyrento.agent.view.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.agent.viewmodel.AgentSubOrderViewModel
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.appbiz.amountWithCurrencyName
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.dashboard.model.modelclass.BaseUserRoleDetail
import com.eazyrento.customer.dashboard.model.modelclass.SubOrderReqResModel
import com.eazyrento.customer.dashboard.model.modelclass.SubOrderUpdateReqModel
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.google.gson.JsonElement
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_users_order_summary.*
import kotlinx.android.synthetic.main.phone_view.*
import kotlinx.android.synthetic.main.template_order_summery_top_view.*
import kotlinx.android.synthetic.main.template_quantity_view.*

class AgentSubOrderActivity :BaseActivity() {
    private var subOrderReqResModel: SubOrderReqResModel?=null
    private var mId: Int = 0
    private var mStatus:String?=null

    private val arrayUpdateStatus = arrayOf<String>("update status","pending","started","completed","cancelled")

//    private var price:Double =0.0

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_update_sub_order)

        topBarWithBackIconAndTitle(getString(R.string.sub_order))

        customer_payment_button.visibility = View.GONE

        booking_price.text = getString(R.string.sub_order_price)

        mId = intent.extras?.getInt(Constant.INTENT_AGENT_SUB_ORDER,0)!!

        callSubOrderAPIOrderList(mId)
    }

    // order details
    private fun callSubOrderAPIOrderList(id: Int){

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<AgentSubOrderViewModel>(this)
                    .agentSubOrder(id)
                , this, this
            )
        }

    }


    override fun <T> onSuccessApiResult(data: T) {
        super.onSuccessApiResult(data)

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        if (data is JsonElement){
            showToast(R.string.REQUEST_SUCCESSED)
            MoveToAnotherComponent.moveToActivityNormal<AgentOrderSummaryActivity>(this)
            return
        }

        if (data is SubOrderReqResModel) {
            subOrderReqResModel = data
            setTopView(data)
            setQunatity(data)
            data.merchant_detail?.let {  setMerchantView(it) }
            updateStatusSpinnerData(data)


        }
    }

    private fun setQunatity(data: SubOrderReqResModel) {
        item_quantity.text = data.quantity.toString()

        minus_quantity.setOnClickListener{

            if (data.quantity-1 <=0){
                Common.showToast(this, R.string.FILL_QUANTITY)
            }
            else {
                data.quantity = data.quantity-1
                item_quantity.text = data.quantity.toString()
            }
        }
        add_quantity.setOnClickListener{
            data.quantity = data.quantity+1
            item_quantity.text = data.quantity.toString()
        }
    }

    private fun setTopView(data: SubOrderReqResModel) {

        tv_order_id.text= resources.getString(R.string.orderid).plus(data.order_id)

        tv_booking_price.text= amountWithCurrencyName(data.amount)

        order_product_quantity.text = resources.getString(R.string.quantity).plus(data.quantity)


        data.product_detail?.let {
            tv_order_product_name.text = it.product_name?.capitalize()
        }
    }

    fun setMerchantView(base: BaseUserRoleDetail){

        tv_user_name.text = base.full_name.capitalize()

        tv_users_role.text = getString(R.string.merchant)//Constant.MERCHANT

        phone_view.setOnClickListener {
            Common.phoneCallWithNumber(base.mobile_number, this)
        }
//        rattingAndReviewClickListener(maintanceUserRoleView,base)

//        maintanceUserOrderStatus(maintanceUserRoleView)

        Picasso.with(this).load(base.profile_image).into(img_user_pic)
    }
    // update order status

    private fun updateStatusSpinnerData(data: SubOrderReqResModel) {

        val update_Status = resources.getStringArray(R.array.UpdateStatusAgent)
        val spinner = findViewById<Spinner>(R.id.status_spinner)


        if (spinner != null) {
            val adapter = ArrayAdapter(this, R.layout.spinner_item_style, update_Status)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if(position==0){
                        mStatus = null
                    }
                    else{
                       // mStatus = update_Status[position]
                        mStatus = arrayUpdateStatus[position]
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        // set spinner data which alrday
        spinner.setSelection(
            getComparedPostion(
                update_Status,
                data.order_status
            )
        )
    }

    private fun getComparedPostion(spinnerDataByID: Array<String>, variant: String?): Int {

        for (i in spinnerDataByID.indices) {
            if (spinnerDataByID[i] == variant) {

                return i
            }
        }
        return 0
    }

    fun onUpdateButtonClick(view: View){

        if(isValidated().not()) return

        val subOrderUpdateReqModel = SubOrderUpdateReqModel(subOrderReqResModel?.price!!,item_quantity.text.toString().toInt(),mStatus!!)

            callAPI()?.let {
                it.observeApiResult(
                    it.callAPIActivity<AgentSubOrderViewModel>(this)
                        .agentSubOrderUpdate(mId,subOrderUpdateReqModel)
                    , this, this
                )
        }


    }

    private fun isValidated():Boolean{
        return if(mStatus== null) {showToast(R.string.SELECT_SUB_ORDER_STATUS);false } else true
    }

}