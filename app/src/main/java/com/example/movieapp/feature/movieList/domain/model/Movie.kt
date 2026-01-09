package com.example.movieapp.feature.movieList.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val overview: String,
    val releaseDate: String
)