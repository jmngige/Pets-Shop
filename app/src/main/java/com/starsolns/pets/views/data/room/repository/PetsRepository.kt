package com.starsolns.pets.views.data.room

import com.starsolns.pets.views.model.Pet

class PetsRepository(private val petsDao: PetDao) {


    suspend fun insert(pet: Pet){
        petsDao.insert(pet)
    }

    suspend fun getPet(petId: Int){
        petsDao.getPet(petId)
    }

    suspend fun deleteCache(){
        petsDao.delete()
    }
}