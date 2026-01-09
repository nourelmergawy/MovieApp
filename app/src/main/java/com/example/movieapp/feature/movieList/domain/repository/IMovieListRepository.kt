package com.example.movieapp.feature.movieList.domain.repository

import com.example.movieapp.feature.movieList.domain.model.Movie

interface IMovieListRepository {
    suspend fun getMovieListFromRemote(): List<Movie>

    suspend fun getMovieListFromLocal(): List<Movie>

    suspend fun searchMovie(query: String): List<Movie>

}