package com.example.android.marsrealestate.network

import retrofit2.Call
import retrofit2.http.GET

interface IMarsApiService {
    @GET("realestate")
    suspend fun getProperties(): List<MarsProperty>
}