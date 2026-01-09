package com.example.movieapp.feature.movieList.data.repository

import com.example.movieapp.core.extensions.toJson
import com.example.movieapp.feature.movieList.data.mapper.MovieListMapper
import com.example.movieapp.feature.movieList.domain.model.Movie
import com.example.movieapp.feature.movieList.domain.repository.IMovieListRepository
import com.example.movieapp.feature.movieList.domain.repository.local.IMovieListLocalDS
import com.example.movieapp.feature.movieList.domain.repository.remote.IMovieListRemoteDS

class MovieListRepository(
    private val remoteDS: IMovieListRemoteDS,
    private val localDs: IMovieListLocalDS
) : IMovieListRepository {

    override suspend fun getMovieListFromRemote(): List<Movie> {
        val result = remoteDS.getMovieList()
        val movies = MovieListMapper.dtoListToDomain(dtoList = result.movies )

//        localDs.saveMovieLst(result.toJson())
        return movies
    }

    override suspend fun getMovieListFromLocal(): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun searchMovie(query: String): List<Movie> {
        TODO("Not yet implemented")
    }
}