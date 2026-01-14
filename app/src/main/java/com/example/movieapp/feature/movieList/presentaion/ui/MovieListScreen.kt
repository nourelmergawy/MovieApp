package com.example.movieapp.feature.movieList.presentaion.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieapp.feature.movieList.presentaion.viewmodel.MovieListContract
import com.example.movieapp.feature.movieList.presentaion.viewmodel.MovieListViewModel


@Composable
fun MovieListScreen(
    onMovieListClick: (String) -> Unit,
    viewModel: MovieListViewModel = hiltViewModel()

) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    MovieListContent(
        uiState = uiState,
        onMovieListClick = onMovieListClick,

        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListContent(
    uiState: MovieListContract.MovieListState,
    onMovieListClick: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Movie APP",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                },
                actions = {
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            MovieCard(
                name = "Movie Name",
                description = "Movie description",
                imageUrl = "https://static0.makeuseofimages.com/wordpress/wp-content/uploads/2022/05/next-movie-apps.jpg?q=50&fit=crop&w=1488&h=837&dpr=1.5",
                onCardClick = { },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}