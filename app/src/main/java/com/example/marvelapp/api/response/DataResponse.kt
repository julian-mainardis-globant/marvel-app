package com.example.marvelapp.api.response

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("data")
    val data: ResultResponse
)
