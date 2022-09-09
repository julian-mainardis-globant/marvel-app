package com.example.marvelapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelapp.databinding.ActivityMainBinding
import com.example.marvelapp.mvvm.viewmodel.MainViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.buttonState.observe({ lifecycle }, ::changingState)
        binding.buttonMainActivity.setOnClickListener { viewModel.onButtonPressed() }
    }

    private fun changingState(buttonData: MainViewModel.ButtonData) {
        when (buttonData.state) {
            MainViewModel.ButtonState.PRESSED -> startActivity(CharacterListActivity.getInstance(this))
        }
    }

    companion object {
        fun getInstance(context: Context) = Intent(context, MainActivity::class.java)
    }
}
