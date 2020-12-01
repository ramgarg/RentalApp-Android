package com.eazyrento.customer.dashboard.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.eazyrento.Constant
import com.eazyrento.Env
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.model.modelclass.ApplyPromoCodeReqModel
import com.eazyrento.common.model.modelclass.ApplyPromoCodeResModel
import com.eazyrento.customer.dashboard.model.modelclass.OrderDetailsResModel
import com.eazyrento.customer.dashboard.viewmodel.ApplyPromoCodeViewModel
import com.eazyrento.customer.dashboard.viewmodel.CustomerCreateBookingViewModel
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
        is_tip = 0


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

        setApplyPromoCodeAPIListener()

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

        if (data is ApplyPromoCodeResModel){
            //
            //ed_apply_copon.setText("")
            ed_enter_amount.setText("")

            amountDataRefrash(data.amountToPay,data.amountPendingForApproval)
            return
        }

        if (data is PaymentGetwayCheckoutIDResModel){
//            paymentGetwayCheckoutIDResModel = data
           // data.is_tip=0

            openPaymentGetWayPage(data)
            return
        }

        if (data is JsonElement){
            MoveToAnotherComponent.moveToActivityWithIntentValue<CustomerMainActivity>(this,Constant.INTENT_PAYMENT_SUCSESS,1)
            return
        }
        val obj = data as OrderDetailsResModel
        super.onSuccessApiResult(obj)
        customerMakePaymentReqModel.status = "successful"

        btn_apply_promo.isEnabled = true
    }

    private fun setApplyPromoCodeAPIListener(){

        btn_apply_promo.setOnClickListener {

            val cupon = ed_apply_copon.text.toString()

            if (cupon.isEmpty()){
                showToastString(resources.getString(R.string.VALID_PROMO_CODE))
                return@setOnClickListener
            }
            callAPI()?.let {
                it.observeApiResult(
                    it.callAPIActivity<ApplyPromoCodeViewModel>(this)
                        .applyPromo(ApplyPromoCodeReqModel(customerMakePaymentReqModel.order_id,cupon))
                    , this, this
                )

            }


        }

    }

    /*private fun openPaymentGetWayPage(data: PaymentGetwayCheckoutIDResModel) {
        //MoveToAnotherComponent.moveToActivityNormal<PaymentCheckout>(this)
//        <user_id>/<checkout_id>/<order_id>

        button_submit.visibility = View.GONE
        PaymentCheckout(this,webview_payment_getway,Env.BASE_URL.plus(PathURL.PAYMENT_GETWAY_URL)
            .plus(data.user_id).plus("/").plus(data.checkout_id).plus("/").plus(data.order_id).plus("/").plus(data.trnx_id)
            ,data.order_id)
    }*/



}