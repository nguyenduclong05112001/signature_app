package com.longhrk.network.utils

import com.google.gson.Gson
import com.longhrk.network.model.NetworkResponseEvent
import com.longhrk.network.model.NetworkStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

inline fun <reified T> networkHandle(
    crossinline networkCalled: suspend () -> T
): Flow<NetworkResponseEvent<T>> {
    return flow {
        emit(NetworkResponseEvent.start())
        try {
            val data = networkCalled()
            emit(NetworkResponseEvent.success(data))
        } catch (e: HttpException) {
            try {
                val parseModel: T =
                    Gson().fromJson(e.response()?.errorBody()?.string(), T::class.java)
                emit(NetworkResponseEvent.success(parseModel))
            } catch (g: Throwable) {
                emit(
                    NetworkResponseEvent.error(
                        message = g.message.toString(),
                        error = Throwable(g.message, g.cause)
                    )
                )
            }
        } catch (e: IOException) {
            emit(
                NetworkResponseEvent.error(
                    message = e.message ?: "",
                    error = Throwable(e.message, e.cause)
                )
            )
        } finally {
            emit(NetworkResponseEvent.finally())
        }
    }
}

fun <T> NetworkResponseEvent<T>.onStart(callback: () -> Unit) {
    if (this.networkStatus === NetworkStatus.START) {
        callback.invoke()
    }
}

fun <T> NetworkResponseEvent<T>.onSuccess(callback: (T) -> Unit): NetworkResponseEvent<T> {
    if (this.networkStatus === NetworkStatus.SUCCESS) {
        this.data?.let { callback.invoke(it) }
    }
    return this
}


fun <T> NetworkResponseEvent<T>.onError(callback: (e: Throwable) -> T): NetworkResponseEvent<T> {
    if (this.networkStatus === NetworkStatus.ERROR) {
        this.error?.let { callback.invoke(it) }
    }
    return this
}


fun <T> NetworkResponseEvent<T>.onFinally(callback: () -> Unit) {
    if (this.networkStatus === NetworkStatus.FINALLY) {
        callback.invoke()
    }
}