package com.eazyrento.customer.presenter

interface ForgotPasswordInterface {

    fun forgotPassword()
    fun showToast(message:String)
        fun onSuccess()
        fun onFailed()

}