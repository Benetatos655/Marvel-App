package com.example.marvelapplication.api

import com.example.marvelapplication.model.MarvelCharacters
import com.example.marvelapplication.model.MarvelSeries
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("series")
    suspend fun getMovies(
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String,
        @Query("title") title: String
    ): Response<MarvelSeries>

    @GET("characters")
    suspend fun getCharachters(
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String,
        @Query("series") series: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Response<MarvelCharacters>


}