package io.morimanga.riku.domain.model

data class SimpleComicsInfo(
    val remoteId: String,
    val name: String,
    val poster: String,
    val description: String,
    val genres: List<String>
)