package io.morimanga.riku.domain.model

data class ChapterInfo(
    val id: Int,
    val url: String,
    val name: String,
    val releaseData: String // YY-MM-DD
)