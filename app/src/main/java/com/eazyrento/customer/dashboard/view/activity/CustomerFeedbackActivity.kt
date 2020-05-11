package com.eazyrento.customer.dashboard.view.activity

import android.app.Activity
import android.os.Bundle
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.dashboard.model.modelclass.CustomerFeedbackRequestModel
import com.eazyrento.customer.dashboard.viewmodel.CustomerFeedbackViewModel
import kotlinx.android.synthetic.main.activity_rate_and_review.*

class CustomerFeedbackActivity:BaseActivity() {
    lateinit var customerFeedbackReqModel: CustomerFeedbackRequestModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_and_review)
        topBarWithBackIconAndTitle(resources.getString(R.string.feedback))
        initView()

        customerFeedbackReqModel = intent.getParcelableExtra<CustomerFeedbackRequestModel>(Constant.INTENT_RATE_REVIEWS)
    }

    private fun initView() {
        btn_submit.setOnClickListener { moveTOSave() }
    }

    private fun moveTOSave() {

        if (checkValidation())
            return

        sendFeedback()
    }

    private fun sendFeedback() {
        customerFeedbackReqModel.review=ed_rating.text.toString()
        customerFeedbackReqModel.rating=rating_bar.rating

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<CustomerFeedbackViewModel>(this)
                    .customerFeedback(customerFeedbackReqModel)
                , this, this
            )
        }
    }

    override fun <T> onSuccessApiResult(data: T) {
        data?.let {
            showToast(ValidationMessage.FEEDBACK_SENT)
            finishCurrentActivity(Activity.RESULT_OK)
        }
    }

    private fun checkValidation(): Boolean {

        if (ed_rating.text.toString().isEmpty() || rating_bar.rating==0.0f){
            showToast(ValidationMessage.VALID_RATING)
            return true
        }
        return false
    }

    override fun <T> moveOnSelecetedItem(type: T) {
    }
}