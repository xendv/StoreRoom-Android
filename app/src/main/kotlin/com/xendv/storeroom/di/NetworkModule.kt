package com.xendv.storeroom.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.xendv.storeroom.data.common.Endpoints
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

@OptIn(ExperimentalSerializationApi::class)
val networkModule = module {
    single {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
            explicitNulls = false
            classDiscriminator = "IBLOCK_CODE"
        }
    }

    single {
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
        Retrofit.Builder()
            .addConverterFactory(
                get<Json>().asConverterFactory("application/json".toMediaType())
            )
            .client(client)
            .baseUrl(Endpoints.API_BASE_URL)
            .build()
    }

    factory {
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}