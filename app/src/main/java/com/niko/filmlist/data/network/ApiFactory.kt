package com.niko.filmlist.data.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory {
    companion object{
        private const val BASE_URL = "https://api.kinopoisk.dev"
        private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(
            BASE_URL).build()
        val api_serv = retrofit.create(ApiService::class.java)
    }
}