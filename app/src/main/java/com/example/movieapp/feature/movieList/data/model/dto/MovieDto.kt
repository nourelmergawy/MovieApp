package com.example.movieapp.feature.movieList.data.model.dto

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null
)