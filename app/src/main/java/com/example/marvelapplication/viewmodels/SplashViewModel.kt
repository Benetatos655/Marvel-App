package com.example.marvelapplication.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapplication.model.MarvelSeries
import com.example.marvelapplication.model.Result
import com.example.marvelapplication.repository.MarvelRepository
import com.example.marvelapplication.utils.Constants.BASE_YEAR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject
constructor(private val repository: MarvelRepository) : ViewModel() {

    private val responseData = MutableLiveData<List<Result>>()
    val marvelSeriesResponse : LiveData<List<Result>>
    get() = responseData

    init {
        getMarvelMovies()
    }

    private fun getMarvelMovies()=viewModelScope.launch {
        repository.getMarvelSeries().let {
            if (it.isSuccessful){
            responseData.postValue(it.body()?.let { it1 -> dataFiltering(it1) })
            }else{
                Log.d("tag","call error")
            }
        }
    }

    private fun dataFiltering(body: MarvelSeries): List<Result> {

        return body.data.results.filter {
            it.startYear==BASE_YEAR
        } as MutableList<Result>
    }


}