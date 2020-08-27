package com.eazyrento.customer.payment.view

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.dashboard.model.modelclass.OrderDetailsResModel
import com.eazyrento.customer.dashboard.viewmodel.CustomerOrderDetailsViewModel
import com.eazyrento.customer.payment.model.modelclass.BaseMakePaymentModel
import com.eazyrento.customer.payment.model.modelclass.CustomerMakePaymentReqModel
import com.eazyrento.customer.payment.model.modelclass.PaymentGetwayCheckoutIDReqModel
import com.eazyrento.customer.payment.model.modelclass.PaymentGetwayCheckoutIDResModel
import com.eazyrento.customer.payment.viewmodel.MakePaymentViewModel
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.thank_you_pop.*

open abstract class PaymentBaseActivity : BaseActivity() {

    protected var amountToPay:Double = 0.0
    private lateinit var baseMakePaymentModel: BaseMakePaymentModel
    private lateinit var paymentGetwayCheckoutIDResModel: PaymentGetwayCheckoutIDResModel
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
                        PaymentGetwayCheckoutIDReqModel(1.0)
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
            
            showDialog(R.string.payment,resources.getString(R.string.thank_you),this,R.layout.thank_you_pop).let {dailog->
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

        tv_total_price.text =Constant.DOLLAR.plus(customerOrderDetailsResModel.order_amount_with_commission)
        tv_pending_amount.text = Constant.DOLLAR.plus(customerOrderDetailsResModel.amount_to_pay)

        tv_pending_approval.text=Constant.DOLLAR.plus(customerOrderDetailsResModel.amount_pending_for_approval)


        amountToPay = customerOrderDetailsResModel.amount_to_pay
    }

    protected fun convertAmountIntoDoubleFromEditText():Double{
        return removeDollarSign(ed_enter_amount.text.toString()).toDouble()
    }
    fun removeDollarSign(amount:String):String{
        return amount
        //return amount.removePrefix(Constant.DOLLAR)
    }




}