package io.morimanga.riku.data.map

import io.morimanga.riku.domain.model.SimpleComicsInfo
import module.title.SimpleTitleInfo

fun simpleComicsInfoToDomain(simpleTitleInfo: SimpleTitleInfo) = SimpleComicsInfo(
    remoteId = simpleTitleInfo.remoteId,
    name = simpleTitleInfo.name,
    poster = simpleTitleInfo.poster,
    description = simpleTitleInfo.description,
    genres = simpleTitleInfo.genres
)