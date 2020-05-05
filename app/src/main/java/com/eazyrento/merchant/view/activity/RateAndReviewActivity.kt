package com.eazyrento.merchant.view.activity

import android.os.Bundle
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.merchant.model.modelclass.MerchantFeedbackReqModel
import com.eazyrento.merchant.viewModel.MerchantFeedbackViewModel
import kotlinx.android.synthetic.main.activity_rate_and_review.*

class RateAndReviewActivity :BaseActivity() {

   val merchantFeedbackModelItem= MerchantFeedbackReqModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_and_review)
        topBarWithBackIconAndTitle(resources.getString(R.string.feedback))
         initView()
    }

    private fun initView() {
        btn_submit.setOnClickListener { moveTOSave() }
    }

    private fun moveTOSave() {
        if (checkValidation())
            return
        setRatingListItem()
    }

    private fun setRatingListItem() {
        merchantFeedbackModelItem.review=ed_rating.text.toString()
        merchantFeedbackModelItem.agent_id=5
        merchantFeedbackModelItem.customer_id=10
        merchantFeedbackModelItem.order_id="1"
        merchantFeedbackModelItem.rating=1.0

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<MerchantFeedbackViewModel>(this)
                    .merchantFeedback(merchantFeedbackModelItem)
                , this, this
            )
        }
         }

    override fun <T> onSuccessApiResult(data: T) {
        data?.let {
            showToast(ValidationMessage.FEEDBACK_SENT)

            //MoveToAnotherComponent.moveToListAddressActivity(this)
        }
    }

    private fun checkValidation(): Boolean {
        when {
            ed_rating.text.toString().isEmpty()->showToast(ValidationMessage.VALID_RATING)

            else-> {
                return false
            }
        }
        return true


    }

    override fun <T> moveOnSelecetedItem(type: T) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}