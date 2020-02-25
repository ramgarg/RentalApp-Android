package com.rental.customer.login.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rental.R
import com.rental.customer.login.viewmodel.ForgotViewModel
import com.rental.customer.utils.MoveToActivity
import com.rental.customer.utils.Validator
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity :AppCompatActivity() {

    private lateinit var forgotViewModel: ForgotViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password);

        initialize()
//        forgotViewModel=ViewModelProviders.of(this).get(ForgotViewModel::class.java)
//        forgotViewModel.getForgotPasswordResponse()?.observe(this, Observer {


//        })
    }

    private fun initialize(){
        btn_send.setOnClickListener { checkValidation(ed_email.text.toString())
        img_back.setOnClickListener { finish() }}

    }
    private fun checkValidation(email: String): Boolean {
        if (email.isEmpty()) {
            Toast.makeText(this,"Please Enter Email/Phone Number", Toast.LENGTH_SHORT).show()
//            forgotPasswordInterface.showToast("Please Enter Email/Phone Number")
        } else if (!Validator.isEmailValid(email)) {
            Toast.makeText(this,"Please Enter Valid Email/Phone Number",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Email have been sent to your entered Email/PhoneNumber",Toast.LENGTH_SHORT).show()
//            forgotViewModel.forgotPasswordAPI(ed_email.text.toString())
//            MoveToActivity.moveToHomeActivity(this)
        }
        return false
    }

}