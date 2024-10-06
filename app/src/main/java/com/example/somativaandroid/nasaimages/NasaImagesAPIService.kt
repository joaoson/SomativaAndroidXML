package com.example.somativaandroid.nasaimages

import com.example.somativaandroid.recyclerviewpackage.Planet
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaImagesAPIService {
    @GET("search")
    fun searchImages(@Query("q") query: String): Call<NasaResponse>
}