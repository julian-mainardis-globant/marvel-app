package com.example.marvelapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.domain.entity.Character
import com.example.marvelapp.R
import com.example.marvelapp.adapter.CharacterAdapter
import com.example.marvelapp.adapter.CharacterAdapterListener
import com.example.marvelapp.databinding.ActivityCharacterListBinding
import com.example.marvelapp.fragment.CharacterDetailFragment
import com.example.marvelapp.mvvm.viewmodel.CharacterListViewModel
import com.example.marvelapp.mvvm.viewmodel.CharacterListViewModel.CharacterState.*
import com.example.marvelapp.util.Constants
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent

class CharacterListActivity : AppCompatActivity(), KoinComponent, CharacterAdapterListener {

    private lateinit var binding: ActivityCharacterListBinding
    private val viewModel: CharacterListViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getCharacters()
        viewModel.characterState.observe({ lifecycle }, ::updateUI)
    }

    private fun updateUI(characterData: CharacterListViewModel.CharacterData) {
        when (characterData.state) {
            RESPONSE_SUCCESS -> showCharacters(characterData.data)
            RESPONSE_ERROR -> showError(R.string.character_list_general_error)
            RESPONSE_ERROR_NOT_FOUND -> showError(R.string.character_list_error_not_found)
            RESPONSE_LOADING -> showLoading()
            CHARACTER_PRESSED -> showCharacterDetail(characterData.idCharacter)
        }
    }

    private fun showCharacters(characterList: List<Character>) {
        binding.charactersListLoader.visibility = View.GONE
        binding.textViewSubtitleCharactersActivity.visibility = View.GONE
        binding.recyclerViewCharacterList.adapter = CharacterAdapter(characterList, this)
    }

    private fun showError(messageId: Int) {
        binding.charactersListLoader.visibility = View.GONE
        binding.textViewSubtitleCharactersActivity.text = getString(messageId)
    }

    private fun showLoading() {
        binding.charactersListLoader.visibility = View.VISIBLE
        binding.textViewSubtitleCharactersActivity.text = getString(R.string.character_list_loader)
    }

    private fun showCharacterDetail(characterId: String) {
        val characterDetailFragment = CharacterDetailFragment.newInstance(characterId)
        val fragmentManager: FragmentManager = supportFragmentManager
        characterDetailFragment.show(fragmentManager, Constants.TAG)
    }

    override fun setOnClickListener(characterId: String) {
        viewModel.onCharacterPressed(characterId)
    }

    companion object {
        fun getInstance(context: Context) = Intent(context, CharacterListActivity::class.java)
    }
}
