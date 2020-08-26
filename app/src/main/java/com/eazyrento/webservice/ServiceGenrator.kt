package com.eazyrento.webservice

import com.eazyrento.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceGenrator {

   companion object{
       var retrofit:Retrofit?=null

       val client:Retrofit
       get() {
           val builder = Retrofit.Builder()
           // extra params and create client
           val client = OkHttpClient.Builder()

         //  if (retrofit == null) {

               builder.baseUrl(PathURL.BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create())

               //TIME OUT SET
               client.connectTimeout(Constant.API_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
               client.readTimeout(Constant.API_READ_TIMEOUT, TimeUnit.SECONDS)

             //LOGGING ENABLED

               if (Env.isLogging) {
                   val loggingInterceptor = HttpLoggingInterceptor()
                   loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                   client.addInterceptor(loggingInterceptor)
               }


          // }

           val tokenHeader = Session.getInstance(EazyRantoApplication.context)?.getAccessToken()
           val  languageHeader= Session.getInstance(EazyRantoApplication.context)?.getLocalLanguage()

           client.addInterceptor { chain ->

               val original = chain.request()
               val builderHeader = original.newBuilder()
               tokenHeader?.let {
                   builderHeader.addHeader(HeaderKey.ACCESS_TOKEN_HEADER,"Bearer "+it)
               }
               languageHeader?.let {
                   builderHeader.addHeader(HeaderKey.LANGUAGE_HEADER,it)
               }
               builderHeader.method(original.method,original.body)

               chain.proceed(builderHeader.build())
           }

           builder.client(client.build())

           retrofit = builder.build()

           return retrofit!!
       }
   }

}