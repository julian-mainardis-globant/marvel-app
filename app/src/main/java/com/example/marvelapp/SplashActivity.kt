package com.example.marvelapp

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelapp.databinding.ActivitySplashBinding
import com.example.marvelapp.mvvm.viewmodel.SplashActivityViewModel

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.animState.observe({ lifecycle }, ::changingState)
        viewModel.startAnim()
    }

    private fun changingState(splashData: SplashActivityViewModel.SplashData) {
        when (splashData.state) {
            SplashActivityViewModel.SplashState.INITIAL -> startAnim()
            SplashActivityViewModel.SplashState.ENDED -> endedAnim()
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
