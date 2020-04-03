package com.rental.login.presenter

import android.widget.*
import com.rental.login.model.repositry.RegistrationRepository
import com.rental.customer.utils.Common
import com.rental.customer.utils.Validator


class RegistrationActivityPresenter(registrationView: RegistrationView) {

    private var registrationView: RegistrationView
    var registrationRepository: RegistrationRepository

    init {
        this.registrationView=registrationView
        registrationRepository=
            RegistrationRepository(registrationView)
    }

     fun registrationAs(registrationType:String, buttonInActiveAgent: TextView, buttonInActiveCustomer:
     TextView, buttonInActiveMerchant: TextView, buttonActiveMerchant: RelativeLayout, buttonActiveCustomer: RelativeLayout,
                        buttonActiveAgent: RelativeLayout){

         if(registrationType.equals("Merchant")){
             Common.showGroupViews(buttonActiveMerchant,buttonInActiveCustomer,buttonInActiveAgent)
             Common.hideGroupViews(buttonActiveAgent,buttonActiveCustomer,buttonInActiveMerchant)

         }else if(registrationType.equals("Agent")){
             Common.showGroupViews(buttonActiveAgent,buttonInActiveCustomer,buttonInActiveMerchant)
             Common.hideGroupViews(buttonActiveMerchant,buttonActiveCustomer,buttonInActiveAgent)
         }else if(registrationType.equals("Customer")){
             Common.showGroupViews(buttonActiveCustomer,buttonInActiveMerchant,buttonInActiveAgent)
             Common.hideGroupViews(buttonActiveAgent,buttonActiveMerchant,buttonInActiveCustomer)
         }

    }

    fun registerAPI(editTextUserName: EditText,editTextEmail: EditText,editTextPassword: EditText,
    checkBoxTerms: CheckBox){
        if(checkValidation(editTextUserName,editTextEmail,editTextPassword,checkBoxTerms)){
            registrationRepository.registrationAPI()
        }

    }

    private fun checkValidation(editTextUserName: EditText,email: EditText, password: EditText,checkBoxTerms: CheckBox): Boolean {
        if (editTextUserName.text.toString().isEmpty()&&email.text.toString().isEmpty() && password.text.toString().isEmpty()) {
            registrationView.showToast("Please Enter All Field")
        }
            else if (editTextUserName.text.toString().length<4) {
                registrationView.showToast("Please Enter Valid UserName")
        } else if (!Validator.isEmailValid(email.text.toString())) {
            registrationView.showToast("Please Enter Valid Email")
            email.requestFocus()
        } else if (password.text.toString().isEmpty()) {
            password.requestFocus()
            registrationView.showToast("Please Enter Valid Password")
        } else if (!Validator.isPasswordValid(password.text.toString())) {
            registrationView.showToast("Invalid Password! minimum length 8")
        }else if(!checkBoxTerms.isChecked){
            registrationView.showToast("Please Check Terms and Condition")
        }else{
            return true
        }
        return false
    }
}