package com.example.movieapp.feature.movieList.presentaion.viewmodel

import com.example.movieapp.core.common.data.models.exception.MovieException
import com.example.movieapp.feature.movieList.domain.model.Movie

interface MovieListContract {
    sealed interface MovieListState {
        data object Idle : MovieListState
        data class Loading(val isLoading: Boolean) : MovieListState
        data class Error(val exception: MovieException) : MovieListState
        data class SavedMovie(val movieList: List<Movie>) : MovieListState
    }

    sealed interface MovieListAction {
        data class OnMovieClicked(val languageCode: String) : MovieListAction
        data object GetSavedCountries : MovieListAction
    }

    sealed interface MovieListEvent {
        data object NavigateToMovieDetails : MovieListEvent
    }
}