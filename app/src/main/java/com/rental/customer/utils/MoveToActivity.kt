package com.rental.customer.utils

import android.content.Context
import android.content.Intent
import com.rental.customer.login.view.OTPActivity
import com.rental.customer.login.view.ForgotPasswordActivity
import com.rental.customer.login.view.RegistrationActivity

class MoveToActivity {

    companion object{

        fun moveToForgotPasswordActivity(context: Context){
            context.startActivity(Intent(context, ForgotPasswordActivity::class.java))
        }

        fun moveToRegistrationActivity(context: Context){
            context.startActivity(Intent(context, RegistrationActivity::class.java))
        }

        fun moveToOTPActivity(context: Context){
            context.startActivity(Intent(context, OTPActivity::class.java))
        }
    }

}