package com.example.network_library.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Movies(
    val id: String,
    val name: String = "",
    val shortDescription: String = "",
    val longDescription: String = "",
    val url: String = "",
    val imageUrl: String = ""
)
