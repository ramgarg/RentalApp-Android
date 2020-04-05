package com.rental.login.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rental.R
import com.rental.appbiz.AppBizLogger
import com.rental.appbiz.AppBizLogin
import com.rental.common.view.BaseActivity
import com.rental.customer.utils.Common
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.login.model.modelclass.RegisterUserReqModel
import com.rental.login.viewmodel.RegisterUserViewModel
import kotlinx.android.synthetic.main.activity_register_user.*
import kotlinx.android.synthetic.main.activity_register_user.btn_agent_active
import kotlinx.android.synthetic.main.activity_register_user.btn_agent_inactive
import kotlinx.android.synthetic.main.activity_register_user.btn_customer_active
import kotlinx.android.synthetic.main.activity_register_user.btn_customer_inactive
import kotlinx.android.synthetic.main.activity_register_user.btn_merchant_active
import kotlinx.android.synthetic.main.activity_register_user.btn_merchant_inactive

class RegistrationUserActivity : BaseActivity(),AppBizLogin{
    //RegistrationView {

//    private lateinit var registrationActivityPresenter: RegistrationActivityPresenter
    private lateinit var viewModel:RegisterUserViewModel
    private lateinit var user_rule:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        initialize()

    // Register view model
     viewModel = ViewModelProviders.of(this).get(RegisterUserViewModel::class.java)

    }

    private fun initialize() {

        btn_merchant_inactive.setOnClickListener { registerAs("Merchant") }
        btn_customer_inactive.setOnClickListener { registerAs("Customer")  }
        btn_agent_inactive.setOnClickListener { registerAs("Agent") }
        btn_register.setOnClickListener { register() }
        skipLogin()
    }

    private fun skipLogin(){
        tv_skip.setOnClickListener { MoveToAnotherComponent.moveToHomeActivity(this) }
    }

     fun registerAs(registartionType: String) {

        /*registrationActivityPresenter.registrationAs(registartionType,btn_agent_inactive,btn_customer_inactive,btn_merchant_inactive,
            btn_merchant_active,btn_customer_active,btn_agent_active)*/

        if(registartionType.equals("Merchant")){
            user_rule ="Merchant"
            Common.showGroupViews(btn_merchant_active,btn_customer_inactive,btn_agent_inactive)
            Common.hideGroupViews(btn_agent_active,btn_customer_active,btn_merchant_inactive)

        }else if(registartionType.equals("Agent")){
            user_rule = "Agent"
            Common.showGroupViews(btn_agent_active,btn_customer_inactive,btn_merchant_inactive)
            Common.hideGroupViews(btn_merchant_active,btn_customer_active,btn_agent_inactive)
        }else if(registartionType.equals("Customer")){
            user_rule = "Customer"
            Common.showGroupViews(btn_customer_active,btn_merchant_inactive,btn_agent_inactive)
            Common.hideGroupViews(btn_agent_active,btn_merchant_active,btn_customer_inactive)
        }
    }



    fun register() {
        if(checkValidation(ed_full_name,ed_email_phone,ed_password,checkbox_terms)){
            showProgress()
           val registerUserReqModel = RegisterUserReqModel("",ed_full_name.text.toString(),ed_password.text.toString()
                ,"normal",user_rule,ed_email_phone.text.toString())
            viewModel.registerUser(registerUserReqModel).observe(this, Observer {
                hideProgress()
                if(it.data==null)
                {
                    // error In API
                    errorHandle(it)
                }
                else{
                    AppBizLogger.log(AppBizLogger.LoggingType.INFO,it.data.toString())
                }
            })
        }

    }

     fun moveToOtp() {
        MoveToAnotherComponent.moveToOTPActivity(this)
    }

    override fun showToast(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

}