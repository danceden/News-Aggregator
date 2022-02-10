package com.example.homework_project

import retrofit2.Call
import retrofit2.http.*

//https://rusha-news-server.herokuapp.com/

interface MainApi {
    @GET(".")
    fun getItems(): Call<List<New>>

    @POST("add")
    fun postItems(@Body addNew: AddNew): Call<Unit>

    @DELETE("{newID}")
    fun deleteItems(@Path("newID") newID : Int): Call<Unit>

    @PUT("{newID}")
    fun editItems(@Path("newID")newID: Int, @Body addNew: AddNew): Call<Unit>



}

