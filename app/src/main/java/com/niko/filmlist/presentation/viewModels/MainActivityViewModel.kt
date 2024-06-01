package com.niko.filmlist.presentation.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.niko.filmlist.data.repository.MovieRepositoryImpl
import com.niko.filmlist.domain.models.Film
import com.niko.filmlist.domain.models.MovieReponse
import com.niko.filmlist.domain.useCases.GetFilms
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivityViewModel() : ViewModel() {
    private var page = 1
    private val repository = MovieRepositoryImpl()
    private val getFilms = GetFilms(repository)
    private val compositeDisposable = CompositeDisposable()
    private val _filmList = MutableLiveData<List<Film>>()
    val filmList : LiveData<List<Film>>
        get() = _filmList
    private val _isLoading = MutableLiveData(false)
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    fun getFilmsList() {
        val loading = _isLoading.value
        if(loading!=null && loading)
            return
        val disposable = getFilms(page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _isLoading.value = true
            }
            .doAfterTerminate {
                _isLoading.value = false
            }
            .subscribe({
                val list = _filmList.value?.toMutableList() ?: mutableListOf()
                list.addAll(it.filmsList)
                _filmList.value = list
                page++
            },{
                Log.e("AUF","${it.message}")
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}