package com.example.marvelapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentCharacterDetailBinding
import com.example.marvelapp.entity.Character
import com.example.marvelapp.mvvm.viewmodel.CharacterDetailFragmentViewModel
import com.example.marvelapp.util.Constants
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent

class CharacterDetailFragment : DialogFragment(), KoinComponent {

    private lateinit var binding: FragmentCharacterDetailBinding
    private val viewModel: CharacterDetailFragmentViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCharacterDetailBinding.inflate(layoutInflater)
        viewModel.characterDetailState.observe({ lifecycle }, ::updateUI)
        viewModel.getCharacterById(arguments?.getString(Constants.CHARACTER_ID).orEmpty())
        binding.buttonCharacterDetail.setOnClickListener { viewModel.onExitButtonPressed() }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    private fun updateUI(characterDetailData: CharacterDetailFragmentViewModel.CharacterDetailData) {
        when (characterDetailData.state) {
            CharacterDetailFragmentViewModel.CharacterDetailState.RESPONSE_SUCCESS -> showCharacter(characterDetailData.data)
            CharacterDetailFragmentViewModel.CharacterDetailState.RESPONSE_ERROR -> showError(R.string.character_list_general_error)
            CharacterDetailFragmentViewModel.CharacterDetailState.RESPONSE_ERROR_NOT_FOUND -> showError(R.string.character_detail_not_found)
            CharacterDetailFragmentViewModel.CharacterDetailState.RESPONSE_LOADING -> showLoading()
            CharacterDetailFragmentViewModel.CharacterDetailState.EXIT_BUTTON_PRESSED -> dismiss()
        }
    }

    private fun showCharacter(character: Character?) {
        character.let {
            binding.characterDetailLoader.visibility = View.GONE
            binding.textViewCharacterDetail.visibility = View.GONE
            binding.apply {
                this.textViewNameCharacterDetail.text = it?.name
                if (it?.description.isNullOrEmpty()) {
                    this.textViewDescriptionCharacterDetail.text = resources.getString(R.string.empty_description)
                } else {
                    this.textViewDescriptionCharacterDetail.text = it?.description
                }
                Glide.with(this@CharacterDetailFragment)
                    .load(it?.imageURL)
                    .into(this.imageCharacterDetail)
            }
        }
    }

    private fun showError(messageId: Int) {
        binding.characterDetailLoader.visibility = View.GONE
        binding.textViewCharacterDetail.text = getString(messageId)
    }

    private fun showLoading() {
        binding.characterDetailLoader.visibility = View.VISIBLE
        binding.textViewCharacterDetail.text = getString(R.string.character_list_loader)
    }

    companion object {
        fun newInstance(characterId: String): CharacterDetailFragment {
            val args = Bundle()
            args.putString(Constants.CHARACTER_ID, characterId)
            val fragment = CharacterDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
