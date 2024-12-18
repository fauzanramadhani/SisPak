package com.ndc.sispak.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

fun <T> apiFlow(
    block: suspend () -> Response<BaseResponse<T>>
): Flow<UiStatus<T>> = flow {
    try {
        val response = block()
        val body = response.body()

        if (response.isSuccessful && body != null) {
            emit(UiStatus.Success(body.data))
        } else {
            val errorCode = body?.code ?: response.code()
            val errorResponse = body?.message ?: response.errorBody()?.string() ?: "Unknown Error"
            emit(UiStatus.Error(errorCode, errorResponse))
        }
    } catch (e: SocketTimeoutException) {
        emit(UiStatus.Error(-1, "Connection Timeout: ${e.localizedMessage}"))
    } catch (e: IOException) {
        emit(UiStatus.Error(-2, "No Internet Connection: ${e.localizedMessage}"))
    } catch (e: Exception) {
        emit(UiStatus.Error(-3, e.localizedMessage ?: "Unknown Error"))
    }
}