package com.rental.customer.login.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rental.R
import com.rental.customer.login.viewmodel.ForgotViewModel
import com.rental.customer.utils.Validator
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity :AppCompatActivity() {

    private lateinit var forgotViewModel: ForgotViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password);

        initialize()
        forgotViewModel=ViewModelProviders.of(this).get(ForgotViewModel::class.java)
        forgotViewModel.getForgotPasswordResponse()?.observe(this, Observer {


        })
    }

    private fun initialize(){
        btn_send.setOnClickListener { checkValidation(ed_email.text.toString()) }
    }
    private fun checkValidation(email: String): Boolean {
        if (email.isEmpty()) {
//            forgotPasswordInterface.showToast("Please Enter Email/Phone Number")
        } else if (!Validator.isEmailValid(email)) {
//            forgotPasswordInterface.showToast("Please Enter Valid Email/Phone Number")
        }else{
            forgotViewModel.forgotPasswordAPI(ed_email.text.toString())
        }
        return false
    }

}