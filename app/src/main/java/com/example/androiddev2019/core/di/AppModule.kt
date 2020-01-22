package com.example.androiddev2019.core.di

import android.content.Context
import android.util.Log
import com.example.androiddev2019.BuildConfig
import com.example.androiddev2019.features.home.data.api.ShopApi
import com.example.androiddev2019.features.home.data.api.ShopApi2
import com.example.androiddev2019.features.home.data.repository.HomeRepository
import com.example.androiddev2019.features.home.data.repository.HomeRepositoryImpl
import com.example.androiddev2019.features.home.presentation.home.HomeViewModel
import com.example.androiddev2019.features.menu.MenuActivity
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {

    single { createHttpClient(androidContext())}
    single { createApiService(get())}
    single { createApiService2(get())}
    single { HomeRepositoryImpl(get(), get()) as HomeRepository }

}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}

val mainModule = module {

//    scope(named<MenuActivity>()) {
//        scoped { (params: MenuActivity) -> MainNavigationController(params) }
//    }
}

fun createHttpClient(context: Context): OkHttpClient {
    val cacheSize = (5 * 1024 * 1024).toLong()
    val myCache = Cache(context.cacheDir, cacheSize)
    val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d("OkHttp", message) })
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)

    if (BuildConfig.DEBUG) {
        okHttpClient
            .cache(myCache)
            .addInterceptor(interceptor)
    }
    return okHttpClient.build()
}



fun createApiService(okHttpClient: OkHttpClient):ShopApi {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.SHOP_API)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
        .build()
        .create(ShopApi::class.java)
}

fun createApiService2(okHttpClient: OkHttpClient): ShopApi2 {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.SHOP_API2)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
        .build()
        .create(ShopApi2::class.java)
}


val appModules = listOf(networkModule, viewModelModule,mainModule)