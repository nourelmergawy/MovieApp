package com.example.movieapp.feature.movieList.domain.repository.local

interface IMovieListLocalDS {
    suspend fun getSavedMovieLst(): String
    suspend fun saveMovieLst(moviesJson: String)
}