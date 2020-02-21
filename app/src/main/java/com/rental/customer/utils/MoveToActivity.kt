package com.rental.customer.utils

import android.content.Context
import android.content.Intent
import com.rental.customer.otp.view.OTPActivity
import com.rental.customer.forgotpassword.view.ForgotPassword
import com.rental.customer.registration.view.RegistrationActivity

class MoveToActivity {

    companion object{

        fun moveToForgotPasswordActivity(context: Context){
            context.startActivity(Intent(context, ForgotPassword::class.java))
        }

        fun moveToRegistrationActivity(context: Context){
            context.startActivity(Intent(context, RegistrationActivity::class.java))
        }

        fun moveToOTPActivity(context: Context){
            context.startActivity(Intent(context, OTPActivity::class.java))
        }
    }

}