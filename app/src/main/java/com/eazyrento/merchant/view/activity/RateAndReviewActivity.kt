package com.eazyrento.merchant.view.activity

import android.app.Activity
import android.os.Bundle
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.merchant.model.modelclass.FeedbackReqModel
import com.eazyrento.merchant.viewModel.MerchantFeedbackViewModel
import kotlinx.android.synthetic.main.activity_rate_and_review.*

class RateAndReviewActivity :BaseActivity() {

    lateinit var feedbackModelItem:FeedbackReqModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_and_review)
        topBarWithBackIconAndTitle(resources.getString(R.string.feedback))
         initView()

        feedbackModelItem = intent.getParcelableExtra<FeedbackReqModel>(Constant.INTENT_RATE_REVIEWS)
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
        feedbackModelItem.review=ed_rating.text.toString()
        feedbackModelItem.rating=rating_bar.rating

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<MerchantFeedbackViewModel>(this)
                    .merchantFeedback(feedbackModelItem)
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