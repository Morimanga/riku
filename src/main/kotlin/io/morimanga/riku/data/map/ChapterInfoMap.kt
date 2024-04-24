package io.morimanga.riku.data.map

import io.morimanga.riku.domain.model.ChapterInfo
import module.title.Chapter

fun chapterInfoMapToDomain(chapter: Chapter) = ChapterInfo(
    id = chapter.id,
    url = chapter.url,
    name = chapter.name,
    releaseData = chapter.releaseDate.toString()
)