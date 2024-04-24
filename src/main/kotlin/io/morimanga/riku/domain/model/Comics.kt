package io.morimanga.riku.domain.model

// TODO: RENAME
data class Comics(
    val localId: Int,
    val remoteId: String,
    val addonId: Int,
    val name: String,
    val poster: String,
    val lastReadChapter: Int
)