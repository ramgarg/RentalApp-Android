package com.rental.webservice

import com.rental.*
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
           if (retrofit == null) {
               val builder = Retrofit.Builder()
                   .baseUrl(PathURL.BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create())

               // extra params and create client
               val client = OkHttpClient.Builder()
               val token = Session.getInstance(EazyRantoApplication.context)?.getAccessToken()

               client.addInterceptor { chain ->

                   val original = chain.request()
                   val builderHeader = original.newBuilder()
                   token?.let { builderHeader.addHeader(PrefKey.ACCESS_TOKEN,"Bearer "+it) }
                   builderHeader.method(original.method,original.body)

                   chain.proceed(builderHeader.build())
               }

               //TIME OUT SET
               client.connectTimeout(Constant.API_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
               client.readTimeout(Constant.API_READ_TIMEOUT, TimeUnit.SECONDS)

             //LOGGING ENABLED

               if (Env.isLogging) {
                   val loggingInterceptor = HttpLoggingInterceptor()
                   loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                   client.addInterceptor(loggingInterceptor)
               }

               builder.client(client.build())

               retrofit = builder.build()
           }


           return retrofit!!
       }
   }

}