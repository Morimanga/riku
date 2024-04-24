package io.morimanga.riku.presentation.model.eddons

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InfoComicsModel(
    @SerialName("remote_id")
    val remoteId: String,
    @SerialName("addons_id")
    val addonsId: Int,
    val poster: String,
    val name: String,
    val description: String,
    val genres: List<String>,
    val chapters: List<InfoChapterModel>
)