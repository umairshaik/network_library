package com.example.network_library.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.response()
        setContent {

            val uiState = viewModel.data.collectAsState()

            if (uiState.value.id != null) {
                val movie = uiState.value
                Column(Modifier.padding(10.dp), verticalArrangement = Arrangement.Center) {
                    Text(text = "Movie ID: ${movie.id}")
                    Text(text = "Movie Name In English: ${movie.translations?.en?.title}")
                    Text(text = "Movie Decs In English: ${movie.translations?.en?.description}")
                    Text(text = "Movie Name In Bahasa: ${movie.translations?.id?.title}")
                    Text(text = "Movie Decs In Bahasa: ${movie.translations?.id?.description}")
                }
            }


        }
    }
}