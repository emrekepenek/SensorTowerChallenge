package com.example.sensortowerchallenge.repo

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @get:GET("random_joke")
    val getJoke: Call<Joke?>?
}