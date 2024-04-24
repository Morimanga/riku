package io.morimanga.riku.presentation.model.eddons

import kotlinx.serialization.Serializable

@Serializable
data class AddonsModel(
    val id: Int,
    val name: String,
    val image: String,
    val description: String
)