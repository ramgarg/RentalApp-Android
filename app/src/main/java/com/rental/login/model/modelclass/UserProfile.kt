package com.rental.login.model.modelclass

data class UserProfile(
    val address_info: AddressInfo,
    val attached_document: Any,
    val buisness: String,
    val country_code: Any,
    val description: String,
    val dob: String,
    val email: String,
    val full_name: String,
    val gender: String,
    val id_proof_title: String,
    val mobile_number: String,
    val profile_image: String,
    val username_choice: String
)