package com.eazyrento

interface Env{
    companion object{


        const val SPLASH_TIME_OUT:Long = 2*1000 // 3 sec
        const val isLogging = true

        // private const val SERVER_URL = "http://qa.eazyrento.com:8000"

//        private const val SERVER_URL = "http://test.eazyrento.com"
       private const val SERVER_URL = "https://qa.eazyrento.com"

      //  private const val SERVER_URL = "https://www.eazyrento.com"


        const val BASE_URL = SERVER_URL

        //Hipppo chat
        const val HIPPO_APP_KEY = "02fde7e9accd26a58283350da5ef0e2a"
        const val HIPPO_APP_TYPE ="MobileAndroid"
        const val HIPPO_CHAT_TITLE = "EazyRento support"
        const val HIPPO_LOADER_TIME:Long = 700 //MS

    }
}