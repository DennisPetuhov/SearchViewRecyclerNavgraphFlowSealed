package com.example.searchviewrecyclernavgraphflowsealed.RETROFIT

import com.example.searchviewrecyclernavgraphflowsealed.Movies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Repository(val api:ApiService) {
    suspend fun  getMovies(key:String):Flow<StatusModel<List<Movies>?>>{
        return flow {
            val response = api.getMovies(key).results
            emit(StatusModel.sucsess(response))
        }.flowOn(Dispatchers.IO)
    }
    suspend fun  getMoviesByTitle(key:String, msg:String):Flow<List<Movies>?>{
        return flow {
            val list = api.getMovies(key).results
            var filteredList = mutableListOf<Movies>()
            if (list != null) {
                for (item in list){
                    if (item.title!!.lowercase().contains(msg.lowercase())){
                        filteredList.add(item)


                    } else{ println("********************")}

                    emit(filteredList)

                }

            }



        }.flowOn(Dispatchers.IO)
    }

}