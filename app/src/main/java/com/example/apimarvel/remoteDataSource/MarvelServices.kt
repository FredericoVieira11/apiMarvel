package com.example.apimarvel.remoteDataSource

import com.example.apimarvel.remoteDataSource.response.details.CharacterDetailsResponse
import com.example.apimarvel.remoteDataSource.response.list.CharacterListResponse
import com.example.apimarvel.utils.Utils
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelServices {


    @GET("/v1/public/characters")
    fun getListCharacter(
        @Query(Utils.TS) ts: Int = Utils.TS_VALUE,
        @Query(Utils.API_KEY) apikey: String = Utils.API_KEY_VALUE,
        @Query(Utils.HASH) hash: String = Utils.HASH_VALUE
    ): Call<CharacterListResponse>


    @GET("/v1/public/characters/{id}")
    fun getDetailsCharacter(
        @Path("id") id: Int,
        @Query(Utils.TS) ts: Int = Utils.TS_VALUE,
        @Query(Utils.API_KEY) apikey: String = Utils.API_KEY_VALUE,
        @Query(Utils.HASH) hash: String = Utils.HASH_VALUE
    ): Call<CharacterDetailsResponse>

}