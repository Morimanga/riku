package io.morimanga.riku.data.repository

import io.morimanga.riku.data.map.addonMapToDomain
import io.morimanga.riku.data.map.comicsInfoMapToDomain
import io.morimanga.riku.data.map.comicsListMapToDomain
import io.morimanga.riku.data.source.AddonsSource
import io.morimanga.riku.domain.model.Addon
import io.morimanga.riku.domain.model.ComicsInfo
import io.morimanga.riku.domain.model.ComicsList
import io.morimanga.riku.domain.repository.AddonsRepository
import kotlinx.coroutines.withContext

class AddonsRepositoryImpl : AddonsRepository {
    private val source = AddonsSource()

    override suspend fun getTitleInfo(remoteTitleId: String, addonId: Int): ComicsInfo {
        val data = source.getTitleInfo(addonId, remoteTitleId)
        return comicsInfoMapToDomain(data)
    }

    override suspend fun getLatestTitles(addonId: Int, page: Int): ComicsList {
        val data = source.getLatestTitles(addonId, page)
        return comicsListMapToDomain(data)
    }

    override suspend fun getTitleChapterImages(addonId: Int, chapterId: Int, remoteTitleId: String): List<String> {
        return source.getTitleChapter(addonId, chapterId, remoteTitleId)
    }

    override suspend fun searchTitle(
        addonId: Int,
        page: Int,
        sortBy: Boolean,
        name: String?,
        years: List<Int>?,
        genres: List<String>?
    ): ComicsList {
        val data = source.searchTitle(addonId, sortBy, page, name, years, genres)
        return comicsListMapToDomain(data)
    }

    override fun getAllAddons(): List<Addon> {
        val data = source.getAllExtensions()
        val buffer = arrayListOf<Addon>()
        data.forEachIndexed { id, extension -> buffer.add(addonMapToDomain(id, extension)) }
        return buffer
    }
}