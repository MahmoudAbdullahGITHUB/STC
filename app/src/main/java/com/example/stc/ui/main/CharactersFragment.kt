package com.example.stc.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stc.R
import com.example.stc.data.remote.model.response.charachter.CharacterDataWrapper
import com.example.stc.data.remote.model.response.charachter.Result
import com.example.stc.databinding.FragmentCharactersBinding
import com.example.stc.ui.main.CharactersFragmentDirections
import com.example.stc.ui.main.adapter.ResultsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private var adapter : ResultsAdapter?=null
    private var dataResponse: CharacterDataWrapper? = null
    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!  // Use non-nullable version for safe access

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        subscribeObservers()
    }

    private fun setupRecyclerView() {
        adapter = ResultsAdapter(navigate = { navToDetailsScreen(it) } )
        binding.charactersRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@CharactersFragment.adapter
        }
    }

    private fun subscribeObservers() {
        lifecycleScope.launch {
            viewModel.items.collectLatest { pagingData ->
                adapter?.submitData(pagingData)
            }
        }

        lifecycleScope.launch {
            viewModel.getResponseFlow().collectLatest { data ->
                dataResponse = data
            }
        }
    }

    private fun navToDetailsScreen(item: Result?) {
        val action = CharactersFragmentDirections.actionMainFragmentToDetailsFragment(item)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}