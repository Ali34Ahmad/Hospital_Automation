package com.example.network.utility


typealias rootError = Error


sealed interface Result<out D,out E: rootError> {
    data class Success<out D>(val data: D) : Result<D, Nothing>
    data class Error<out E: rootError>(val error: E) : Result<Nothing,E>
}

/**
 * Maps the successful value of this [Result] using the provided [map] function
 *
 * If this [Result] is a [Result.Success], the [map] function is applied to the
 * contained data, and a new [Result.Success] containing the result of the mapping
 * is returned
 *
 * If this [Result] is a [Result.Error], the map function is not applied and
 * a new [Result.Error] containing the original error is returned.
 *
 * @param map a function that transforms the successful value (`T`) into a new value (`R`).
 * @param T the type of successful value.
 * @param E the type of error value.
 * @param R the type of successful value of the result.
 *
 * @see Error
 * @author Ali Mansoura.
 */
inline fun <T, E: rootError, R> Result<T, E>.map(map: (T) -> R): Result<R, E> {
    return when(this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(data))
    }
}

/**
 * Performs the given [action] if this [Result] is a [Result.Success].
 *
 * @param action A function to perform on the successful value (`T`).
 * @param T the type of successful value.
 * @param E the type of error value.
 *
 * @see Error
 * @author Ali Mansoura
 */
inline fun <T, E: rootError> Result<T,E>.onSuccess(action:(T) -> Unit) : Result<T,E> {

    return when(this){
        is Result.Error<E> -> this
        is Result.Success<T> -> {
            action(data)
            this
        }
    }
}

/**
 * Performs the given [action] if this [Result] is a [Result.Error].
 *
 * @param action A function to perform on the error value (`E`).
 * @param T the type of successful value.
 * @param E the type of error value.
 *
 * @see Error
 * @author Ali Mansoura
 */
inline fun <T, E: rootError> Result<T, E>.onError(action: (E) -> Unit): Result<T, E> {
    return when(this) {
        is Result.Error -> {
            action(error)
            this
        }
        is Result.Success -> {
            this
        }
    }
}















