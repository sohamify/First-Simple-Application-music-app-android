package com.example.music_application

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Headers

interface ApiInterface {
    @Headers("x-rapidapi-key: bfbde395c2msh38af02276c38ef8p14491cjsnbc763622fec3",
    "x-rapidapi-host:deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q") query: String): Call<MyData>

}