package com.eazyrento.customer.dashboard.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.eazyrento.Constant
import com.eazyrento.Env
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.customer.dashboard.model.modelclass.OrderDetailsResModel
import com.eazyrento.customer.payment.model.modelclass.BaseMakePaymentModel
import com.eazyrento.customer.payment.model.modelclass.CustomerMakePaymentReqModel
import com.eazyrento.customer.payment.model.modelclass.PaymentGetwayCheckoutIDResModel
import com.eazyrento.customer.payment.view.PaymentBaseActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.paymentgetway.PaymentCheckout
import com.eazyrento.webservice.PathURL
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_payment.*

class CustomerPaymentActivity : PaymentBaseActivity() {

    private var modeOfPayment:String = ""

    override fun getReqPaymentModel(): BaseMakePaymentModel {
        return customerMakePaymentReqModel
    }

    private val customerMakePaymentReqModel = CustomerMakePaymentReqModel("",0.0,0,modeOfPayment,"","")

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        paymentMessage = resources.getString(R.string.thank_you)

        //MoveToAnotherComponent.moveToActivityNormal<PaymentCheckout>(this)

        topBarWithBackIconAndTitle(getString(R.string.payment))


        img_cash.setOnClickListener {
            check_cash.visibility=View.VISIBLE
            check_paypal.visibility=View.GONE
            modeOfPayment = Constant.CASH
        }

        img_paypal.setOnClickListener {
            check_cash.visibility=View.GONE
            check_paypal.visibility=View.VISIBLE
            modeOfPayment = Constant.PAYPAL
        }

    }

    override fun onClickDailog(int: Int) {
        when(int){

            Constant.OK ->{
                submitPayment()
            }

        }
    }

    override fun requestPaymentObjectBuilder():BaseMakePaymentModel {

        customerMakePaymentReqModel.amount_paid = convertAmountIntoDoubleFromEditText()
        customerMakePaymentReqModel.mode_of_payment = modeOfPayment
        customerMakePaymentReqModel.is_tip =0
        customerMakePaymentReqModel.transaction_id=""

        return customerMakePaymentReqModel

    }


      override fun checkValidation(): Boolean {

          if (modeOfPayment.isEmpty()){
              showToast(R.string.PAYMENT_MODE)
              return false
          }

          else
              return  super.checkValidation()

    }



    override fun <T> onSuccessApiResult(data: T) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        if (data is PaymentGetwayCheckoutIDResModel){
//            paymentGetwayCheckoutIDResModel = data

            openPaymentGetWayPage(data)
            return
        }

        if (data is JsonElement){
            MoveToAnotherComponent.moveToActivityWithIntentValue<CustomerMainActivity>(this,Constant.INTENT_PAYMENT_SUCSESS,1)
            return
        }
        val obj = data as OrderDetailsResModel
        super.onSuccessApiResult(obj)
        customerMakePaymentReqModel.status = obj.order_status
    }

    private fun openPaymentGetWayPage(data: PaymentGetwayCheckoutIDResModel) {
        //MoveToAnotherComponent.moveToActivityNormal<PaymentCheckout>(this)
//        <user_id>/<checkout_id>/<order_id>

        button_submit.visibility = View.GONE
        PaymentCheckout(this,webview_payment_getway,Env.BASE_URL.plus(PathURL.PAYMENT_GETWAY_URL)
            .plus(data.user_id).plus("/").plus(data.checkout_id).plus("/").plus(data.order_id).plus("/").plus(data.trnx_id)
            ,data.order_id)
    }

    fun paymentGetwayCallback(msg:String){
        showToastString(msg)
        MoveToAnotherComponent.moveToActivityWithIntentValue<CustomerMainActivity>(this,Constant.INTENT_PAYMENT_SUCSESS,1)
    }

}