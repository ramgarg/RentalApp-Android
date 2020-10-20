package com.eazyrento.tracking.data.api

import com.eazyrento.Env
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GoogleApiCreation {


    private fun getRetrofit(): Retrofit {

        val builder =  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://maps.googleapis.com/")

        val client = OkHttpClient.Builder()

        if (Env.isLogging) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(loggingInterceptor)
        }

        builder.client(client.build())

        return builder.build()

    }

    fun getDirectionApi():ApiInterface{
        return getRetrofit().create(ApiInterface::class.java)
    }

}