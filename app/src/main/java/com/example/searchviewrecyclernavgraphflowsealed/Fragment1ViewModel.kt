package com.example.searchviewrecyclernavgraphflowsealed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchviewrecyclernavgraphflowsealed.RETROFIT.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class Fragment1ViewModel() : ViewModel() {
    val api = ApiHelper.getRetrofitInstance()
    val repo = Repository(api)


    val flow: StateFlow<StatusModel<List<Movies>>> get() = mutableFlow

    // var mutableFlow: MutableStateFlow<List<Movies>?> = MutableStateFlow(listOf())
    var mutableFlow = MutableStateFlow(
        StatusModel(
            Status.LOADING, "",
            listOf<Movies>()
        )
    )

    fun getMovies(key: String) {
        mutableFlow.value = StatusModel.loading()
        viewModelScope.launch(Dispatchers.IO) {

            repo.getMovies(key)
                .catch { mutableFlow.value = StatusModel.error(it.message.toString()) }

                .collect {
                    it?.let {
                        mutableFlow.value = StatusModel.sucsess(it.data)
                    }
                }
        }

    }



     fun filter(text: String, movieList: List<Movies>): ArrayList<Movies> {
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<Movies> = ArrayList()

        // running a for loop to compare elements.
        for (item in movieList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.title!=null)
                if(item.title.toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
//            Toast.makeText(this@Fragment1, "No Data Found..", Toast.LENGTH_SHORT).show()

        } else {
            // at last we are passing that filtered
            // list to our adapter class.
//            courseRVAdapter.filterList(filteredlist)
        }
         viewModelScope.launch {
             StatusModel.sucsess(filteredlist)  }

        return filteredlist
    }


//    fun searchMovieTitle(key: String, msg: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repo.getMoviesByTitle(key, msg).collect{mutableFlow.value = it.data}
//        }
//
//    }

}