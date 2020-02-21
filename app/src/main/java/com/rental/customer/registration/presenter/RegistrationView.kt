package com.rental.customer.registration.presenter

interface RegistrationView {


    fun registerAs(registartionType:String)
    fun showToast(message:String)
    fun register()
    fun moveToOtp()
}