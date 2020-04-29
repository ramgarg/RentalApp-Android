package com.eazyrento.login.model.modelclass

data class ForgotPasswordRequest(var username:String) {
    constructor():this("xyz@gmail.com")
}