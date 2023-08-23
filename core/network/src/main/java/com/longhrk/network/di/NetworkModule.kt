package com.longhrk.network.di

import com.longhrk.network.api.APIServer
import com.longhrk.network.const.NetworkConst.API_TESTING
import com.longhrk.network.repository.NetworkRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @BaseUri
    @Provides
    @Singleton
    fun providesBaseUri() = API_TESTING

    @TimeOut
    @Provides
    @Singleton
    fun providesTimeOut() = 60L

    @GsonFactory
    @Provides
    @Singleton
    fun providesGsonFactory(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }

    @Provides
    fun provideOkHttpClient(
        @TimeOut timeOut: Long,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val client: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(timeout = timeOut, unit = TimeUnit.SECONDS)
            .writeTimeout(timeout = timeOut, unit = TimeUnit.SECONDS)
            .readTimeout(timeout = timeOut, unit = TimeUnit.SECONDS)
        client.addInterceptor(httpLoggingInterceptor)
        return client.build()
    }

    @Provides
    @Singleton
    fun providesAPIServer(
        @BaseUri uriBase: String,
        @GsonFactory factory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): APIServer = Retrofit.Builder()
        .baseUrl(uriBase)
        .client(okHttpClient)
        .addConverterFactory(factory)
        .build()
        .create(APIServer::class.java)

    @Provides
    @Singleton
    fun providesNetworkRepo(apiServer: APIServer) = NetworkRepo(apiServer)
}