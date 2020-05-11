package com.eazyrento.agent.view.activity

import android.app.Activity
import android.os.Bundle
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.agent.model.modelclass.AgentFeedbackReqModel
import com.eazyrento.agent.viewmodel.AgentFeedbackViewModel
import com.eazyrento.common.view.BaseActivity
import kotlinx.android.synthetic.main.activity_rate_and_review.*

class AgentFeedbackActivity: BaseActivity() {

    lateinit var agentFeedbackReqModel: AgentFeedbackReqModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_and_review)
        topBarWithBackIconAndTitle(resources.getString(R.string.feedback))
        initView()

        agentFeedbackReqModel = intent.getParcelableExtra<AgentFeedbackReqModel>(Constant.INTENT_RATE_REVIEWS)
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
        agentFeedbackReqModel.review=ed_rating.text.toString()
        agentFeedbackReqModel.rating=rating_bar.rating

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<AgentFeedbackViewModel>(this)
                    .agentFeedback(agentFeedbackReqModel)
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}