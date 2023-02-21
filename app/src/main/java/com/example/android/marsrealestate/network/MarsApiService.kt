package com.example.android.marsrealestate.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

/**
 * Retrofit needs at least two things available to it to build a web services
 * - API: the base URI for the web service
 * - Converter Factory (Scalar,Gson):
 * the converter tells Retrofit what to do with the data it gets back from the web service
 * */
class MarsApiService() {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()) // For Moshi's Annotations
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()
    private val retrofitService: IMarsApiService by lazy {
        retrofit.create(IMarsApiService::class.java)
    }

    fun getInstance(): IMarsApiService {
        return retrofitService
    }
}
