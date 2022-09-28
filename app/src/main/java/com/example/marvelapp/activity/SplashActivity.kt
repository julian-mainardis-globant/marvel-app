package com.example.marvelapp.activity

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelapp.R
import com.example.marvelapp.databinding.ActivitySplashBinding
import com.example.marvelapp.mvvm.viewmodel.SplashViewModel
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.animState.observe({ lifecycle }, ::changingState)
        viewModel.startAnim()
    }

    private fun changingState(splashData: SplashViewModel.SplashData) {
        when (splashData.state) {
            SplashViewModel.SplashState.INITIAL -> startAnim()
            SplashViewModel.SplashState.ENDED -> endedAnim()
        }
    }

    private fun startAnim() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation)
        binding.imageViewSplashActivity.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(anim: Animation?) {}
            override fun onAnimationEnd(anim: Animation?) {
                anim?.setAnimationListener(null)
                viewModel.endedAnim()
            }

            override fun onAnimationRepeat(anim: Animation?) {}
        })
    }

    private fun endedAnim() {
        startActivity(MainActivity.getInstance(this))
        finish()
    }
}
