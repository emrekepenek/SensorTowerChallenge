package com.example.sensortowerchallenge.repo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Joke {

    @SerializedName("setup")
    @Expose
    var setup: String? = null

    @SerializedName("punchline")
    @Expose
    var punchline: String? = null
}