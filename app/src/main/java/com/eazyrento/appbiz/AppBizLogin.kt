package com.eazyrento.appbiz

import android.content.Context
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eazyrento.ValidationMessage
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.utils.Validator
import com.eazyrento.supporting.PhoneNumberFormat
import com.eazyrento.supporting.isValidPhoneNumber


abstract class AppBizLogin: BaseActivity(){

    protected lateinit var phoneNumberFormat: PhoneNumberFormat

    fun checkValidation(editTextUserName: EditText, email: EditText, password: EditText,
                        checkBoxTerms: CheckBox, user_role:String?): Boolean {

        if(failCheckValdationLoginCredintitial(email,password)){
            return false
        }

        if(user_role==null){
            showToast(ValidationMessage.SELECT_USER_ROLE)
            return false
        }

        if (editTextUserName.text.toString().isEmpty()) {
             showToast(ValidationMessage.VALID_USER_NAME)
        }

        else if (editTextUserName.text.toString().length<4) {
             showToast(ValidationMessage.VALID_USER_NAME)
        } else if(!checkBoxTerms.isChecked){
             showToast(ValidationMessage.CHECK_TERMS_POLICY)
        }else{
            return true
        }
        return false
    }

    //user validation
    fun failCheckValdationLoginCredintitial(email: EditText,password: EditText):Boolean{
        if(email.text.toString().isEmpty() && password.text.toString().isEmpty()){
            showToast(ValidationMessage.VALID_EMAIL_PHONE)
            email.requestFocus()
        }
      else if (password.text.toString().isEmpty()) {
            password.requestFocus()
            showToast(ValidationMessage.VALID_PASSWORD)
        } else if (Validator.isPasswordValid(password.text.toString()).not()) {
            showToast(ValidationMessage.VALID_PASSWORD_LENGTH)
        }
        else if (Validator.isEmailValid(email.text.toString()).not()) {
            return if (isValidPhoneNumber(email.text.toString()).not()/*phoneNumberFormat.isValidPhoneNumber(phoneNumberFormat.parseNumberWithoutCountryCode(email.text.toString())).not()*/){

                showToast(ValidationMessage.VALID_EMAIL_PHONE)
                email.requestFocus()

                true
            }
            else{
                false
            }
        }
        else
        {
            return false
        }

        return true
    }

    // chk validation for profile


    override fun <T> moveOnSelecetedItem(type: T) {
    }

}