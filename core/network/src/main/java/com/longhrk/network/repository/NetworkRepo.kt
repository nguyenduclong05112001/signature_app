package com.longhrk.network.repository

import com.longhrk.network.api.APIServer
import com.longhrk.network.utils.networkHandle
import javax.inject.Inject

class NetworkRepo @Inject constructor(private val apiServer: APIServer) {
    suspend fun exampleAPICall() = networkHandle {
        return@networkHandle apiServer.exampleAPI()
    }
}