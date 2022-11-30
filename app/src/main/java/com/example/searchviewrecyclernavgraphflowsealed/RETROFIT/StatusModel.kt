package com.example.searchviewrecyclernavgraphflowsealed.RETROFIT

data class StatusModel<out V>(
    val status: Status,
    val mesage: String?,
    val data: V?
) {
    companion object {
        fun <V> sucsess(data: V?): StatusModel<V> {
            return StatusModel(Status.SUCCESS, null, data)
        }

        fun <V> error(msg: String?): StatusModel<V> {
            return StatusModel(Status.ERROR, msg, null)
        }

        fun <V> loading(): StatusModel<V> {
            return StatusModel(Status.LOADING, null, null)
        }

    }
}


enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
