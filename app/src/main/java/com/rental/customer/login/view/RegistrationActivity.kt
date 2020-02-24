package com.rental.customer.login.view

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.rental.R
import com.rental.customer.login.presenter.RegistrationActivityPresenter
import com.rental.customer.login.presenter.RegistrationView
import com.rental.customer.utils.MoveToActivity
import kotlinx.android.synthetic.main.activity_sign_up.*

class RegistrationActivity : AppCompatActivity(),
    RegistrationView {

    private lateinit var registrationActivityPresenter: RegistrationActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initialize();
        registrationActivityPresenter =
            RegistrationActivityPresenter(this)
    }

    private fun initialize() {

        btn_merchant_inactive.setOnClickListener { registerAs("Merchant") }
        btn_customer_inactive.setOnClickListener { registerAs("Customer")  }
        btn_agent_inactive.setOnClickListener { registerAs("Agent") }
        btn_register.setOnClickListener { register() }
    }

    override fun registerAs(registartionType: String) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        registrationActivityPresenter.registrationAs(registartionType,btn_agent_inactive,btn_customer_inactive,btn_merchant_inactive,
            btn_merchant_active,btn_customer_active,btn_agent_active)
    }

    override fun showToast(message: String) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun register() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        registrationActivityPresenter.registerAPI(ed_full_name,ed_email_phone,ed_password,checkbox_terms)
    }

    override fun moveToOtp() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        MoveToActivity.moveToOTPActivity(this)
    }
}