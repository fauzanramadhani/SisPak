package com.ndc.sispak.common

sealed interface UiStatus<out T> {
    data class Success<out T>(val data: T?) : UiStatus<T>
    data class Error(val code: Int, val message: String) : UiStatus<Nothing>
}
