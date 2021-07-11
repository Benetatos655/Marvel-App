package com.example.marvelapplication.repository


import com.example.marvelapplication.api.MarvelService
import com.example.marvelapplication.utils.Constants.API_KEY
import com.example.marvelapplication.utils.Constants.AVENGERS
import com.example.marvelapplication.utils.Constants.HASH
import com.example.marvelapplication.utils.Constants.TIMESTAMP_NEW
import java.sql.Timestamp
import java.time.Instant
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class MarvelRepository @Inject
constructor(private val marvelService : MarvelService) {
    suspend fun getMarvelSeries() = marvelService.getMovies(API_KEY, HASH, TIMESTAMP_NEW, AVENGERS)
    suspend fun getMarvelCharacters(id:Int,limit:Int ,itemsLoaded:Int) = marvelService.getCharachters(API_KEY, HASH, TIMESTAMP_NEW, id,limit,itemsLoaded)
}