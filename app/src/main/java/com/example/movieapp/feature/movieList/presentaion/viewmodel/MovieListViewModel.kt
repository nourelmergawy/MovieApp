package com.example.movieapp.feature.movieList.presentaion.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.common.data.models.state.Resource
import com.example.movieapp.core.common.presentaion.mvi.MovieAppViewModel
import com.example.movieapp.feature.movieList.domain.interactor.GetMovieListFromRemoteUseCase
import com.example.movieapp.feature.movieList.presentaion.viewmodel.MovieListContract.MovieListAction
import com.example.movieapp.feature.movieList.presentaion.viewmodel.MovieListContract.MovieListEvent
import com.example.movieapp.feature.movieList.presentaion.viewmodel.MovieListContract.MovieListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMovieListFromRemoteUseCase: GetMovieListFromRemoteUseCase
) : MovieAppViewModel<MovieListAction, MovieListEvent, MovieListState>(MovieListState.Idle) {

    init {
        loadMovieList()
    }

    fun loadMovieList() {
        viewModelScope.launch {
            getMovieListFromRemoteUseCase().collect { resource ->
                when (resource) {
                    is Resource.Loading -> updateState(MovieListState.Loading(resource.loading))
                    is Resource.Success -> {
                        updateState(MovieListState.SavedMovie(resource.data))
                    }

                    is Resource.Failure -> updateState(
                        MovieListState.Error(
                            resource.exception
                        )
                    )
                }
            }
        }
    }
}