package com.burakhkahraman.itunesnavigation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burakhkahraman.itunessearch.network.Network
import com.burakhkahraman.itunessearch.network.response.Result
import com.burakhkahraman.itunessearch.network.response.SearchResultResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ITunesListViewModel(): ViewModel() {
    private val _results = MutableLiveData<List<Result>>()
    val results: LiveData<List<Result>> get() = _results

    private val service = Network.service

    fun searchByTerm(term: String) {
        viewModelScope.launch {
            service.searchByTerm(term).enqueue(object : Callback<SearchResultResponse> {
                override fun onResponse(
                    call: Call<SearchResultResponse>,
                    response: Response<SearchResultResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        _results.value = response.body()?.results
                    } else {
                        _results.value = emptyList()
                    }
                }

                override fun onFailure(call: Call<SearchResultResponse>, t: Throwable) {
                    _results.value = emptyList()
                }
            })
        }
    }
}