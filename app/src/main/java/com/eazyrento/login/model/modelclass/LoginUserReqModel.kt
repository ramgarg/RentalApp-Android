package com.eazyrento.login.model.modelclass

data class LoginUserReqModel(
    val device_info: DeviceInfo,
    val password: String,
    val registeration_source: String,
    val source: String,
    val username: String,

    /*social login*/
    var user_role: String?=null,
    var full_name:String?=null,
    var email:String?=null
)