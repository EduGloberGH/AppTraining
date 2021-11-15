package com.edu.glob.pokeapp.di

import com.edu.glob.pokeapp.BuildConfig
import com.edu.glob.pokeapp.data.remote.apis.PokemonAPI
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {

    single {
        createWebService<PokemonAPI>(
            okHttpClient = createHttpClient(),
            baseUrl = BuildConfig.BASE_API_URL
        )
    }

}

inline fun <reified ApiType> createWebService(
    okHttpClient: OkHttpClient,
    baseUrl: String
): ApiType {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(okHttpClient)
        .build()
    return retrofit.create(ApiType::class.java)
}

fun createHttpClient(): OkHttpClient = createHttpClientBuilder().build()

fun createHttpClientBuilder(): OkHttpClient.Builder =
    OkHttpClient.Builder()
    .readTimeout(300, TimeUnit.SECONDS)
    .connectTimeout(300, TimeUnit.SECONDS)
    .addInterceptor(addLogInterceptor())

fun addLogInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}