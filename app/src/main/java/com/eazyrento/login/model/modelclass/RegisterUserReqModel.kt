package com.eazyrento.login.model.modelclass

data class RegisterUserReqModel(
    val country_code: String,
    val full_name: String,
    val password: String,
    val registeration_source: String,
    val user_role: String,
    val username: String
)