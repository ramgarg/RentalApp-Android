package com.rental.customer.login.view

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rental.R
import com.rental.customer.login.viewmodel.LoginViewModel
import com.rental.customer.utils.MoveToActivity
import com.rental.customer.utils.Validator
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity :AppCompatActivity() {

    lateinit var loginViewModel:LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initialize()

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginViewModel.getLogin()?.observe(this , Observer {

        })
    }

    private fun initialize(){
        tv_registration.setOnClickListener { MoveToActivity.moveToRegistrationActivity(this) }
        tv_forgot_password.setOnClickListener { MoveToActivity.moveToForgotPasswordActivity(this) }
        btn_login.setOnClickListener { checkValidation(ed_email,ed_password) }
    }



    private fun checkValidation(email: EditText, password: EditText): Boolean {
        if (email.text.toString().isEmpty() && password.text.toString().isEmpty()) {
//            showToast("Please Enter Email/Phone Number and Password")
        } else if (!Validator.isEmailValid(email.text.toString())) {
//            showToast("Please Enter Valid Email")
            email.requestFocus()
        } else if (password.text.toString().isEmpty()) {
            password.requestFocus()
//            showToast("Please Enter Valid Password")
        } else if (!Validator.isPasswordValid(password.text.toString())) {
//            showToast("Invalid Password! minimum length 8")
        }else{
            loginViewModel.onClick(email,password)
        }
        return false
    }




}