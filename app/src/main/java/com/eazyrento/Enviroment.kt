package com.eazyrento

interface Env{
    companion object{

        const val SPLASH_TIME_OUT:Long = 2*1000 // 3 sec
        const val isLogging = true

   //  private const val SERVER_URL = "https://reqres.in/api/"
       private const val SERVER_URL = "http://qa.eazyrento.com:8000"
        const val BASE_URL = SERVER_URL
    }
}