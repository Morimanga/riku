package io.morimanga.riku.presentation.model.folders

import kotlinx.serialization.Serializable

@Serializable
data class FolderModel(
    val id: Int,
    val name: String
)