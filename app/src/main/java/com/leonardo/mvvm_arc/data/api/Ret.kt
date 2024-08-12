package com.leonardo.mvvm_arc.data.api

import com.leonardo.mvvm_arc.data.model.LiveModelo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Ret {

    //https://s3.amazonaws.com/api.ocanha.com/lista-lives.json
    @GET("api.ocanha.com/lista-lives.json")
    fun getAllLives(): Call<List<LiveModelo>>

    companion object{
        private val retrofitService: Ret by lazy {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://s3.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(Ret::class.java)
        }

        fun getIntance(): Ret{
            return retrofitService
        }

    }



}