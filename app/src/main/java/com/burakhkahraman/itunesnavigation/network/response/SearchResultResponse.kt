package com.burakhkahraman.itunessearch.network.response

import kotlinx.serialization.Serializable

//@Serializable
data class SearchResultResponse(val resultCount: Int, val results: List<Result>)