package com.example.androiddev2019.features.home.presentation.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.androiddev2019.core.BaseViewModel
import com.example.androiddev2019.features.home.data.model.Cloth
import com.example.androiddev2019.features.home.data.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class HomeViewModel(val repository: HomeRepository): BaseViewModel() {
    val liveData = MutableLiveData<Result>()

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
        data class Error(val error: String) : Result()
    }
}