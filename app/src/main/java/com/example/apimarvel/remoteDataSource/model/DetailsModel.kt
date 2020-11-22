package com.example.apimarvel.remoteDataSource.model

data class DetailsModel (
    val name: String,
    val description: String,
    val image: String,
    val extension: String,
){
    fun url(): String{
        return "$image.$extension"
    }
}