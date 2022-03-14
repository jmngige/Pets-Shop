package com.starsolns.pets.views.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.starsolns.pets.views.data.room.PetsDatabase
import com.starsolns.pets.views.data.room.PetsRepository
import com.starsolns.pets.views.model.Pet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PetsRoomViewModel(application: Application): AndroidViewModel(application) {

    private var repository: PetsRepository
    private var petsDao = PetsDatabase.getDatabase(application).petDao()

    init {
        repository = PetsRepository(petsDao)
    }

    fun getPet(petId: Int){
        viewModelScope.launch {
            repository.getPet(petId)
        }
    }

    fun insert(pet: Pet){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(pet)
        }
    }

    fun deleteCache(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCache()
        }
    }

}