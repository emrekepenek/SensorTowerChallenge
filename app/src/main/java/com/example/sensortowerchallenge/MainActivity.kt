package com.example.sensortowerchallenge

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.sensortowerchallenge.databinding.MainBinding
import com.example.sensortowerchallenge.repo.Joke


class MainActivity : AppCompatActivity() {

    lateinit var binding: MainBinding
    private val mainViewModel by lazy { MainViewModel() }
    private var jokeReadyToAnswer = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()

        mainViewModel.jokeItem.observe(this){
            binding.textViewSetup.text = it.setup
            jokeReadyToAnswer = true
        }
    }

    private fun setupViews() {
        binding.showcaseButton.apply {
            this.setOnClickListener {
                if (!jokeReadyToAnswer){
                    mainViewModel.load()
                    binding.showcaseButton.text = getString(R.string.answer)
                } else {
                    val joke : Joke = mainViewModel.jokeItem.value!!
                    binding.textViewPunchline.text = joke.punchline
                    binding.showcaseButton.text = getString(R.string.tell_me_a_joke)
                    binding.showcaseButton.isClickable = false
                    startClockDown()
                }
            }
        }
    }

    private fun startClockDown() {
        object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.d("Info",(millisUntilFinished/1000).toString() + "second remain")
            }

            override fun onFinish() {
                jokeReadyToAnswer = false
                binding.showcaseButton.isClickable = true
                binding.textViewPunchline.text = ""
                binding.textViewSetup.text = ""
            }
        }.start()
    }
}