package com.example.movieapp.feature.movieList.domain.interactor

import com.example.movieapp.core.common.data.models.exception.MovieException
import com.example.movieapp.core.common.data.models.state.Resource
import com.example.movieapp.feature.movieList.domain.model.Movie
import com.example.movieapp.feature.movieList.domain.repository.IMovieListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class GetMovieListFromRemoteUseCase (private val movieListRepository: IMovieListRepository){
    operator fun invoke(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        val country = movieListRepository.getMovieListFromRemote()
        emit(Resource.Success(country))
    }.catch { throwable ->
        val exception =
            when (throwable) {
                is MovieException -> throwable
                is UnknownHostException -> MovieException.Network.UnknownHost(errorMessage = throwable.localizedMessage)
                is SocketTimeoutException -> MovieException.Network.Timeout(errorMessage = throwable.localizedMessage)
                else -> MovieException.UnknownException("Unknown error in GetMovieListFromRemoteUseCase: $throwable")
            }
        emit(Resource.Failure(exception))

    }.onCompletion {
        emit(Resource.Loading(false))
    }.flowOn(Dispatchers.IO)
}