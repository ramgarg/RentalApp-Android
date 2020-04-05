package com.rental.appbiz

import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rental.customer.utils.Validator

open interface AppBizLogin{

    fun checkValidation(editTextUserName: EditText, email: EditText, password: EditText,
                                  checkBoxTerms: CheckBox): Boolean {

        if (editTextUserName.text.toString().isEmpty()&&email.text.toString().isEmpty() && password.text.toString().isEmpty()) {
             showToast("Please Enter All Field")
        }
        else if (editTextUserName.text.toString().length<4) {
             showToast("Please Enter Valid UserName")
        } else if (!Validator.isEmailValid(email.text.toString())) {
             showToast("Please Enter Valid Email")
            email.requestFocus()
        } else if (password.text.toString().isEmpty()) {
            password.requestFocus()
             showToast("Please Enter Valid Password")
        } else if (!Validator.isPasswordValid(password.text.toString())) {
             showToast("Invalid Password! minimum length 8")
        }else if(!checkBoxTerms.isChecked){
             showToast("Please Check Terms and Condition")
        }else{
            return true
        }
        return false
    }

    fun showToast(message: String)

}