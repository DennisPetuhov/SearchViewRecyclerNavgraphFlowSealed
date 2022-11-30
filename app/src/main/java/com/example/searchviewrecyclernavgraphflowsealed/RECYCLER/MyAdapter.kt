package com.example.searchviewrecyclernavgraphflowsealed.RECYCLER

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.example.searchviewrecyclernavgraphflowsealed.Movies
import com.example.searchviewrecyclernavgraphflowsealed.R
import com.example.searchviewrecyclernavgraphflowsealed.databinding.RecyclerItemBinding

class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
    var movieList: MutableList<Movies> = mutableListOf<Movies>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.movieName.text = movie.title
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("key", movie)
         //   val directions= Fragment1Directions.actionFragment12ToFragment22(movie.original_language.toString())
                holder.binding.movieHolder.findNavController()
                    .navigate(R.id.action_fragment12_to_fragment22, bundle)
//            it.findNavController().navigate()

        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun updateRecycler(list: MutableList<Movies>) {
        list.toMutableList()
        val diffcallback = MovieDiffutill(movieList, list)
        val differense = DiffUtil.calculateDiff(diffcallback, true)
        movieList = list
        differense.dispatchUpdatesTo(this)


    }

}