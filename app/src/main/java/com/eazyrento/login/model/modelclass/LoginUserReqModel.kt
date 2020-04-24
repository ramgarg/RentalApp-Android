package com.eazyrento.login.model.modelclass

data class LoginUserReqModel(
    val device_info: DeviceInfo,
    val password: String,
    val registeration_source: String,
    val source: String,
    /*val user_role: String,*/
    val username: String
)