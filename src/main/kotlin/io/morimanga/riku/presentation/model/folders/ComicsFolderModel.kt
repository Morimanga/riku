package io.morimanga.riku.presentation.model.folders

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ComicsFolderModel(
    @SerialName("local_id")
    val localId: Int,
    @SerialName("remote_id")
    val remoteId: String,
    @SerialName("addon_id")
    val addonId: Int,
    val name: String,
    val poster: String,
    @SerialName("last_read_chapter")
    val lastReadChapter: Int
)