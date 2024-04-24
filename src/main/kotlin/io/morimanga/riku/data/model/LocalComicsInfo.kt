package io.morimanga.riku.data.model

data class LocalComicsInfo(
    val localId: Int,
    val remoteId: String,
    val addonId: Int,
    val name: String,
    val poster: String,
    val lastReadChapter: Int
)