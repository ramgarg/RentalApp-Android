package com.rental.customer.login.view

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rental.R
import com.rental.customer.login.viewmodel.OTPViewModel
import com.rental.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.activity_otp.*

class OTPActivity :AppCompatActivity(){

    lateinit var otpViewModel: OTPViewModel

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
        }else
//            otpViewModel.otpAPI(ed_email.text.toString())

            MoveToAnotherComponent.moveToHomeActivity(this)
        return false
    }



}