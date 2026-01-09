package com.example.movieapp.feature.movieList.domain.repository.remote

import com.example.movieapp.feature.movieList.data.model.dto.MovieListResponseDto

interface IMovieListRemoteDS {
    suspend fun getMovieList() : MovieListResponseDto

}