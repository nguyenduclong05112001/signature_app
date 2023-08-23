package com.longhrk.network.api

import android.os.Parcelable
import com.longhrk.network.const.NetworkConst.API_TESTING_FACT
import kotlinx.parcelize.Parcelize
import retrofit2.http.GET

/** data example
 * {
 * "fact":"Cats lap liquid from the underside of their tongue, not from the top.",
 * "length":69
 * }
 * */

interface APIServer {

    @GET(API_TESTING_FACT)
    suspend fun exampleAPI(): Fact
}

@Parcelize
data class Fact(
    val fact: String,
    val length: Int,
) : Parcelable