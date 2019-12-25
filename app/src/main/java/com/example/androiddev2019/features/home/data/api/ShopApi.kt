package com.example.androiddev2019.features.home.data.api

import com.example.androiddev2019.features.home.data.model.Cloth
import com.example.androiddev2019.features.home.data.model.Type
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ShopApi {
    @GET("clothes")
    fun getClothes(): Deferred<Response<List<Cloth>>>
}


interface ShopApi2 {
    @GET("bVoCzaumuW?indent=2")
    fun getTypes(): Deferred<Response<List<Type>>>
}