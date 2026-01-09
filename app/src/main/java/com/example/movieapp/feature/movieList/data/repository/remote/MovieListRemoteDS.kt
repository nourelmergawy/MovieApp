package com.example.movieapp.feature.movieList.data.repository.remote

import com.example.movieapp.core.common.domain.repository.remote.IRestApiNetworkProvider
import com.example.movieapp.feature.movieList.data.model.dto.MovieListResponseDto
import com.example.movieapp.feature.movieList.domain.repository.remote.IMovieListRemoteDS

class MovieListRemoteDS(
    private val restApiNetworkProvider: IRestApiNetworkProvider
) : IMovieListRemoteDS {
    override suspend fun getMovieList(): MovieListResponseDto {
        return restApiNetworkProvider.get(
            pathUrl = "movie/popular/",
            queryParams = mapOf("pagination" to "all"),
            responseType = MovieListResponseDto::class.java
        )
    }
}