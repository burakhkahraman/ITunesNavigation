package com.burakhkahraman.itunessearch.network.response

import java.io.Serializable


data class Result(
    val artistName: String,
    val collectionName: String?,
    val artworkUrl100: String,
    val collectionPrice: Double,
    val currency: String,
    val trackName: String,
    val releaseDate: String
): Serializable
