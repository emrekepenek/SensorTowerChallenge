package com.example.sensortowerchallenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensortowerchallenge.repo.ApiClient
import com.example.sensortowerchallenge.repo.ApiInterface
import com.example.sensortowerchallenge.repo.Joke
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class MainViewModel : ViewModel() {

    private val client by lazy { ApiClient.client?.create(ApiInterface::class.java) }
    protected val mutableLinkItem = MutableLiveData<Joke>()

    val jokeItem: LiveData<Joke>
        get() = mutableLinkItem

    fun load(): Job = viewModelScope.launch {
        val call: Call<Joke?>? = client?.getJoke

        call?.enqueue(object : Callback<Joke?> {
            override fun onResponse(call: Call<Joke?>, response: Response<Joke?>) {
                val joke: Joke? = response.body()
                mutableLinkItem.value = joke
            }

            override fun onFailure(call: Call<Joke?>, t: Throwable) {
            }
        })
    }
}