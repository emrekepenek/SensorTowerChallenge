package com.example.sensortowerchallenge.repo

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var retrofit: Retrofit? = null
    private const val Base_Url = "https://official-joke-api.appspot.com/"

    val client: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(OkHttpClient())
                    .build()
                return retrofit
            }
            return retrofit
        }
}