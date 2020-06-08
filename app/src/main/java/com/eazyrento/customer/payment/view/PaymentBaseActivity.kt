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
import com.eazyrento.customer.payment.viewmodel.MakePaymentViewModel
import kotlinx.android.synthetic.main.activity_payment.*

open abstract class PaymentBaseActivity : BaseActivity() {

    protected var totalPrice:Double = 0.0
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

        /*ed_enter_amount.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.toString().contains(Constant.DOLLAR) && count==0){
                    ed_enter_amount.setText(s.toString().plus(Constant.DOLLAR))
                    ed_enter_amount.setSelection(1)
                }
            }

        })*/

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
        else if (totalPrice.compareTo(removeDollarSign(ed_enter_amount.text.toString()).toDouble())<0){
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

        setDataOnUI(data as OrderDetailsResModel)
    }

    protected fun setDataOnUI(customerOrderDetailsResModel: OrderDetailsResModel) {

         customerOrderDetailsResModel.order_id.let {
             tv_order_id.text =Constant.ORDER_ID+it
             baseMakePaymentModel.order_id =it
         }

        tv_pending_amount.text = Constant.DOLLAR+customerOrderDetailsResModel.pending_order_amount
        tv_total_price.text =Constant.DOLLAR+customerOrderDetailsResModel.total_order_amount
     //   tv_pending_approval.text=Constant.DOLLAR+customerOrderDetailsResModel


        totalPrice = customerOrderDetailsResModel.total_order_amount
    }

    protected fun convertAmountIntoDoubleFromEditText():Double{
        return removeDollarSign(ed_enter_amount.text.toString()).toDouble()
    }
    fun removeDollarSign(amount:String):String{
        return amount
        //return amount.removePrefix(Constant.DOLLAR)
    }




}