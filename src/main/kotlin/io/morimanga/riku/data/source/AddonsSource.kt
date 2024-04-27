package io.morimanga.riku.data.source

import extensions.base.Extensions
import extensions.base.OrderType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import module.extension.ExtensionInfo
import module.title.Title
import module.title.TitleList

class AddonsSource {
    private val allExtensions = Extensions.entries.toList()

    fun getAllExtensions(): List<ExtensionInfo> {
        return allExtensions.map { it.instance.extensionInfo() }
    }

    suspend fun getTitleInfo(addonId: Int, remoteId: String): Title {
        return allExtensions[addonId].instance.getTitleInfo(remoteId)
    }

    suspend fun getTitleChapter(addonId: Int, chapterId: Int, remoteId: String): List<String> {
        return allExtensions[addonId].instance.getTitleChapter(chapterId, remoteId)
    }

    suspend fun getLatestTitles(addonId: Int, page: Int): TitleList {
        return allExtensions[addonId].instance.getLatestTitles(page)
    }

    suspend fun searchTitle(
        addonId: Int,
        sort: Boolean,
        page: Int,
        name: String?,
        years: List<Int>?,
        genres: List<String>?
    ): TitleList {
        val sortBy = if (sort) OrderType.ASCENDING else OrderType.DESCENDING
        return allExtensions[addonId].instance.search(page, sortBy, name, genres, years)
    }

    suspend fun getAllGenres(addonId: Int): List<String> {
        return allExtensions[addonId].instance.getGenres()
    }
}