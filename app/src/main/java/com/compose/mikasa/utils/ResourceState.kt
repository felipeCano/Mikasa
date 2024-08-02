package com.compose.mikasa.utils

sealed class ResourceState<T> {
    class Success<T>(val data: T) : ResourceState<T>()
    class Error<T>(val message: String) : ResourceState<T>()
    class Loading<T> : ResourceState<T>()

}