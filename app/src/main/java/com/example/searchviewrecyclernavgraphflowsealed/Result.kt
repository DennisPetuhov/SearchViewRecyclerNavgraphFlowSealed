package com.example.searchviewrecyclernavgraphflowsealed

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
 data class Result(
    val page: Int?=null,
    val results: List<Movies>?=null,
    val total_pages: Int?=null,
    val total_results: Int?=null
):Parcelable