package com.example.movieapp.feature.movieList.data.repository.local

import com.example.movieapp.core.common.domain.repository.local.ILocalStorageProvider
import com.example.movieapp.feature.movieList.domain.repository.local.IMovieListLocalDS

class MovieListLocalDS(private val storageProvider: ILocalStorageProvider) : IMovieListLocalDS {
    override suspend fun getSavedMovieLst(): String {
        TODO("Not yet implemented")
    }

    override suspend fun saveMovieLst(moviesJson: String) {
        TODO("Not yet implemented")
    }
}