package com.rental

interface Env{
    companion object{

        const val isLogging = true

//      private const val SERVER_URL = "https://reqres.in/api/"
        private const val SERVER_URL = "http://qa.eazyrento.com:8000"
        const val BASE_URL = SERVER_URL
    }
}