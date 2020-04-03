package com.rental.login.view

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rental.R
import com.rental.login.viewmodel.LoginViewModel
import com.rental.customer.utils.Common
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.Validator
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity :AppCompatActivity() {

    lateinit var loginViewModel:LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initialize()
//
//        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
//        loginViewModel.getLogin()?.observe(this , Observer {
//
//        })
    }

    private fun initialize(){
        tv_registration.setOnClickListener { MoveToAnotherComponent.moveToRegistrationActivity(this) }
        tv_forgot_password.setOnClickListener { MoveToAnotherComponent.moveToForgotPasswordActivity(this) }
        btn_login.setOnClickListener { checkValidation(ed_email,ed_password) }
        tv_skip.setOnClickListener {

            Common.showDialog("UserType","",this,R.layout.rental_dialog)
//            MoveToAnotherComponent.moveToHomeActivity(this)
        }

    }



    private fun checkValidation(email: EditText, password: EditText): Boolean {
        if (email.text.toString().isEmpty() && password.text.toString().isEmpty()) {
//            showToast("Please Enter Email/Phone Number and Password")
            Toast.makeText(this,"Please Enter Email/Phone Number", Toast.LENGTH_SHORT).show()
        } else if (!Validator.isEmailValid(email.text.toString())) {
//            showToast("Please Enter Valid Email")
            Toast.makeText(this,"Please Enter Valid Email", Toast.LENGTH_SHORT).show()
            email.requestFocus()
        } else if (password.text.toString().isEmpty()) {
            password.requestFocus()
//            showToast("Please Enter Valid Password")
            Toast.makeText(this,"Please Enter Vaild Password", Toast.LENGTH_SHORT).show()
        } else if (!Validator.isPasswordValid(password.text.toString())) {
//            showToast("Invalid Password! minimum length 8")
        }else{
//            loginViewModel.onClick(email,password)
            MoveToAnotherComponent.moveToHomeActivity(this)
        }
        return false
    }




}