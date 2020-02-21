package com.rental.customer.otp.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rental.R
import com.rental.customer.otp.presenter.OTPActivityPresenter
import com.rental.customer.otp.presenter.OTPView

class OTPActivity :AppCompatActivity(),OTPView{

    private lateinit var textViewOTPMessage:TextView
    private lateinit var otpActivityPresenter:OTPActivityPresenter
    private lateinit var editTextOTP: EditText
    private lateinit var buttonContinue: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp);

        initialize()
        otpActivityPresenter= OTPActivityPresenter(this)
        otpActivityPresenter.setMessage(getString(R.string.opt_detail),textViewOTPMessage)
    }

    private fun initialize(){
        textViewOTPMessage=findViewById(R.id.otp_message)
        editTextOTP=findViewById(R.id.ed_email)
        buttonContinue=findViewById(R.id.btn_continue)
        buttonContinue.setOnClickListener { optVerify() }
    }

    override fun optVerify() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        otpActivityPresenter.verifyOTP(editTextOTP)
    }

    override fun showToast(message: String) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }


}