package com.rental

interface ValidationMessage{
    companion object{
        const val VALID_PASSWORD_LENGTH= "Invalid Password! minimum length 8"
        const val VALID_PASSWORD= "Please Enter Valid Password"
        const val VALID_EMAIL_ID = "Please Enter Valid Email"
        const val ENTER_ALL_FIELDS = "Please Enter all fields"
        const val CHECK_TERMS_POLICY= "Please Check Terms and Condition"
        const val VALID_USER_NAME= "Please Enter User Name Field"
        const val SELECT_USER_ROLE = "Please select user role"
        const val NO_DATA_FOUND = "No data found"

    }
}