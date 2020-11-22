package com.example.apimarvel.view.listCharacter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apimarvel.R
import com.example.apimarvel.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private lateinit var binding: FragmentCharacterListBinding
    private lateinit var adapterCharacterList: AdapterCharacterList
    private lateinit var  viewModel: CharacterListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCharacterListBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(CharacterListViewModel::class.java)

        viewModel.getListCharacter()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setObservers()
    }

    private fun setObservers() {
        viewModel.characterLiveData.observe(viewLifecycleOwner, Observer {

            adapterCharacterList = AdapterCharacterList(it, this)

            binding.characterListRecyclerView.apply {
                adapter = adapterCharacterList
                adapterCharacterList.notifyDataSetChanged()
                layoutManager = LinearLayoutManager(activity)
            }
        })
    }
}