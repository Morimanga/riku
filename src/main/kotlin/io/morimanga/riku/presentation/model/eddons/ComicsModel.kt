package io.morimanga.riku.presentation.model.eddons

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ComicsModel(
    @SerialName("remote_id")
    val remoteId: String,
    val poster: String,
    val name: String,
    val description: String,
    val genres: List<String>
)