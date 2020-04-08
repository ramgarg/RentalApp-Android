package com.rental

interface Env{
    companion object{

        const val isLogging = true

        const val SERVER_URL_PRODUCTION = "https://reqres.in/api/"
        const val SERVER_URL_QA = "http://qa.eazyrento.com:8000"
        const val BASE_URL = SERVER_URL_PRODUCTION
    }
}