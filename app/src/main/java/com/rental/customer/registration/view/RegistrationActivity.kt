package com.rental.customer.registration.view

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.rental.R
import com.rental.customer.registration.presenter.RegistrationActivityPresenter
import com.rental.customer.registration.presenter.RegistrationView
import com.rental.customer.utils.MoveToActivity

class RegistrationActivity : AppCompatActivity(), RegistrationView {

    private lateinit var registrationActivityPresenter: RegistrationActivityPresenter
    private lateinit var buttonInActiveCustomer: TextView
    private lateinit var buttonInActiveAgent: TextView
    private lateinit var buttonInActiveMerchant: TextView
    private lateinit var buttonActiveMerchant: RelativeLayout
    private lateinit var buttonActiveAgent: RelativeLayout
    private lateinit var buttonActiveCustomer: RelativeLayout
    private lateinit var editTextUserName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonRegister: Button
    private lateinit var checkBoxTerms: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initialize();
        registrationActivityPresenter = RegistrationActivityPresenter(this)
    }

    private fun initialize() {
        buttonActiveAgent = findViewById(R.id.btn_agent_active)
        buttonActiveCustomer = findViewById(R.id.btn_customer_active)
        buttonActiveMerchant = findViewById(R.id.btn_merchant_active)
        buttonInActiveAgent = findViewById(R.id.btn_agent_inactive)
        buttonInActiveCustomer = findViewById(R.id.btn_customer_inactive)
        buttonInActiveMerchant = findViewById(R.id.btn_merchant_inactive)
        buttonRegister = findViewById(R.id.btn_register)
        editTextEmail = findViewById(R.id.ed_email_phone)
        editTextUserName = findViewById(R.id.ed_full_name)
        editTextPassword = findViewById(R.id.ed_password)
        checkBoxTerms=findViewById(R.id.checkbox_terms)
        buttonInActiveMerchant.setOnClickListener { registerAs("Merchant") }
        buttonInActiveCustomer.setOnClickListener { registerAs("Customer")  }
        buttonInActiveAgent.setOnClickListener { registerAs("Agent") }
        buttonRegister.setOnClickListener { register() }
    }

    override fun registerAs(registartionType: String) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        registrationActivityPresenter.registrationAs(registartionType,buttonInActiveAgent,buttonInActiveCustomer,buttonInActiveMerchant,
            buttonActiveMerchant,buttonActiveCustomer,buttonActiveAgent)
    }

    override fun showToast(message: String) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun register() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        registrationActivityPresenter.registerAPI(editTextUserName,editTextEmail,editTextPassword,checkBoxTerms)
    }

    override fun moveToOtp() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        MoveToActivity.moveToOTPActivity(this)
    }
}