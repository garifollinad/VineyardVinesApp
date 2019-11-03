package com.example.androiddev2019.features.home.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Cloth (
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("shortDescription") val shortDescription : String,
    @SerializedName("fullDescription") val fullDescription: String,
    @SerializedName("date") val date: String,
    @SerializedName("pictureUrl") val pictureUrl: String
): Serializable
