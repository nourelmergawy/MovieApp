package com.example.movieapp.feature.movieList.di

import com.example.movieapp.core.common.domain.repository.local.ILocalStorageProvider
import com.example.movieapp.core.common.domain.repository.remote.IRestApiNetworkProvider
import com.example.movieapp.feature.movieList.data.repository.MovieListRepository
import com.example.movieapp.feature.movieList.data.repository.local.MovieListLocalDS
import com.example.movieapp.feature.movieList.data.repository.remote.MovieListRemoteDS
import com.example.movieapp.feature.movieList.domain.interactor.GetMovieListFromRemoteUseCase
import com.example.movieapp.feature.movieList.domain.repository.IMovieListRepository
import com.example.movieapp.feature.movieList.domain.repository.local.IMovieListLocalDS
import com.example.movieapp.feature.movieList.domain.repository.remote.IMovieListRemoteDS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class MovieListModule {
    @Provides
    fun provideGetMovieListFromRemoteUseCase(movieListRepository: IMovieListRepository): GetMovieListFromRemoteUseCase {
        return GetMovieListFromRemoteUseCase(
            movieListRepository = movieListRepository
        )
    }

    @Provides
    fun provideMovieListRepository(
        movieListRemoteDS: IMovieListRemoteDS,
        movieListLocalDS: IMovieListLocalDS
    ): IMovieListRepository {
        return MovieListRepository(movieListRemoteDS, movieListLocalDS)
    }

    @Provides
    fun provideMovieListRemoteDS(
        restApiNetworkProvider: IRestApiNetworkProvider
    ): IMovieListRemoteDS {
        return MovieListRemoteDS(restApiNetworkProvider)
    }

    @Provides
    fun getMovieListLocalDS(localStorageProvider: ILocalStorageProvider): IMovieListLocalDS {
        return MovieListLocalDS(storageProvider = localStorageProvider)
    }

}