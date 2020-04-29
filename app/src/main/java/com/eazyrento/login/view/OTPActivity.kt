package com.eazyrento.login.view

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eazyrento.R
import com.eazyrento.Session
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.appbiz.AppBizLogin
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.forgotpassword.model.modelClass.OTPRequest
import com.eazyrento.login.viewmodel.OTPViewModel
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.login.viewmodel.LoginOTPViewModel
import kotlinx.android.synthetic.main.activity_otp.*

class OTPActivity :BaseActivity(){

    var otpRequest = OTPRequest()

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp);

        initialize()
//        otpViewModel=ViewModelProviders.of(this).get(OTPViewModel::class.java)
//        otpViewModel.getOTPResponse()?.observe(this, Observer {
//
//        })



    }

    private fun initialize(){
        btn_continue.setOnClickListener { checkValidation(ed_email) }
    }

    private fun checkValidation(editTextOTP: EditText):Boolean{

        if(editTextOTP.text.toString().isEmpty()){
//            otpView.showToast("Please Enter Valid OTP")
            Toast.makeText(this,"Please Enter Valid OTP",Toast.LENGTH_SHORT).show()
        }else{
            otpRequest.user_id = Session.getInstance(this)?.getUserID()
            otpRequest.passcode = editTextOTP.text.toString().toInt()

            callAPI()?.let {
                it.observeApiResult(
                    it.callAPIActivity<LoginOTPViewModel>(this)
                        .OTPAPI(otpRequest)
                    , this, this
                )
            }
        }
//            otpViewModel.otpAPI(ed_email.text.toString())

//            MoveToAnotherComponent.moveToHomeActivity(this)
        return false
    }

    override fun <T> onSuccessApiResult(data: T) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())
        MoveToAnotherComponent.moveToHomeActivity(this)
    }



}