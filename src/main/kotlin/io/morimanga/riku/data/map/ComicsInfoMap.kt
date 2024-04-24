package io.morimanga.riku.data.map

import io.morimanga.riku.domain.model.ComicsInfo
import module.title.Title

fun comicsInfoMapToDomain(title: Title) = ComicsInfo(
    remoteId = title.id,
    name = title.name,
    poster = title.posterUrl,
    releaseDate = title.release.toString(),
    description = title.description,
    author = title.author,
    genres = title.genres,
    chapters = title.chapters.map(::chapterInfoMapToDomain)
)