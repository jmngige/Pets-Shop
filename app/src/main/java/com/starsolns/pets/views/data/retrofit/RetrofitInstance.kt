package com.starsolns.pets.views.data.retrofit

import com.starsolns.pets.views.model.Pet
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInstance {

    private val baseUrl = "https://raw.githubusercontent.com/DevTides/DogsApi/master/dogs.json/"

    private val api = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(Api::class.java)

    fun getPets(): Single<List<Pet>>{
        return api.getPets()
    }

}