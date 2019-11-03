package com.example.androiddev2019.features.home.data.api

import com.example.androiddev2019.features.home.data.model.Cloth
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ShopApi {
    @GET("clothes")
    fun getClothes(): Deferred<Response<List<Cloth>>>
}