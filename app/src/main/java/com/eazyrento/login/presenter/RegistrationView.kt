package com.eazyrento.login.presenter

interface RegistrationView {


    fun registerAs(registartionType:String)
    fun showToast(message:String)
    fun register()
    fun moveToOtp()
}