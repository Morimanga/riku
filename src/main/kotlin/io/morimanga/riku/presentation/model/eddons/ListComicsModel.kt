package io.morimanga.riku.presentation.model.eddons

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListComicsModel(
    val content: List<ComicsModel>,
    @SerialName("all_page")
    val allPage: Int
)