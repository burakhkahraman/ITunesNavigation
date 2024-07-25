package com.burakhkahraman.itunesnavigation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.burakhkahraman.itunessearch.network.response.Result

class ITunesDetailViewModel : ViewModel() {

    private val _result = MutableLiveData<com.burakhkahraman.itunessearch.network.response.Result>()
    val result: LiveData<com.burakhkahraman.itunessearch.network.response.Result> get() = _result

    fun setResult(result: Result) {
        _result.value = result

    }
}