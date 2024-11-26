package com.example.stc.ui.main

import ResultsAdapter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stc.R
import com.example.stc.data.remote.model.response.charachter.Result
import com.example.stc.databinding.ActivityMainBinding
import com.example.stc.utils.Constants
import com.example.stc.utils.Utils
import com.example.stc.utils.ui.DataState
import com.itworxedu.core.ui.ProgressBarState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val adapter = ResultsAdapter()

    var characters = emptyList<Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()



        loadData()
        subscribeObservers()
    }

    private fun setupRecyclerView() {
        binding.charactersRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun loadData() {
        viewModel.fetchCharacters(
            ts = System.currentTimeMillis().toString(),
            apiKey = Constants.PUBLIC_API_KEY,
            hash = Utils.toMD5Hash(
                System.currentTimeMillis()
                    .toString() + Constants.PRIVATE_API_KEY
                        + Constants.PUBLIC_API_KEY
            ),
            offset = 0,
            limit = 20,
        )
    }

    fun subscribeObservers() {
        lifecycleScope.launch {
            viewModel.characters
                .flowWithLifecycle(lifecycle).collect { dataState ->
                    when (dataState) {
                        is DataState.Success -> {
                            dataState.data?.let { data ->
                                characters = data
                                adapter.submitList(characters)
                                println("RRRRRRRRRRRRRright")
                            }
                        }

                        is DataState.Error -> {
                            when (dataState.exception) {
//                                ResponseCodeHandler.UNAUTHORIZED -> showLoginDialog(requireContext())
//                                ResponseCodeHandler.FORBIDDEN -> showInfoSnackBar(com.itworxedu.components.R.string.msg_general_error)
                                else -> {
//                                    showInfoSnackBar(com.itworxedu.components.R.string.msg_general_error)
                                }
                            }
                        }

                        is DataState.Loading -> {
                            if (dataState.progressBarState == ProgressBarState.Idle) {
//                                hideProgress()
                            } else {
//                                showProgress()
                            }
                        }
                    }

                }
        }
    }

}