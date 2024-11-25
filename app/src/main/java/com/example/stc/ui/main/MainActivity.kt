package com.example.stc.ui.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.stc.R
import com.example.stc.utils.Constants
import com.example.stc.utils.Utils
import com.example.stc.utils.ui.DataState
import com.itworxedu.core.ui.ProgressBarState
import com.itworxedu.core.ui.ResponseCodeHandler
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        loadData()
        subscribeObservers()
    }

    private fun loadData() {
        viewModel.fetchCharacters(
            ts=System.currentTimeMillis().toString(),
            apiKey = Constants.PUBLIC_API_KEY,

            hash = Utils.toMD5Hash(
                System.currentTimeMillis()
                    .toString() + Constants.PRIVATE_API_KEY
                        + Constants.PUBLIC_API_KEY
            )
        )
    }

    fun subscribeObservers() {
        lifecycleScope.launch {
            viewModel.characters
                .flowWithLifecycle(lifecycle).collect { dataState ->
                    when (dataState) {
                        is DataState.Success -> {
                            dataState.data?.let { posts ->
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