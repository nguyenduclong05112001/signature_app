package com.longhrk.network.model

import com.longhrk.network.const.NetworkConst.NETWORK_ERROR

data class NetworkResponseEvent<out T>(
    val networkStatus: NetworkStatus,
    val data: T? = null,
    val message: String? = null,
    val error: Throwable? = null
) {
    companion object {
        fun <T> success(data: T): NetworkResponseEvent<T> {
            return NetworkResponseEvent(
                networkStatus = NetworkStatus.SUCCESS,
                data = data
            )
        }

        fun <T> error(message: String, error: Throwable?): NetworkResponseEvent<T> {
            return NetworkResponseEvent(
                networkStatus = NetworkStatus.ERROR,
                message = "$NETWORK_ERROR $message",
                error = error
            )
        }

        fun start(): NetworkResponseEvent<Nothing> {
            return NetworkResponseEvent(networkStatus = NetworkStatus.START)
        }

        fun finally(): NetworkResponseEvent<Nothing> {
            return NetworkResponseEvent(networkStatus = NetworkStatus.FINALLY)
        }
    }
}
