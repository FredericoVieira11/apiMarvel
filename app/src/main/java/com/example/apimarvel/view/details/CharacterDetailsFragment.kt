package com.example.apimarvel.view.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.apimarvel.R
import com.example.apimarvel.databinding.FragmentCharacterDetailsBinding
import com.example.apimarvel.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailsBinding
    private val args: CharacterDetailsFragmentArgs by navArgs()
    private lateinit var viewModel: CharacterDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(CharacterDetailsViewModel::class.java)

        val id = args.id
        viewModel.getDetailsCharacter(id)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setObservers()
    }

    private fun setObservers() {
        viewModel.detailsLiveData.observe(viewLifecycleOwner, Observer {


            if (it.name.isBlank()){
                binding.titleTextView.text = getString(R.string.title_is_empty)
            }else{
                binding.titleTextView.text = it.name
            }

            if (it.description.isBlank()){
                binding.descriptionTextView.text = getString(R.string.description_is_empty)

            }else{
                binding.descriptionTextView.text = it.description

            }
            loadImage(it.url(),binding.imageCharacterImageView,this)
        })
    }
}