package com.ndc.sispak.common

sealed interface Either<out R> {
    data object Left : Either<Nothing>
    data class Right<out T>(val value: T) : Either<T>
    companion object {
        fun left(): Either<Nothing> = Left
        fun <T> right(effect: T) = Right(effect)
    }

    fun onRight(cb: (R) -> Unit) {
        when (this) {
            Left -> Unit
            is Right -> {
                cb(this.value)
            }
        }
    }
}