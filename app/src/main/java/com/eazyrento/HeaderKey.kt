package com.eazyrento

interface HeaderKey{
    companion object{
        const val ACCESS_TOKEN_HEADER = "Authorization"
        const val LANGUAGE_HEADER = "Accept-Language"
    }
}