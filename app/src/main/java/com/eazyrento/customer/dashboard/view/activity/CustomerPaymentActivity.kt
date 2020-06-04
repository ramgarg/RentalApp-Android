package com.eazyrento.customer.dashboard.view.activity

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderDetailsResModel
import com.eazyrento.customer.payment.model.modelclass.BaseMakePaymentModel
import com.eazyrento.customer.payment.model.modelclass.CustomerMakePaymentReqModel
import com.eazyrento.customer.payment.view.PaymentBaseActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_payment.*

class CustomerPaymentActivity : PaymentBaseActivity() {

    private var modeOfPayment:String = ""

    override fun getReqPaymentModel(): BaseMakePaymentModel {
        return customerMakePaymentReqModel
    }

    private val customerMakePaymentReqModel = CustomerMakePaymentReqModel("",0.0,false,modeOfPayment,"","")

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_payment)

        topBarWithBackIconAndTitle(getString(R.string.payment))


//        callAPIOrderList(intent.getIntExtra(Constant.KEY_ORDER_DETAILS_ID,-1))

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

            Constant.OK ->submitPayment()

        }
    }

    /*private fun submitPayment(){

        requestPaymentObjectBuilder()

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<MakePaymentViewModel>(this)
                    .makePayment(intent.getIntExtra(Constant.KEY_ORDER_DETAILS_ID,-1),customerMakePaymentReqModel)
                , this, this
            )
        }
    }*/

    override fun requestPaymentObjectBuilder():BaseMakePaymentModel {

        customerMakePaymentReqModel.amount_paid = convertAmountIntoDoubleFromEditText()
        customerMakePaymentReqModel.mode_of_payment = modeOfPayment
        customerMakePaymentReqModel.is_tip =false
        customerMakePaymentReqModel.transaction_id=""

        return customerMakePaymentReqModel

    }


  /*  fun onSubmitClick(view: View){
        if (checkValidation()){
            
            showDialog(getString(R.string.payment),getString(R.string.thank_you),this,R.layout.thank_you_pop)
        }
    }*/

      override fun checkValidation(): Boolean {

          if (modeOfPayment.isEmpty()){
              showToast(ValidationMessage.PAYMENT_MODE)
              return false
          }

          else
              return  super.checkValidation()

    }



    override fun <T> onSuccessApiResult(data: T) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        if (data is JsonElement){
            MoveToAnotherComponent.moveToActivityWithIntentValue<CustomerMainActivity>(this,Constant.INTENT_PAYMENT_SUCSESS,1)
            return
        }
        val obj = data as CustomerOrderDetailsResModel
        super.setDataOnUI(obj)
        customerMakePaymentReqModel.status = obj.order_status
    }

    /*private fun setDataOnUI(customerOrderDetailsResModel: CustomerOrderDetailsResModel) {

         customerOrderDetailsResModel.order_id.let {
             tv_order_id.text =it
             customerMakePaymentReqModel.order_id =it
         }
        tv_pending_amount.text = resources.getString(R.string.pending_amount)+customerOrderDetailsResModel.pending_order_amount
        tv_total_price.text =resources.getString(R.string.booking_price)+customerOrderDetailsResModel.total_order_amount

        customerMakePaymentReqModel.status = customerOrderDetailsResModel.order_status
        totalPrice = customerOrderDetailsResModel.total_order_amount
    }*/

}