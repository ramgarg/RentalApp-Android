package com.eazyrento.login.model.modelclass

data class UserInfo(
    val access_token: String,
    val full_name: String,
    val profile_image: String,
    val refresh_token: String,
    val user_id: Int,
    val user_role: String,
    val username: String,
    val is_address_available:Boolean?
)