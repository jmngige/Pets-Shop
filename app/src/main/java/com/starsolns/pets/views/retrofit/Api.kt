package com.starsolns.pets.views.retrofit

import com.starsolns.pets.views.Pet
import io.reactivex.Single
import retrofit2.http.GET

interface Api {

    @GET("/DevTides/DogsApi/master/dogs.json")
    fun getPets(): Single<List<Pet>>
}