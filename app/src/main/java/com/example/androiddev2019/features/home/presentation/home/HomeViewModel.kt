package com.example.androiddev2019.features.home.presentation.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.androiddev2019.core.BaseViewModel
import com.example.androiddev2019.features.home.data.model.Cloth
import com.example.androiddev2019.features.home.data.model.Type
import com.example.androiddev2019.features.home.data.repository.HomeRepository
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class HomeViewModel(val repository: HomeRepository): BaseViewModel(), CoroutineScope {
    val liveData = MutableLiveData<Result>()
    override val coroutineContext: CoroutineContext
        get() = SupervisorJob()

    fun getClothes() {
        liveData.value = Result.ShowLoading
        uiScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.getClothes()
                }

                liveData.value = Result.HideLoading
                if (response.isSuccessful) {

                    response.body()?.let { list ->
                        liveData.value = Result.Clothes(list as ArrayList<Cloth>)
                    }

                } else {
                    val error = response.errorBody()?.string()
                    Log.d("my_response", error)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                liveData.value =
                    Result.Error(e.localizedMessage ?: "error")
                liveData.value = Result.HideLoading
            }
        }
    }

    fun getTypes() {
        liveData.value = Result.ShowLoading
        uiScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.getTypes()
                }

                liveData.value = Result.HideLoading
                if (response.isSuccessful) {

                    response.body()?.let { list ->
                        liveData.value = Result.Types(list as ArrayList<Type>)
                        Log.d("my_dinara", list.toString())
                    }

                } else {
                    val error = response.errorBody()?.string()
                    Log.d("my_response", error)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                liveData.value =
                    Result.Error(e.localizedMessage ?: "error")
                liveData.value = Result.HideLoading
            }
        }
    }

    sealed class Result {
        object ShowLoading : Result()
        object HideLoading : Result()
        data class Clothes(val clothList: ArrayList<Cloth>) : Result()
        data class Types(val typeList: ArrayList<Type>) : Result()
        data class Error(val error: String) : Result()
    }
}