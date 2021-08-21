package com.example.taskrfrv

import com.google.gson.annotations.SerializedName

data class ResponseBody(
    @SerializedName("action")   val action: String,
    @SerializedName("baseurl")  val baseurl: String,
    @SerializedName("restaurents") val restaurents: List<Restaurent>
    )