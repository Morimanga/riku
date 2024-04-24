package io.morimanga.riku.presentation.model.eddons

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InfoChapterModel(
    val id: Int,
    val name: String,
    @SerialName("release_date")
    val releaseDate: String
)