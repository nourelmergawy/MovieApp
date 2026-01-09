package com.example.movieapp.feature.movieList.data.mapper

import com.example.movieapp.core.common.data.constans.Constants.INVALID_ID
import com.example.movieapp.core.common.data.mapper.Mapper
import com.example.movieapp.feature.movieList.data.model.dto.MovieDto
import com.example.movieapp.feature.movieList.data.model.entity.MovieEntity
import com.example.movieapp.feature.movieList.domain.model.Movie

internal object MovieListMapper : Mapper<MovieDto, Movie, MovieEntity>() {
    override fun dtoToDomain(model: MovieDto): Movie {
        return Movie(
            id = model.id ?: INVALID_ID,
            title = model.title.orEmpty(),
            posterPath = model.posterPath.orEmpty(),
            overview = model.overview.orEmpty(),
            releaseDate = model.releaseDate.orEmpty(),
        )
    }
    fun dtoListToDomain(dtoList: List<MovieDto>): List<Movie> {
        return dtoList.map { dtoToDomain(it) }
    }

    override fun entityToDomain(model: MovieEntity): Movie {
        return Movie(
            id = model.id,
            title = model.title,
            posterPath = model.posterPath,
            overview = model.overview,
            releaseDate = model.releaseDate
        )
    }

    override fun domainToEntity(model: Movie): MovieEntity {
        return MovieEntity(
            id = model.id,
            title = model.title,
            posterPath = model.posterPath,
            overview = model.overview,
            releaseDate = model.releaseDate
        )
    }

}