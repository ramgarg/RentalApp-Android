package com.eazyrento.agent.view.activity

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderDetailsResModel
import com.eazyrento.customer.payment.model.modelclass.AgentMakePaymentReqModel
import com.eazyrento.customer.payment.model.modelclass.BaseMakePaymentModel
import com.eazyrento.customer.payment.view.PaymentBaseActivity
import com.eazyrento.customer.payment.viewmodel.MakePaymentViewModel
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

//        setContentView(R.layout.activity_payment)

        topBarWithBackIconAndTitle(getString(R.string.requestpayment))

        payment_method_choose.visibility = View.GONE

        tv_choose_mtd.visibility = View.GONE

    }

    override fun onClickDailog(int: Int) {
        when(int){

            Constant.OK ->submitPayment()

        }
    }



    override fun requestPaymentObjectBuilder():BaseMakePaymentModel {
        agentMakePaymentReqModel.requested_amount = ed_enter_amount.text.toString().toDouble()
        return agentMakePaymentReqModel
    }


      override fun checkValidation(): Boolean {
              return  super.checkValidation()
    }



    override fun <T> onSuccessApiResult(data: T) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        if (data is JsonElement){
            MoveToAnotherComponent.moveToActivityWithIntentValue<AgentMainActivity>(this,Constant.INTENT_PAYMENT_SUCSESS,1)
            return
        }
        val obj = data as CustomerOrderDetailsResModel

        super.setDataOnUI(obj)
    }

}