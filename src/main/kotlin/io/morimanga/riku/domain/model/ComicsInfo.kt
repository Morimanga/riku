package io.morimanga.riku.domain.model

data class ComicsInfo(
    val remoteId: String,
    val name: String,
    val poster: String,
    val releaseDate: String, // YY-MM-DD
    val description: String,
    val author: String,
    val genres: List<String>,
    val chapters: List<ChapterInfo>
)