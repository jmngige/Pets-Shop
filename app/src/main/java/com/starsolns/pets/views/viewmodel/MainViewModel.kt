package com.starsolns.pets.views.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.starsolns.pets.views.Pet
import com.starsolns.pets.views.retrofit.Api
import com.starsolns.pets.views.retrofit.RetrofitInstance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel: ViewModel() {

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
                        isLoading.value = true
                        isError.value = false
                        pets.value = petsList
                        isLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        isLoading.value = false
                        isError.value = true
                    }

                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}