package com.example.taskrfrv

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    companion object{
        var BASE_URL:String = "https://f91.in/foods91/api/"
        var retrofit:Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @GET("getRestaurents")
    fun getRestaurents():Call<ResponseBody>

    @GET("getshopgallery")
    fun getShopGallery():Call<GalleryResponseBody>

}