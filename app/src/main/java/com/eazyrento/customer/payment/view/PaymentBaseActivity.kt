package com.eazyrento.customer.payment.view

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.Env
import com.eazyrento.R
import com.eazyrento.appbiz.amountWithCurrencyName
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.dashboard.model.modelclass.OrderDetailsResModel
import com.eazyrento.customer.dashboard.view.activity.CustomerMainActivity
import com.eazyrento.customer.dashboard.viewmodel.CustomerOrderDetailsViewModel
import com.eazyrento.customer.payment.model.modelclass.BaseMakePaymentModel
import com.eazyrento.customer.payment.model.modelclass.CustomerMakePaymentReqModel
import com.eazyrento.customer.payment.model.modelclass.PaymentGetwayCheckoutIDReqModel
import com.eazyrento.customer.payment.model.modelclass.PaymentGetwayCheckoutIDResModel
import com.eazyrento.customer.payment.viewmodel.MakePaymentViewModel
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.paymentgetway.PaymentCheckout
import com.eazyrento.webservice.PathURL
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.thank_you_pop.*

open abstract class PaymentBaseActivity : BaseActivity() {

    protected var amountToPay:Double = 0.0
    protected var paymentMessage: String = ""
    private lateinit var baseMakePaymentModel: BaseMakePaymentModel
    abstract fun getReqPaymentModel():BaseMakePaymentModel
    abstract fun requestPaymentObjectBuilder():BaseMakePaymentModel
    protected var is_tip: Int = 0

    protected var totalPrice:Double =0.0

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_payment)

        baseMakePaymentModel = getReqPaymentModel()

        callAPIOrderList(intent.getIntExtra(Constant.KEY_ORDER_DETAILS_ID,-1))

    }

    protected fun submitPayment(){


        baseMakePaymentModel = requestPaymentObjectBuilder()
        if (baseMakePaymentModel is CustomerMakePaymentReqModel){
           val cusObj = baseMakePaymentModel as CustomerMakePaymentReqModel
            if(cusObj.mode_of_payment==Constant.PAYPAL) {
                callPaymentGetway(cusObj.amount_paid)
                return
            }
        }
             callAPIMakePayment()
    }
    private fun callPaymentGetway(amountPaid: Double) {
        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<MakePaymentViewModel>(this)
                    .paymentGetwayCheckOutID(intent.getIntExtra(Constant.KEY_ORDER_DETAILS_ID,-1),
                        PaymentGetwayCheckoutIDReqModel(amountPaid,is_tip)
                    )
                , this, this
            )
        }
    }

    private fun callAPIMakePayment(){
        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<MakePaymentViewModel>(this)
                    .makePayment(intent.getIntExtra(Constant.KEY_ORDER_DETAILS_ID,-1),baseMakePaymentModel)
                , this, this
            )
        }
    }

    fun onSubmitClick(view: View){
        if (checkValidation()){
            
            showDialog(R.string.payment,paymentMessage,this,R.layout.thank_you_pop).let {dailog->
                dailog.btn_cancle.run {
                    visibility = View.VISIBLE
                    setOnClickListener {
                        dailog.cancel()
                    }
                }
            }

        }
    }

     open fun checkValidation(): Boolean {
            if (ed_enter_amount.text.isEmpty()) {
                showToast(R.string.ENTER_AMOUNT_PAYMENT)
                return false
            }
        else if (amountToPay.compareTo(removeDollarSign(ed_enter_amount.text.toString()).toDouble())<0){
                showToast(R.string.ENTER_AMOUNT_PAYMENT_LESS_THEN_TO)
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

        setDataOnUI(data as OrderDetailsResModel)
    }

    protected fun setDataOnUI(customerOrderDetailsResModel: OrderDetailsResModel) {

         customerOrderDetailsResModel.order_id.let {
             tv_order_id.text =resources.getString(R.string.orderid).plus(it)
             baseMakePaymentModel.order_id =it
         }

        /*tv_total_price.text =Constant.DOLLAR.plus(customerOrderDetailsResModel.order_amount_with_commission)
        tv_pending_amount.text = Constant.DOLLAR.plus(customerOrderDetailsResModel.amount_to_pay)

        tv_pending_approval.text=Constant.DOLLAR.plus(customerOrderDetailsResModel.amount_pending_for_approval)


        amountToPay = customerOrderDetailsResModel.amount_to_pay*/

        totalPrice = Common.roundOfDouble(customerOrderDetailsResModel.order_amount_with_commission)

        amountDataRefrash(customerOrderDetailsResModel.amount_to_pay,
            customerOrderDetailsResModel.amount_pending_for_approval)
    }

    protected fun amountDataRefrash(amount_to_pay:Double,amount_pending_for_approval:Double){

        tv_total_price.text =amountWithCurrencyName(totalPrice)
        tv_pending_amount.text = amountWithCurrencyName(Common.roundOfDouble(amount_to_pay))

        tv_pending_approval.text=amountWithCurrencyName(amount_pending_for_approval)


        amountToPay = amount_to_pay
    }

    protected fun convertAmountIntoDoubleFromEditText():Double{
        return removeDollarSign(ed_enter_amount.text.toString()).toDouble()
    }
    fun removeDollarSign(amount:String):String{
        return amount
        //return amount.removePrefix(Constant.DOLLAR)
    }

    protected fun openPaymentGetWayPage(data: PaymentGetwayCheckoutIDResModel) {
        //MoveToAnotherComponent.moveToActivityNormal<PaymentCheckout>(this)
//        <user_id>/<checkout_id>/<order_id>

        button_submit.visibility = View.GONE
        PaymentCheckout(this,webview_payment_getway, Env.BASE_URL.plus(PathURL.PAYMENT_GETWAY_URL)
            .plus(data.user_id).plus("/").plus(data.checkout_id).plus("/").plus(data.order_id).plus("/").plus(data.trnx_id).plus("/").plus(data.is_tip)
            ,data.order_id)
    }

    fun paymentGetwayCallback(msg:String){
        showToastString(msg)
        MoveToAnotherComponent.moveToActivityWithIntentValue<CustomerMainActivity>(this,Constant.INTENT_PAYMENT_SUCSESS,1)
    }


}