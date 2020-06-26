package com.eazyrento.appbiz

import android.widget.CheckBox
import android.widget.EditText
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.utils.Validator
import com.eazyrento.supporting.PhoneNumberFormat
import com.eazyrento.supporting.isValidPhoneNumber


abstract class AppBizLogin: BaseActivity(){

    protected lateinit var phoneNumberFormat: PhoneNumberFormat

    fun checkValidation(editTextUserName: EditText, email: EditText, password: EditText,
                        checkBoxTerms: CheckBox, user_role:String?): Boolean {

        if(isValidCredintitial(email,password).not()){
            return false
        }

        if(user_role==null){
            showToast(ValidationMessage.SELECT_USER_ROLE)
            return false
        }

        if (editTextUserName.text.toString().isEmpty()) {
             showToast(ValidationMessage.VALID_USER_NAME)
        }

        else if (editTextUserName.text.toString().length< resources.getInteger(R.integer.full_name_min_len)) {
             showToast(ValidationMessage.VALID_USER_NAME)
        } else if(!checkBoxTerms.isChecked){
             showToast(ValidationMessage.CHECK_TERMS_POLICY)
        }else{
            return true
        }
        return false
    }

    //user validation
    fun isValidCredintitial(email: EditText, password: EditText):Boolean{

        when{

            email.text.toString().isEmpty()->{
                email.requestFocus()
                showToast(ValidationMessage.VALID_EMAIL_PHONE)
            }

            password.text.toString().isEmpty() || Validator.isPasswordValid(password.text.toString()).not()->{
                password.requestFocus()
                showToast(ValidationMessage.VALID_PASSWORD_LENGTH)
            }

            Validator.isEmailValid(email.text.toString()).not()->{

                phoneNumberFormat.parseNumberWithoutCountryCode(email.text.toString())

                if (isValidPhoneNumber(""+phoneNumberFormat.phoneNumber?.nationalNumber,this).not()){

                    showToast(ValidationMessage.VALID_EMAIL_PHONE)
                    email.requestFocus()
                }
                else{
                    return true
                }

            }

            else-> return true
        }
        return false

    }

    // chk validation for profile


    override fun <T> moveOnSelecetedItem(type: T) {
    }

}