package com.mohit.weatherfeather.api.response

sealed class NetworkResponse<out T> {
    data class Success<T>(var data:T) : NetworkResponse<T>()
    data class Error(var message : String) : NetworkResponse<Nothing>()
    data object Loading : NetworkResponse<Nothing>()
}