package com.eazyrento.login.model.modelclass

data class UserProfile(
    var address_info: AddressInfo,
    var attached_document: Any,
    var buisness: String,
    var country_code: Any,
    var description: String,
    var dob: String,
    var email: String,
    var full_name: String,
    var gender: String,
    var id_proof_title: String,
    var mobile_number: String,
    var profile_image: String,
    var username_choice: String
)