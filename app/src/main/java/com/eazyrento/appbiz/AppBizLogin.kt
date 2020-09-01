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

        if(user_role==null){
            showToast(R.string.SELECT_USER_ROLE)
            return false
        }

        else if (editTextUserName.text.toString().isEmpty()) {
             showToast(R.string.VALID_USER_NAME)
        }

        else if (editTextUserName.text.toString().length< resources.getInteger(R.integer.full_name_min_len)) {
             showToast(R.string.VALID_USER_NAME)
        }
        else if(isValidCredintitial(email,password).not()){
            return false
        }
        else if(!checkBoxTerms.isChecked){
            showToast(R.string.CHECK_TERMS_POLICY)
        }
        else{
            return true
        }
        return false
    }

    //user validation
    fun isValidCredintitial(email: EditText, password: EditText):Boolean{

        when{

            email.text.toString().isEmpty() ->{
                email.requestFocus()
                showToast(R.string.VALID_EMAIL_PHONE)
            }

            Validator.isEmailValid(email.text.toString()).not()->{

                phoneNumberFormat.parseNumberWithoutCountryCode(email.text.toString())

                if (isValidPhoneNumber(""+phoneNumberFormat.phoneNumber?.nationalNumber,this).not()){

                    showToast(R.string.VALID_EMAIL_PHONE)
                    email.requestFocus()
                }
                else{
                    return true
                }

            }

            password.text.toString().isEmpty() || Validator.isPasswordValid(password.text.toString()).not()->{
                password.requestFocus()
                showToast(R.string.VALID_PASSWORD_LENGTH)
            }

            else-> return true
        }
        return false

    }

    // chk validation for profile


    override fun <T> moveOnSelecetedItem(type: T) {
    }

}