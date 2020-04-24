package com.eazyrento.login.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.eazyrento.R
import com.eazyrento.Session
import com.eazyrento.appbiz.AppBizLogin
import com.eazyrento.appbiz.retrofitapi.ApiObserver
import com.eazyrento.appbiz.retrofitapi.ChangedListener
import com.eazyrento.common.view.UserInfoAPP
import com.eazyrento.common.view.UserInfoAPP.Companion.user_role
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.login.model.modelclass.RegisterUserReqModel
import com.eazyrento.login.model.modelclass.RegisterUserResModel
import com.eazyrento.login.viewmodel.RegisterUserViewModel
import kotlinx.android.synthetic.main.activity_register_user.*
import kotlinx.android.synthetic.main.activity_register_user.btn_agent_active
import kotlinx.android.synthetic.main.activity_register_user.btn_agent_inactive
import kotlinx.android.synthetic.main.activity_register_user.btn_customer_active
import kotlinx.android.synthetic.main.activity_register_user.btn_customer_inactive
import kotlinx.android.synthetic.main.activity_register_user.btn_merchant_active
import kotlinx.android.synthetic.main.activity_register_user.btn_merchant_inactive

class RegistrationUserActivity : AppBizLogin(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        initialize()

    }

    private fun initialize() {

        btn_merchant_inactive.setOnClickListener { assignRole(UserInfoAPP.MERCHANT) }
        btn_customer_inactive.setOnClickListener { assignRole(UserInfoAPP.CUSTOMER)  }
        btn_agent_inactive.setOnClickListener { assignRole(UserInfoAPP.AGENT) }
        documentSpinnerData()

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
                lyt_select_document.visibility=View.VISIBLE
            }
            UserInfoAPP.CUSTOMER->{

                user_role = UserInfoAPP.CUSTOMER

                Common.showGroupViews(btn_customer_active,btn_merchant_inactive,btn_agent_inactive)
                Common.hideGroupViews(btn_agent_active,btn_merchant_active,btn_customer_inactive)
                lyt_select_document.visibility=View.INVISIBLE
            }
            UserInfoAPP.AGENT->{

                user_role = UserInfoAPP.AGENT

                Common.showGroupViews(btn_agent_active,btn_customer_inactive,btn_merchant_inactive)
                Common.hideGroupViews(btn_merchant_active,btn_customer_active,btn_agent_inactive)
                lyt_select_document.visibility=View.VISIBLE
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
                ApiObserver<RegisterUserResModel>(
                    this@RegistrationUserActivity,
                    object :
                        ChangedListener<RegisterUserResModel> {

                        override fun onSuccess(dataWrapper: RegisterUserResModel) {
                            hideProgress()
                            Session.getInstance(this@RegistrationUserActivity)
                                ?.saveUserRole(user_role)
                            Session.getInstance(this@RegistrationUserActivity)
                                ?.saveUserID(dataWrapper.user_id)

                            moveToOtp()
                        }

                        /* override fun onError(dataWrapper: DataWrapper<RegisterUserResModel>) {
                             hideProgress()
                             errorHandle(dataWrapper.error,dataWrapper.apiException)
                         }*/

                    })
            )
        }

    }

    private fun createRegisterUserReqModel(): RegisterUserReqModel {
        return RegisterUserReqModel(
            "",
            ed_full_name.text.toString(),
            ed_password.text.toString()
            ,
            UserInfoAPP.REGISTRATIONS_SOURCE!!,
            user_role!!,
            ed_email_phone.text.toString()
        )
    }

    fun moveToOtp() {
        MoveToAnotherComponent.moveToOTPActivity(this)
    }

    private fun documentSpinnerData() {
        val document = resources.getStringArray(R.array.RegistrationDocument)

        val spinner = findViewById<Spinner>(R.id.sp_select_document)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, document)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if(position==0){

                    }
                    else{
                        Toast.makeText(this@RegistrationUserActivity, getString(R.string.selected_item) + " " + "" + document[position], Toast.LENGTH_SHORT).show()

                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

}