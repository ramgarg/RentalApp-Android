package com.eazyrento.login.view

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.forgotpassword.model.modelClass.OTPRequest
import com.eazyrento.customer.forgotpassword.model.modelClass.OTPResponse
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.login.model.modelclass.ResendOTPRequest
import com.eazyrento.login.viewmodel.LoginOTPViewModel
import com.eazyrento.login.viewmodel.ResendOTPViewModel
import kotlinx.android.synthetic.main.activity_otp.*
import java.lang.NumberFormatException

class OTPActivity :BaseActivity(){

    private var isResendOtpClcik:Boolean = false
    private val mHandlerPostDelayed = HandlerPostDelayed()

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        topBarWithBackIconAndTitle(resources.getString(R.string.title_OTP))
        //val userEmail=intent.getStringExtra(Constant.INTENT_USER_EMAIL)
        //otp_message.setText(ValidationMessage.OTP_MESSAGE_START+" ("+"${userEmail}"+"). "+ValidationMessage.OTP_MESSAGE_END)
        visibleResendOTPButton()
    }
    private fun visibleResendOTPButton(){

        resend_otp_timer_text.visibility = View.VISIBLE
        btn_resend_otp.visibility = View.GONE

        mHandlerPostDelayed.TimerCustom(object: UpdateData {
            override fun onUpdate(millisUntilFinished: Long) {

                val convertToSecond = millisUntilFinished/1000

                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"convertToSecond: $convertToSecond")

                if (convertToSecond==0L)
                    resend_otp_timer_text.visibility = View.GONE
                else
                    resend_otp_timer_text.text = getString(R.string.otp_timer).plus(" $convertToSecond seconds")

            }

            override fun onFinish() {
                btn_resend_otp.visibility = View.VISIBLE
            }

        })
    }

   fun onResendOTPClick(view:View){
        val userID=intent.getIntExtra(Constant.INTENT_OTP_USER_ID,-1)
       isResendOtpClcik = true

       callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<ResendOTPViewModel>(this)
                    .resendOTPAPI(ResendOTPRequest(userID))
                , this, this
            )
        }

    }

    fun onContinueClick(view:View){


        val userID = intent.getIntExtra(Constant.INTENT_OTP_USER_ID,-1)
        var passcode:Int
        try {
             passcode =ed_otp.text.toString().toInt()
        }
        catch (e:NumberFormatException){
            showToast(R.string.OTP_VALID_NUMBER)
            return
        }



        if (checkValidationFailed(userID,passcode)){
            return
        }

        isResendOtpClcik = false

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<LoginOTPViewModel>(this)
                    .OTPAPI(OTPRequest(userID,passcode))
                , this, this
            )
        }
    }

    private fun checkValidationFailed(userID: Int, passcode: Int):Boolean{

        if(userID==-1 || passcode<=0){
            Toast.makeText(this,R.string.VALID_OTD,Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }

    override fun <T> onSuccessApiResult(data: T) {

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        if (isResendOtpClcik){
            isResendOtpClcik = false
            showToast(R.string.OPT_SUCCESSED)
            visibleResendOTPButton()
            return
        }


        if (data is OTPResponse){
//             {"status":200}
            showToast(R.string.REGISTRATION_SUCCESS)
            MoveToAnotherComponent.moveToActivityWithIntentValue<LoginActivity>(this,Constant.INTENT_OTP_TO_LOGIN,1)

        }

    }
}
class HandlerPostDelayed{
    private val mHandler =Handler()
    private val postDelayedResentButton:Long=10*1000
    private val interval:Long =1000
    private var mUpdateData: UpdateData?=null

    val runnableUpdate = Runnable {
        //mUpdateData?.onUpdate()
    }

    fun postDelayed(updateData: UpdateData){

          this.mUpdateData =  updateData
          mHandler.postDelayed(runnableUpdate,postDelayedResentButton)
    }
    fun removeHandlerCallBacks(runnable: Runnable?){
        runnable?.let { mHandler.removeCallbacks(it) }
    }

    fun TimerCustom(updateData: UpdateData){

        val timer = object: CountDownTimer(postDelayedResentButton, interval) {
            override fun onTick(millisUntilFinished: Long) {
                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"onTick $millisUntilFinished")
                updateData.onUpdate(millisUntilFinished)
            }

            override fun onFinish() {
                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"onFinish")
                updateData.onFinish()
            }
        }
        timer.start()
    }
}
interface UpdateData{
    fun onUpdate(millisUntilFinished: Long)
    fun onFinish()
}