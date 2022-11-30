package com.example.searchviewrecyclernavgraphflowsealed.RETROFIT

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.themoviedb.org/3/movie/"

class ApiHelper {
    companion object {
        fun getRetrofitInstance(): ApiService {
            val gson = GsonBuilder().create()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            val api: ApiService = retrofit.create(ApiService::class.java)

            return api
        }
    }
}