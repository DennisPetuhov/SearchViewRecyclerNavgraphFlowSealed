package com.example.searchviewrecyclernavgraphflowsealed.RECYCLER


import androidx.recyclerview.widget.DiffUtil
import com.example.searchviewrecyclernavgraphflowsealed.Movies

class MovieDiffutill(val oldList:List<Movies>, private val newList:List<Movies>):DiffUtil.Callback() {
    override fun getOldListSize(): Int {
       return oldList.size
    }

    override fun getNewListSize(): Int {
       return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id==newList[newItemPosition].id

    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]==newList[newItemPosition]
    }
}