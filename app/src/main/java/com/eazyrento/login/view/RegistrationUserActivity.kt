package com.eazyrento.login.view

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.eazyrento.*
import com.eazyrento.appbiz.AppBizLogin
import com.eazyrento.common.view.UserInfoAPP
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.webpages.TermsConditionActivity
import com.eazyrento.login.model.modelclass.RegisterUserReqModel
import com.eazyrento.login.model.modelclass.RegisterUserResModel
import com.eazyrento.login.viewmodel.RegisterUserViewModel
import com.eazyrento.supporting.OnPiclImageToBase64
import com.eazyrento.supporting.PhoneNumberFormat
import com.eazyrento.supporting.PhoneTextWatcher
import com.eazyrento.supporting.UploadImageFromDevice
import kotlinx.android.synthetic.main.activity_register_user.*
import kotlinx.android.synthetic.main.activity_register_user.btn_agent_active
import kotlinx.android.synthetic.main.activity_register_user.btn_agent_inactive
import kotlinx.android.synthetic.main.activity_register_user.btn_customer_active
import kotlinx.android.synthetic.main.activity_register_user.btn_customer_inactive
import kotlinx.android.synthetic.main.activity_register_user.btn_merchant_active
import kotlinx.android.synthetic.main.activity_register_user.btn_merchant_inactive

class RegistrationUserActivity : AppBizLogin(){
    private val uploadImageFromDevice = UploadImageFromDevice()
    private var selectProfID:String?=null
    private var selectBase64String:String?=null
    private var user_role:String?=null
//    private  val phoneNumberFormat = PhoneNumberFormat(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        ed_full_name.filters = filterAs(FilterEnum.FULL_NAME)

        phoneNumberFormat = PhoneNumberFormat(this)

        ed_email_phone.addTextChangedListener(PhoneTextWatcher(phoneNumberFormat,ed_email_phone))

        initialize()

    }

    fun onRegisterButtonClick(view: View){

        if(checkValidation(ed_full_name,ed_email_phone,ed_password,checkbox_terms,user_role) && isDocumentUploaded()){

            register(UserInfoAPP.BY_NORMAL)
        }

    }
    fun onLoginClick(view: View){
        finishCurrentActivity(2)
    }
    fun onTermsAndConditionsClick(view: View){
        MoveToAnotherComponent.moveToActivityNormal<TermsConditionActivity>(this)
    }
    private fun isDocumentUploaded():Boolean{

        if (!(user_role.equals(UserInfoAPP.CUSTOMER)) && (selectBase64String==null || selectProfID==null))
        {
            showToast(R.string.SELECT_DOCUMENT)
            return false
        }
        return true
    }

    private fun initialize() {

        btn_merchant_inactive.setOnClickListener { assignRole(UserInfoAPP.MERCHANT) }
        btn_customer_inactive.setOnClickListener { assignRole(UserInfoAPP.CUSTOMER)  }
        btn_agent_inactive.setOnClickListener { assignRole(UserInfoAPP.AGENT) }

        user_role = UserInfoAPP.CUSTOMER
        Common.showGroupViews(btn_customer_active,btn_merchant_inactive,btn_agent_inactive)
        Common.hideGroupViews(btn_agent_active,btn_merchant_active,btn_customer_inactive)
        lyt_select_document.visibility=View.INVISIBLE

        documentSpinnerData()


        skipLogin()
    }

    private fun skipLogin(){
        //tv_skip.setOnClickListener { MoveToAnotherComponent.moveToHomeActivity(this) }
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

    fun register(byUser: String) {

          UserInfoAPP.REGISTRATIONS_SOURCE = byUser

           val registerUserReqModel = createRegisterUserReqModel()

            callAPI()?.let {
                it.observeApiResult(
                    it.callAPIActivity<RegisterUserViewModel>(this)
                        .registerUser(registerUserReqModel)
                    , this, this
                )
            }
    }

    private fun createRegisterUserReqModel(): RegisterUserReqModel {

        var countryCode = ""
        var emailOrPhoneNumber = ed_email_phone.text.toString()

       try {
           if (phoneNumberFormat.phoneNumber==null) {

               phoneNumberFormat.parseNumberWithoutCountryCode(ed_email_phone.text.toString())
           }
           phoneNumberFormat.phoneNumber?.let {
               countryCode = "+" +it.countryCode
               emailOrPhoneNumber =""+it.nationalNumber
           }
       }catch (e:Exception){
                e.printStackTrace()
       }

        return RegisterUserReqModel(
            countryCode,
            ed_full_name.text.toString(),
            ed_password.text.toString(),
            UserInfoAPP.REGISTRATIONS_SOURCE!!,
            user_role!!,
            emailOrPhoneNumber,
            selectProfID,
            selectBase64String
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constant.PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
            uploadImageFromDevice.onActivityResult(requestCode, resultCode, data)
        }
    }
    /*fun moveToOtp() {
        MoveToAnotherComponent.moveToOTPActivity(this)
    }*/

    private fun documentSpinnerData() {
        val document = resources.getStringArray(R.array.RegistrationDocument)

        val spinner = findViewById<Spinner>(R.id.sp_select_document)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, document)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if(position==0){
                            selectBase64String = null
                            selectProfID = null
                    }
                    else{
                        uploadImageFromDevice.pickImage(this@RegistrationUserActivity,
                            object : OnPiclImageToBase64 {
                                override fun onBase64(image64: String?) {
                                    selectProfID = this@RegistrationUserActivity.resources.getStringArray(R.array.RegistrationDocument)[position]
                                    selectBase64String =image64
                                }

                                override fun onBitmap(bm: Bitmap?) {
                                    document_pic.setImageBitmap(bm)
                                }
                            })

                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    override fun <T> onSuccessApiResult(data: T) {

        if (data is RegisterUserResModel) {

//            EazyRantoApplication.onLoginUpdateSession(data.user_info)

//            {"status":201,"user_id":348}

            /*Session.getInstance(this@RegistrationUserActivity)
                ?.saveUserRole(user_role)
            Session.getInstance(this@RegistrationUserActivity)
                ?.saveUserID(data.user_id)*/

            MoveToAnotherComponent.moveToActivityWithIntentValue<OTPActivity>(this,Constant.INTENT_OTP_USER_ID,data.user_id)
            //intent.putExtra(Constant.INTENT_USER_EMAIL,ed_email_phone.text.toString())

        }
    }



}