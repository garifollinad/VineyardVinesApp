package com.example.androiddev2019.features.home.data.repository

import com.example.androiddev2019.features.home.data.api.ShopApi
import com.example.androiddev2019.features.home.data.model.Cloth
import retrofit2.Response

interface HomeRepository {
    suspend fun getClothes(): Response<List<Cloth>>
}

class HomeRepositoryImpl(val api: ShopApi): HomeRepository {
    override suspend fun getClothes(): Response<List<Cloth>> {
        return api.getClothes().await()
    }
}