package com.example.movieapp.features.movieList

import android.graphics.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MovieListViewModel {
    private val _uiState = MutableStateFlow(HomeUiState())

    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

}
data class HomeUiState(
    val recipes: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val errorMessage: String? = null,
    val searchQuery: String = "",
    val selectedCategory: String? = null
) {
    val isEmpty: Boolean
        get() = recipes.isEmpty() && !isLoading && errorMessage == null

    val hasError: Boolean
        get() = errorMessage != null && !isLoading
}