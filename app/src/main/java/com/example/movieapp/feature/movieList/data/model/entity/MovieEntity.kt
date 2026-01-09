package com.example.movieapp.feature.movieList.data.model.entity

data class MovieEntity(
    val id: Int,
    val title: String,
    val posterPath: String,
    val overview: String,
    val releaseDate: String
)