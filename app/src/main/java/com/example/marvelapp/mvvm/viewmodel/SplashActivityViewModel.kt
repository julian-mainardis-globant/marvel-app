package com.example.marvelapp.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashActivityViewModel : ViewModel() {

    private var _animState: MutableLiveData<SplashData> = MutableLiveData()

    val animState: LiveData<SplashData>
        get() {
            return _animState
        }

    fun startAnim() {
        _animState.value = SplashData(SplashState.INITIAL)
    }

    fun endedAnim() {
        _animState.value = SplashData(SplashState.ENDED)
    }

    data class SplashData(
        val state: SplashState
    )

    enum class SplashState {
        INITIAL,
        ENDED
    }
}
