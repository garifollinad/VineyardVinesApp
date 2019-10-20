package com.example.androiddev2019.core.model

import java.io.Serializable

data class Cloth (
    var name : String,
    var shortDescription: String,
    var fullDescription: String,
    var date: String,
    var pictureUrl: String
):Serializable