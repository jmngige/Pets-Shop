package com.starsolns.pets.views.data.retrofit

import com.starsolns.pets.views.model.Pet
import io.reactivex.Single
import retrofit2.http.GET

interface Api {

    @GET("/DevTides/DogsApi/master/dogs.json")
    fun getPets(): Single<List<Pet>>
}