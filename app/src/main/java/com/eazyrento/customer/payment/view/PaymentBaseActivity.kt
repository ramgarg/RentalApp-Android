package com.eazyrento.customer.payment.view

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderDetailsResModel
import com.eazyrento.customer.dashboard.viewmodel.CustomerOrderDetailsViewModel
import com.eazyrento.customer.payment.model.modelclass.BaseMakePaymentModel
import com.eazyrento.customer.payment.viewmodel.MakePaymentViewModel
import kotlinx.android.synthetic.main.activity_payment.*

open abstract class PaymentBaseActivity : BaseActivity() {

    private var totalPrice:Double = 0.0
    private lateinit var baseMakePaymentModel: BaseMakePaymentModel
    abstract fun getReqPaymentModel():BaseMakePaymentModel
    abstract fun requestPaymentObjectBuilder():BaseMakePaymentModel

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_payment)

        baseMakePaymentModel = getReqPaymentModel()

        callAPIOrderList(intent.getIntExtra(Constant.KEY_ORDER_DETAILS_ID,-1))

    }

    protected fun submitPayment(){

        val obj = requestPaymentObjectBuilder()

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<MakePaymentViewModel>(this)
                    .makePayment(intent.getIntExtra(Constant.KEY_ORDER_DETAILS_ID,-1),obj)
                , this, this
            )
        }
    }

    fun onSubmitClick(view: View){
        if (checkValidation()){
            
            showDialog(getString(R.string.payment),getString(R.string.thank_you),this,R.layout.thank_you_pop)
        }
    }

     open fun checkValidation(): Boolean {
            if (ed_enter_amount.text.isEmpty()) {
                showToast(ValidationMessage.ENTER_AMOUNT_PAYMENT)
                return false
            }
        else if (totalPrice.compareTo(ed_enter_amount.text.toString().toDouble())<0){
                showToast(ValidationMessage.ENTER_AMOUNT_PAYMENT_LESS_THEN_TO)
                return false
            }
       return true

    }

    // order details
    protected fun callAPIOrderList(id: Int){

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<CustomerOrderDetailsViewModel>(this)
                    .getOrderDetails(id)
                , this, this
            )
        }

    }

    override fun <T> onSuccessApiResult(data: T) {

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        setDataOnUI(data as CustomerOrderDetailsResModel)
    }

    protected fun setDataOnUI(customerOrderDetailsResModel: CustomerOrderDetailsResModel) {

         customerOrderDetailsResModel.order_id.let {
             tv_order_id.text =it
             baseMakePaymentModel.order_id =it
         }

        tv_pending_amount.text = resources.getString(R.string.pending_amount)+customerOrderDetailsResModel.pending_order_amount
        tv_total_price.text =resources.getString(R.string.booking_price)+customerOrderDetailsResModel.total_order_amount


        totalPrice = customerOrderDetailsResModel.total_order_amount
    }

}