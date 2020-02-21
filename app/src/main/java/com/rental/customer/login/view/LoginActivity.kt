package com.rental.customer.login.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rental.R
import com.rental.customer.login.presenter.LoginActivityPresenter
import com.rental.customer.presenter.LoginInterface
import com.rental.customer.utils.MoveToActivity

class LoginActivity :AppCompatActivity(), LoginInterface {

    private lateinit var loginActivityPresenter: LoginActivityPresenter
    private lateinit var editTextEmail:EditText
    private lateinit var editTextPassword:EditText
    private lateinit var buttonLogin:Button
    private lateinit var texViewForgotPassword: TextView
    private lateinit var textViewRegistration: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initialize()
        loginActivityPresenter= LoginActivityPresenter(this)
    }

    private fun initialize(){
        editTextEmail=findViewById(R.id.ed_email)
        editTextPassword=findViewById(R.id.ed_password)
        texViewForgotPassword=findViewById(R.id.tv_forgot_password)
        textViewRegistration=findViewById(R.id.tv_registration)
        textViewRegistration.setOnClickListener { registration() }
        texViewForgotPassword.setOnClickListener { forgotPassword() }
        buttonLogin=findViewById(R.id.btn_login)
        buttonLogin.setOnClickListener { doLogin() }

    }

    override fun doLogin() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        loginActivityPresenter.login(editTextEmail,editTextPassword)
    }

    override fun showToast(message: String) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun forgotPassword() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        MoveToActivity.moveToForgotPasswordActivity(this)
    }

    override fun registration() {
//        TODO("not implemented") //To change body of created functions use File | Settings |
        MoveToActivity.moveToRegistrationActivity(this)
    }

}