package com.example.composeapp.utils

sealed class Status<T : Any> {
    data class Success<T : Any>(val data: T) : Status<T>()
    data class Failure<T : Any>(val exception: Exception) : Status<T>()
}

