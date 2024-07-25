package com.burakhkahraman.itunessearch.network

import com.burakhkahraman.itunessearch.network.response.SearchResultResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("/search")
    fun searchByTerm(@Query("term") term: String): Call<SearchResultResponse>
}