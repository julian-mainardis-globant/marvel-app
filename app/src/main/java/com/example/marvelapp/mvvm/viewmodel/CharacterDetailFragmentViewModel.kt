package com.example.marvelapp.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Character
import com.example.domain.util.Result
import com.example.marvelapp.mvvm.model.CharacterDetailFragmentModel
import com.example.marvelapp.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterDetailFragmentViewModel(private val model: CharacterDetailFragmentModel) : ViewModel() {

    private var _characterDetailState = MutableLiveData<CharacterDetailData>()
    val characterDetailState: LiveData<CharacterDetailData>
        get() = _characterDetailState

    fun getCharacterById(characterId: String) = viewModelScope.launch {
        _characterDetailState.postValue(CharacterDetailData(state = CharacterDetailState.RESPONSE_LOADING))
        withContext(Dispatchers.IO) {
            model.getCharacterById(characterId)
        }.let { result ->
            when (result) {
                is Result.Success -> {
                    _characterDetailState.postValue(CharacterDetailData(state = CharacterDetailState.RESPONSE_SUCCESS, data = result.data))
                }
                is Result.Failure -> {
                    if (result.exception.message.equals(Constants.NOT_FOUND)) {
                        _characterDetailState.postValue(CharacterDetailData(state = CharacterDetailState.RESPONSE_ERROR_NOT_FOUND))
                    } else {
                        _characterDetailState.postValue(CharacterDetailData(state = CharacterDetailState.RESPONSE_ERROR))
                    }
                }
            }
        }
    }

    fun onExitButtonPressed() {
        _characterDetailState.value = CharacterDetailData(CharacterDetailState.EXIT_BUTTON_PRESSED)
    }

    data class CharacterDetailData(
        val state: CharacterDetailState,
        val data: Character? = null
    )

    enum class CharacterDetailState {
        RESPONSE_LOADING,
        RESPONSE_SUCCESS,
        RESPONSE_ERROR,
        RESPONSE_ERROR_NOT_FOUND,
        EXIT_BUTTON_PRESSED
    }
}
