package com.example.androiddev2019.features.home.data.repository

import com.example.androiddev2019.features.home.data.api.ShopApi
import com.example.androiddev2019.features.home.data.api.ShopApi2
import com.example.androiddev2019.features.home.data.model.Cloth
import com.example.androiddev2019.features.home.data.model.Type
import retrofit2.Response

interface HomeRepository {
    suspend fun getClothes(): Response<List<Cloth>>
    suspend fun getTypes(): Response<List<Type>>
}

class HomeRepositoryImpl(val api: ShopApi, val api2: ShopApi2): HomeRepository {
    override suspend fun getClothes(): Response<List<Cloth>> {
        return api.getClothes().await()
    }

    override suspend fun getTypes(): Response<List<Type>> {
        return api2.getTypes().await()
    }
}