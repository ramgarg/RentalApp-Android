package com.eazyrento.common.view

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.customer.dashboard.model.modelclass.OrderDetailsResModel
import com.eazyrento.customer.dashboard.view.activity.CustomerMainActivity
import com.eazyrento.customer.payment.model.modelclass.BaseMakePaymentModel
import com.eazyrento.customer.payment.model.modelclass.CustomerMakePaymentReqModel
import com.eazyrento.customer.payment.view.PaymentBaseActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_payment.*

class TipActivity : PaymentBaseActivity(){

    private var modeOfPayment:String = ""

    override fun getReqPaymentModel(): BaseMakePaymentModel {
        return customerMakePaymentReqModel
    }

    private val customerMakePaymentReqModel = CustomerMakePaymentReqModel("",0.0,1,modeOfPayment,"","")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_payment)
        paymentMessage = resources.getString(R.string.thank_you)

        topBarWithBackIconAndTitle(getString(R.string.tip))

        lyt_pending.visibility=View.GONE
        lyt_approval.visibility=View.GONE
        ed_enter_amount.setHint(R.string.tip_amount)
        lyt_promo.visibility=View.GONE

        img_cash.setOnClickListener {
            check_cash.visibility= View.VISIBLE
            check_paypal.visibility= View.GONE
            modeOfPayment = Constant.CASH
        }

        img_paypal.setOnClickListener {
            check_cash.visibility= View.GONE
            check_paypal.visibility= View.VISIBLE
            modeOfPayment = Constant.PAYPAL
        }

    }

    override fun onClickDailog(int: Int) {
        when(int){

            Constant.OK ->submitPayment()

        }
    }

    override fun requestPaymentObjectBuilder(): BaseMakePaymentModel {
        customerMakePaymentReqModel.amount_paid = convertAmountIntoDoubleFromEditText()
        customerMakePaymentReqModel.mode_of_payment = modeOfPayment
        customerMakePaymentReqModel.is_tip =1
        customerMakePaymentReqModel.transaction_id=""

        return customerMakePaymentReqModel
    }

    override fun checkValidation(): Boolean {

        if (modeOfPayment.isEmpty()){
            showToast(R.string.PAYMENT_MODE)
            return false
        }else if (ed_enter_amount.text.isEmpty()) {
            showToast(R.string.ENTER_AMOUNT_PAYMENT)
            return false
        }
        return true
    }

    override fun <T> onSuccessApiResult(data: T) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        if (data is JsonElement){
            MoveToAnotherComponent.moveToActivityWithIntentValue<CustomerMainActivity>(this,Constant.INTENT_PAYMENT_SUCSESS,1)
            return
        }
        val obj = data as OrderDetailsResModel
        super.setDataOnUI(obj)
        //customerMakePaymentReqModel.status = obj.order_status
        customerMakePaymentReqModel.status = null
    }

}