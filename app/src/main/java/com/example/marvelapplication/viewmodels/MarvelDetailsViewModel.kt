package com.example.marvelapplication.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapplication.model.*
import com.example.marvelapplication.repository.MarvelRepository
import com.example.marvelapplication.utils.Constants.LIMIT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class MarvelDetailsViewModel @Inject
constructor(private val repository: MarvelRepository) : ViewModel() {

    private val responseData = MutableLiveData<MutableList<MyModelForAdapter>>()
    val marvelSeriesResponse: LiveData<MutableList<MyModelForAdapter>>
        get() = responseData

    private var totalNumberOfCharacters: Int = 999999999//we will make the first call either way


    fun getCharacters(id: Int, itemsLoaded: Int) = viewModelScope.launch {
        if (doIHaveMoreCharacters(itemsLoaded)) {
            repository.getMarvelCharacters(id, LIMIT, itemsLoaded).let {
                if (it.isSuccessful) {
                    it.body()?.data?.let { it1 -> setTotalNumberOfCharacters(it1?.total) }
                    responseData.postValue(changeDataToMyOwnModel(it.body()))
                } else {
                    Log.d("tag", "call error")
                }
            }
        }
    }

    private fun changeDataToMyOwnModel(body: MarvelCharacters?): MutableList<MyModelForAdapter> {
        var myCharModel: MutableList<MyModelForAdapter> = ArrayList()

        body?.data?.results.let {
            it?.forEach { data ->
                var dummy: MyModelForAdapter =
                    MyModelForAdapter(data.thumbnail.path, data.name, data.description)
                myCharModel.add(dummy)
            }
        }
        return myCharModel
    }

    private fun setTotalNumberOfCharacters(total: Int) {
        totalNumberOfCharacters = total
    }

     fun doIHaveMoreCharacters(position: Int) : Boolean{

        return position < totalNumberOfCharacters
    }

    fun getToastMessage(position: Int): String {
        if (doIHaveMoreCharacters(position)) {
            return "loading"
        }
        return "last item has already been render"
    }

}

