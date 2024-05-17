package io.morimanga.riku.domain.service

import io.morimanga.riku.domain.model.Addon
import io.morimanga.riku.domain.model.ComicsInfo
import io.morimanga.riku.domain.model.ComicsList

interface AddonsService {
    suspend fun getAllGenres(addonId: Int): List<String>
    suspend fun getTitleInfo(remoteTitleId: String, addonId: Int): ComicsInfo
    suspend fun getLatestTitles(addonId: Int, page: Int): ComicsList
    suspend fun getTitleChapterImages(addonId: Int, chapterId: Int, remoteTitleId: String): List<String>
    suspend fun searchTitle(
        addonId: Int,
        page: Int,
        sortBy: Boolean,
        name: String?,
        years: List<Int>?,
        genres: List<String>?
    ): ComicsList

    fun getAllAddons(): List<Addon>

}