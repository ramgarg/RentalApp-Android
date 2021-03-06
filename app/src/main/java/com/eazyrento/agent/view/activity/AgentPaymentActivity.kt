package com.eazyrento.agent.view.activity

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.model.modelclass.ApplyPromoCodeResModel
import com.eazyrento.customer.dashboard.model.modelclass.OrderDetailsResModel
import com.eazyrento.customer.payment.model.modelclass.AgentMakePaymentReqModel
import com.eazyrento.customer.payment.model.modelclass.BaseMakePaymentModel
import com.eazyrento.customer.payment.view.PaymentBaseActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_payment.*

class AgentPaymentActivity : PaymentBaseActivity() {


    override fun getReqPaymentModel(): BaseMakePaymentModel {
        return agentMakePaymentReqModel
    }

    private val agentMakePaymentReqModel = AgentMakePaymentReqModel("",0.0)

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        paymentMessage = resources.getString(R.string.agent_payment_message)

//        setContentView(R.layout.activity_payment)

        topBarWithBackIconAndTitle(getString(R.string.requestpayment))
        is_tip=0

        payment_method_choose.visibility = View.GONE
        lyt_approval.visibility=View.GONE
        lyt_customer_amount.visibility=View.GONE
        lyt_agent_amount.visibility=View.VISIBLE

        tv_choose_mtd.visibility = View.GONE

        // promo code apply
        lyt_promo.visibility = View.INVISIBLE

    }

    override fun onClickDailog(int: Int) {
        when(int){

            Constant.OK ->submitPayment()
            Constant.OK_THANKYOU_PAYMENT_MSG ->thankyouMessageConfirm<AgentOrderSummaryActivity>()

        }
    }

    override fun requestPaymentObjectBuilder():BaseMakePaymentModel {
        agentMakePaymentReqModel.requested_amount = ed_enter_amount_agent.text.toString().toDouble()/*convertAmountIntoDoubleFromEditText()*/
        return agentMakePaymentReqModel
    }


      override fun checkValidation(): Boolean {
          if (ed_enter_amount_agent.text.isEmpty()) {
              showToast(R.string.ENTER_AMOUNT_PAYMENT)
              return false
          }
          else if (amountToPay.compareTo(removeDollarSign(ed_enter_amount_agent.text.toString()).toDouble())<0){
              showToast(R.string.ENTER_AMOUNT_PAYMENT_LESS_THEN_TO)
              return false
          }
          return true
    }



    override fun <T> onSuccessApiResult(data: T) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        /*if (data is ApplyPromoCodeResModel){
            //
            //ed_apply_copon.setText("")
            ed_enter_amount_agent.setText("")

            amountDataRefrash(data.amountToPay,data.amountPendingForApproval)
            return
        }*/

        if (data is JsonElement){
//            MoveToAnotherComponent.moveToActivityWithIntentValue<AgentMainActivity>(this,Constant.INTENT_PAYMENT_SUCSESS,1)
//            MoveToAnotherComponent.moveToActivityNormal<AgentOrderSummaryActivity>(this)
             thankYouPopup()
            return
        }
        val obj = data as OrderDetailsResModel

        super.setDataOnUI(obj)
    }

}