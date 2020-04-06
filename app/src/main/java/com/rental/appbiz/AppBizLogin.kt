package com.rental.appbiz

import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rental.ValidationMessage
import com.rental.customer.utils.Validator

open interface AppBizLogin{

    fun checkValidation(editTextUserName: EditText, email: EditText, password: EditText,
                                  checkBoxTerms: CheckBox,user_role:String?): Boolean {

        if(failCheckValdationLoginCredintitial(email,password)){
            return false
        }

        if(user_role==null){
            showToast(ValidationMessage.SELECT_USER_ROLE)
            return false
        }

        if (editTextUserName.text.toString().isEmpty()) {
             showToast("Please Enter User Name Field")
        }

        else if (editTextUserName.text.toString().length<4) {
             showToast("Please Enter Valid UserName")
        } else if(!checkBoxTerms.isChecked){
             showToast("Please Check Terms and Condition")
        }else{
            return true
        }
        return false
    }
    fun failCheckValdationLoginCredintitial(email: EditText,password: EditText):Boolean{
        if(email.text.toString().isEmpty() && password.text.toString().isEmpty()){
            showToast("Please Enter all fields")
        }
       else if (!Validator.isEmailValid(email.text.toString())) {
            showToast("Please Enter Valid Email")
            email.requestFocus()

        } else if (password.text.toString().isEmpty()) {
            password.requestFocus()
            showToast("Please Enter Valid Password")
        } else if (!Validator.isPasswordValid(password.text.toString())) {
            showToast("Invalid Password! minimum length 8")
        }else
        {
            return false
        }

        return true
    }

    fun showToast(message: String)

}