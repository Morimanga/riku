package io.morimanga.riku.presentation.model.eddons

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChapterImagesModel(
    val images: List<String>,
    @SerialName("all_pages")
    val allPages: Int
)