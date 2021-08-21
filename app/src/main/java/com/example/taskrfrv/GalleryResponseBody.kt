package com.example.taskrfrv

import com.google.gson.annotations.SerializedName

data class GalleryResponseBody(
    @SerializedName("action") val action: String,
    @SerializedName("baseurl") val baseurl: String,
    @SerializedName("gallery") val gallery: List<Gallery>
)