package com.example.marvelapp.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.api.util.Result
import com.example.marvelapp.entity.Character
import com.example.marvelapp.mvvm.model.CharacterListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterListViewModel(private val model: CharacterListModel) : ViewModel() {

    private var _characterState = MutableLiveData<CharacterData>()
    val characterState: LiveData<CharacterData>
        get() = _characterState

    fun getCharacters() = viewModelScope.launch {
        _characterState.postValue(CharacterData(state = CharacterState.RESPONSE_LOADING))
        withContext(Dispatchers.IO) {
            model.getCharacters()
        }.let { result ->
            when (result) {
                is Result.Success -> {
                    _characterState.postValue(CharacterData(state = CharacterState.RESPONSE_SUCCESS, data = result.data))
                }
                is Result.Failure -> {
                    if(result.exception.message.equals(NOT_FOUND)){
                        _characterState.postValue(CharacterData(state = CharacterState.RESPONSE_ERROR_NOT_FOUND))
                    } else {
                        _characterState.postValue(CharacterData(state = CharacterState.RESPONSE_ERROR))
                    }
                }
            }
        }
    }

    data class CharacterData(
        var state: CharacterState,
        var data: List<Character> = emptyList()
    )

    enum class CharacterState {
        RESPONSE_LOADING,
        RESPONSE_SUCCESS,
        RESPONSE_ERROR,
        RESPONSE_ERROR_NOT_FOUND
    }

    companion object {
        private const val NOT_FOUND = "NOT_FOUND"
    }
}
