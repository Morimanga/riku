package io.morimanga.riku.domain.model

data class ComicsList(
    val allPages: Int,
    val comics: List<SimpleComicsInfo>
)