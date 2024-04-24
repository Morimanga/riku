package io.morimanga.riku.data.map

import io.morimanga.riku.data.model.LocalComicsInfo
import io.morimanga.riku.domain.model.Comics

fun comicsMapToDomain(localComicsInfo: LocalComicsInfo) = Comics(
    localId = localComicsInfo.localId,
    remoteId = localComicsInfo.remoteId,
    addonId = localComicsInfo.addonId,
    name = localComicsInfo.name,
    poster = localComicsInfo.poster,
    lastReadChapter = localComicsInfo.lastReadChapter
)

fun comicsMapToData(comics: Comics) = LocalComicsInfo(
    localId = comics.localId,
    remoteId = comics.remoteId,
    addonId = comics.addonId,
    name = comics.name,
    poster = comics.poster,
    lastReadChapter = comics.lastReadChapter
)