package com.rental.customer.registration.presenter

import android.transition.Visibility
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import com.rental.customer.utils.Validator


class RegistrationActivityPresenter(registrationView: RegistrationView) {

    private var registrationView:RegistrationView

    init {
        this.registrationView=registrationView
    }

     fun registrationAs(registrationType:String, buttonInActiveAgent: TextView, buttonInActiveCustomer:
     TextView, buttonInActiveMerchant: TextView, buttonActiveMerchant: RelativeLayout, buttonActiveCustomer: RelativeLayout,
                        buttonActiveAgent: RelativeLayout){

         if(registrationType.equals("Merchant")){
             buttonActiveMerchant.visibility=View.VISIBLE
             buttonActiveAgent.visibility=View.GONE
             buttonActiveCustomer.visibility=View.GONE
             buttonInActiveMerchant.visibility=View.GONE
             buttonInActiveCustomer.visibility=View.VISIBLE
             buttonInActiveAgent.visibility=View.VISIBLE

         }else if(registrationType.equals("Agent")){
             buttonActiveAgent.visibility=View.VISIBLE
             buttonActiveMerchant.visibility=View.GONE
             buttonActiveCustomer.visibility=View.GONE
             buttonInActiveAgent.visibility=View.GONE
             buttonInActiveCustomer.visibility=View.VISIBLE
             buttonInActiveMerchant.visibility=View.VISIBLE
         }else if(registrationType.equals("Customer")){
             buttonActiveCustomer.visibility=View.VISIBLE
             buttonActiveAgent.visibility=View.GONE
             buttonActiveMerchant.visibility=View.GONE
             buttonInActiveCustomer.visibility=View.GONE
             buttonInActiveMerchant.visibility=View.VISIBLE
             buttonInActiveAgent.visibility=View.VISIBLE
         }

    }

    fun registerAPI(editTextUserName: EditText,editTextEmail: EditText,editTextPassword: EditText){
        if(checkValidation(editTextUserName,editTextEmail,editTextPassword)){
            registrationView.showToast("Registration Successful")
        }

    }

    private fun checkValidation(editTextUserName: EditText,email: EditText, password: EditText): Boolean {
        if (editTextUserName.text.toString().isEmpty()&&email.text.toString().isEmpty() && password.text.toString().isEmpty()) {
            registrationView.showToast("Please Enter All Field")
        }
            else if (editTextUserName.text.toString().length>4) {
                registrationView.showToast("Please Enter Valid UserName")
        } else if (!Validator.isEmailValid(email.text.toString())) {
            registrationView.showToast("Please Enter Valid Email")
            email.requestFocus()
        } else if (password.text.toString().isEmpty()) {
            password.requestFocus()
            registrationView.showToast("Please Enter Valid Password")
        } else if (!Validator.isPasswordValid(password.text.toString())) {
            registrationView.showToast("Invalid Password! minimum length 8")
        }else{
            return true
        }
        return false
    }
}