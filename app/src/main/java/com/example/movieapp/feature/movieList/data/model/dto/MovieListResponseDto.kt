package com.example.movieapp.feature.movieList.data.model.dto

import com.google.gson.annotations.SerializedName

data class MovieListResponseDto(
    @SerializedName("results")
    val movies: List<MovieDto>
)