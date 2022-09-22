package com.example.marvelapp.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.api.util.Result
import com.example.marvelapp.entity.Character
import com.example.marvelapp.mvvm.model.CharacterListModel
import com.example.marvelapp.util.Constants
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
                    if (result.exception.message.equals(Constants.NOT_FOUND)) {
                        _characterState.postValue(CharacterData(state = CharacterState.RESPONSE_ERROR_NOT_FOUND))
                    } else {
                        _characterState.postValue(CharacterData(state = CharacterState.RESPONSE_ERROR))
                    }
                }
            }
        }
    }

    fun onCharacterPressed(characterId: String) {
        _characterState.value = CharacterData(CharacterState.CHARACTER_PRESSED, idCharacter = characterId)
    }

    data class CharacterData(
        val state: CharacterState,
        val data: List<Character> = emptyList(),
        val idCharacter: String = Constants.EMPTY_STRING
    )

    enum class CharacterState {
        RESPONSE_LOADING,
        RESPONSE_SUCCESS,
        RESPONSE_ERROR,
        RESPONSE_ERROR_NOT_FOUND,
        CHARACTER_PRESSED
    }
}
