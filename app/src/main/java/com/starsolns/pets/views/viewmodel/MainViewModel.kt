package com.starsolns.pets.views.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.starsolns.pets.views.model.Pet
import com.starsolns.pets.views.data.retrofit.RetrofitInstance
import com.starsolns.pets.views.data.room.PetsDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application)  {

    val pets = MutableLiveData<List<Pet>>()
     val isLoading = MutableLiveData<Boolean>()
     val isError = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()
    private val apiService = RetrofitInstance()



    fun getPets(){
        disposable.add(
            apiService.getPets()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Pet>>(){
                    override fun onSuccess(petsList: List<Pet>) {
                       //storeInRoom(petsList)
                        petsRetrieved(petsList)
                    }

                    override fun onError(e: Throwable) {
                        isLoading.value = false
                        isError.value = true
                    }

                })
        )

    }

    private fun storeInRoom(petsList: List<Pet>) {
        val dao = PetsDatabase.getDatabase(getApplication()).petDao()
        viewModelScope.launch {
            dao.delete()
            val res = dao.insert(*petsList.toTypedArray())
            var i = 0
            while ( i < petsList.size){
                //petsList[i].uuid = res[i].toInt()
            }

            petsRetrieved(petsList)
        }
    }

    private fun petsRetrieved(petsList: List<Pet>){
        isLoading.value = true
        isError.value = false
        pets.value = petsList
        isLoading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}