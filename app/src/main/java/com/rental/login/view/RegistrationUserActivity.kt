package com.rental.login.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.rental.R
import com.rental.Session
import com.rental.appbiz.AppBizLogin
import com.rental.appbiz.retrofitapi.ApiObserver
import com.rental.appbiz.retrofitapi.ChangedListener
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.common.view.BaseActivity
import com.rental.common.view.UserInfoAPP
import com.rental.common.view.UserInfoAPP.Companion.user_role
import com.rental.customer.utils.Common
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.login.model.modelclass.RegisterUserReqModel
import com.rental.login.model.modelclass.RegisterUserResModel
import com.rental.login.viewmodel.RegisterUserViewModel
import kotlinx.android.synthetic.main.activity_register_user.*
import kotlinx.android.synthetic.main.activity_register_user.btn_agent_active
import kotlinx.android.synthetic.main.activity_register_user.btn_agent_inactive
import kotlinx.android.synthetic.main.activity_register_user.btn_customer_active
import kotlinx.android.synthetic.main.activity_register_user.btn_customer_inactive
import kotlinx.android.synthetic.main.activity_register_user.btn_merchant_active
import kotlinx.android.synthetic.main.activity_register_user.btn_merchant_inactive

class RegistrationUserActivity : BaseActivity(),AppBizLogin{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        initialize()

    // Register view model
//     viewModel = ViewModelProviders.of(this).get(RegisterUserViewModel::class.java)

    }

    private fun initialize() {

        btn_merchant_inactive.setOnClickListener { assignRole(UserInfoAPP.MERCHANT) }
        btn_customer_inactive.setOnClickListener { assignRole(UserInfoAPP.CUSTOMER)  }
        btn_agent_inactive.setOnClickListener { assignRole(UserInfoAPP.AGENT) }

        btn_register.setOnClickListener {
            register()
        }

        skipLogin()
    }

    private fun skipLogin(){
        tv_skip.setOnClickListener { MoveToAnotherComponent.moveToHomeActivity(this) }
    }

     fun assignRole(registartionType: String) {

        when(registartionType) {
            UserInfoAPP.MERCHANT -> {

                user_role = UserInfoAPP.MERCHANT

                Common.showGroupViews(btn_merchant_active,btn_customer_inactive,btn_agent_inactive)
                Common.hideGroupViews(btn_agent_active,btn_customer_active,btn_merchant_inactive)
            }
            UserInfoAPP.CUSTOMER->{

                user_role = UserInfoAPP.CUSTOMER

                Common.showGroupViews(btn_customer_active,btn_merchant_inactive,btn_agent_inactive)
                Common.hideGroupViews(btn_agent_active,btn_merchant_active,btn_customer_inactive)
            }
            UserInfoAPP.AGENT->{

                user_role = UserInfoAPP.AGENT

                Common.showGroupViews(btn_agent_active,btn_customer_inactive,btn_merchant_inactive)
                Common.hideGroupViews(btn_merchant_active,btn_customer_active,btn_agent_inactive)
            }
        }
    }



    fun register() {

        if(checkValidation(ed_full_name,ed_email_phone,ed_password,checkbox_terms,user_role)){

            showProgress()

            UserInfoAPP.REGISTRATIONS_SOURCE = UserInfoAPP.BY_NORMAL
           val registerUserReqModel = createRegisterUserReqModel()

            val viewModel = ViewModelProviders.of(this).get(RegisterUserViewModel::class.java)

            viewModel.registerUser(registerUserReqModel).observe(this,
            ApiObserver<RegisterUserResModel>(object :ChangedListener<RegisterUserResModel>{

                override fun onSuccess(dataWrapper: RegisterUserResModel) {
                    hideProgress()
                    Session.getInstance(this@RegistrationUserActivity)?.saveUserRole(user_role)
                    Session.getInstance(this@RegistrationUserActivity)?.saveUserID(dataWrapper.user_id)

                    moveToOtp()
                }

                override fun onError(dataWrapper: DataWrapper<RegisterUserResModel>) {
                    hideProgress()
                    errorHandle(dataWrapper.error,dataWrapper.apiException)
                }

            }))
        }

    }

    private fun createRegisterUserReqModel(): RegisterUserReqModel {
        return RegisterUserReqModel("",ed_full_name.text.toString(),ed_password.text.toString()
            ,UserInfoAPP.REGISTRATIONS_SOURCE!!,user_role!!,ed_email_phone.text.toString())
    }

    fun moveToOtp() {
        MoveToAnotherComponent.moveToOTPActivity(this)
    }

    override fun showToast(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

}