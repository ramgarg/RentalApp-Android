package com.rental.customer.forgotpassword.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rental.R
import com.rental.customer.forgotpassword.presenter.ForgotPasswordActivityPresenter
import com.rental.customer.presenter.ForgotPasswordInterface

class ForgotPassword :AppCompatActivity(), ForgotPasswordInterface {

    private lateinit var editTextEmail:EditText
    private lateinit var buttonSend:Button
    private lateinit var forgotPasswordActivityPresenter: ForgotPasswordActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password);

        initialize()
        forgotPasswordActivityPresenter= ForgotPasswordActivityPresenter(this)
    }

    private fun initialize(){
        editTextEmail=findViewById(R.id.ed_email)
        buttonSend=findViewById(R.id.btn_send)
        buttonSend.setOnClickListener { forgotPassword() }
    }

    override fun forgotPassword() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        forgotPasswordActivityPresenter.forgotPasswordAPI(editTextEmail.text.toString())
    }

    override fun showToast(message: String) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}