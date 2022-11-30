package com.example.searchviewrecyclernavgraphflowsealed.RETROFIT

import com.example.searchviewrecyclernavgraphflowsealed.Movies
import com.example.searchviewrecyclernavgraphflowsealed.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("popular?")
    suspend fun getMovies(@Query("api_key") ipKey:String):Result

}