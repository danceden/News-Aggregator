package com.example.homework_project

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceLocator {
    fun mainApi(): MainApi {
        val retrofit = Retrofit.Builder()
//            .baseUrl("https://rusha-news-server.herokuapp.com/") // наконце всегда оставлять "/" !!!
            .baseUrl("https://8cd4-31-173-120-52.ngrok.io/") // наконце всегда оставлять "/" !!!
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build();
        return retrofit.create(MainApi::class.java)
    }
}