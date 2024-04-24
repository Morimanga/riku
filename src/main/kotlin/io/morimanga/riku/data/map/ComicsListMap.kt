package io.morimanga.riku.data.map

import io.morimanga.riku.domain.model.ComicsList
import module.title.TitleList

fun comicsListMapToDomain(titleList: TitleList) = ComicsList(
    allPages = titleList.allPages,
    comics = titleList.titles.map(::simpleComicsInfoToDomain)
)