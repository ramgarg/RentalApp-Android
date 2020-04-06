package com.rental.webservice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

   companion object{
       private var retrofit:Retrofit?=null

       val client:Retrofit
       get() {
           if(retrofit ==null){
               retrofit =Retrofit.Builder()
                   .addConverterFactory(GsonConverterFactory.create())
                   .baseUrl(PathURL.BASE_URL)
                   .build()
           }
           return retrofit!!
       }
   }

}