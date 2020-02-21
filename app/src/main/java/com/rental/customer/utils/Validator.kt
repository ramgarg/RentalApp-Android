package com.rental.customer.utils

class Validator {
    companion object {
        @JvmStatic
        val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";
        fun isEmailValid(email: String): Boolean {
            return EMAIL_REGEX.toRegex().matches(email);
        }

        fun isPasswordValid(password:String):Boolean{
            if(password.length>6)
                return true
            return false
        }
    }
}