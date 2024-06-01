package com.niko.filmlist.presentation.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.niko.filmlist.data.repository.MovieRepositoryImpl
import com.niko.filmlist.domain.models.Review
import com.niko.filmlist.domain.models.Trailer
import com.niko.filmlist.domain.useCases.GetReviews
import com.niko.filmlist.domain.useCases.GetTrailers
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class FilmDetailViewModel() : ViewModel() {
    private val repositoryImpl = MovieRepositoryImpl()
    private val getTrailers = GetTrailers(repositoryImpl)
    private val compositeDisposable = CompositeDisposable()
    private val getReviewList = GetReviews(repositoryImpl)
    private var page = 1;
    private var isLoading = false
    private val _trailersList = MutableLiveData<List<Trailer>>()
    val trailersList: LiveData<List<Trailer>>
        get() = _trailersList
    private val _reviewList = MutableLiveData<List<Review>>()
    val reviewList: LiveData<List<Review>>
        get() = _reviewList

    fun getMovieTrailers(filmId: Int) {
        val disposable = getTrailers(filmId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _trailersList.value = it.videos?.trailers
                    Log.e("AUF", "$it")
                }, {
                    Log.e("AUF", "${it.message}")
                }
            )
        compositeDisposable.add(disposable)
    }

    fun getReviews(id: Int) {
        if (isLoading)
            return
        val disposable =
            getReviewList(id, page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    it.reviewList
                }
                .doOnSubscribe {
                    isLoading = true
                }
                .doAfterTerminate {
                    isLoading = false
                }
                .subscribe({
                    Log.e("AUF", "$it")
                    val list = _reviewList.value?.toMutableList() ?: mutableListOf()
                    list.addAll(it)
                    _reviewList.value = list
                    page++
                }, {
                    Log.e("AUF", "${it.message}")
                })
        compositeDisposable.add(disposable)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}