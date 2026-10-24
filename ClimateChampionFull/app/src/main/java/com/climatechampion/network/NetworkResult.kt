package com.climatechampion.network

/**
 * A sealed class to represent the state of a network (or Firestore) operation.
 * Used throughout the app to handle loading, success, and error states uniformly.
 */
sealed class NetworkResult<T> {
    /** Operation completed successfully with [data]. */
    data class Success<T>(val data: T) : NetworkResult<T>()

    /** Operation failed with an error [message]. */
    data class Error<T>(val message: String, val code: Int? = null) : NetworkResult<T>()

    /** Operation is in progress. */
    class Loading<T> : NetworkResult<T>()
}
