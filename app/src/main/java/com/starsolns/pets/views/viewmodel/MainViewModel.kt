package com.starsolns.pets.views.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import com.starsolns.pets.views.model.Pet
import com.starsolns.pets.views.data.retrofit.RetrofitInstance
import com.starsolns.pets.views.data.room.PetsDatabase
import com.starsolns.pets.views.util.NotificationHelper
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

    private val pref: SharedPreferences? = application.getSharedPreferences("notify_pref", Context.MODE_PRIVATE)
    private val notify = pref?.getBoolean("notifications_id", true)

    fun getPets(){
        disposable.add(
            apiService.getPets()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Pet>>(){
                    override fun onSuccess(petsList: List<Pet>) {
                       //storeInRoom(petsList)
                        petsRetrieved(petsList)
                       if (notify == true){
                           NotificationHelper(getApplication()).createNotification()
                       }
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