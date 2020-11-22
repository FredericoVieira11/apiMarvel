package com.example.apimarvel.remoteDataSource.response.details

data class Result(
    val description: String,
    val id: Int,
    val modified: String,
    val name: String,
    val thumbnail: Thumbnail
)