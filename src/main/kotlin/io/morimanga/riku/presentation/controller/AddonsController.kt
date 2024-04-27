package io.morimanga.riku.presentation.controller

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.morimanga.riku.data.repository.AddonsRepositoryImpl
import io.morimanga.riku.presentation.model.eddons.*
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class AddonsController(private val call: ApplicationCall) {
    private val repository = AddonsRepositoryImpl()

    suspend fun getGenres(addonId: Int) = coroutineScope {
        val genres = async { repository.getAllGenres(addonId) }.await()
        call.respond(genres)
    }

    suspend fun getAllAddons() = coroutineScope {
        val data = async { repository.getAllAddons() }.await()
            .map {
                AddonsModel(
                    id = it.id,
                    name = it.name,
                    image = it.poster,
                    description = it.description
                )
            }
        call.respond(data)
    }

    suspend fun getTitleById(remoteTitleId: String, addonId: Int) = coroutineScope {
        val data = async { repository.getTitleInfo(remoteTitleId, addonId) }.await()
        val buffer = InfoComicsModel(
            remoteId = data.remoteId,
            addonsId = addonId,
            poster = data.poster,
            name = data.name,
            description = data.description,
            genres = data.genres,
            chapters = data.chapters.map {
                InfoChapterModel(
                    id = it.id,
                    name = it.name,
                    releaseDate = it.releaseData
                )
            }
        )
        call.respond(buffer)
    }

    suspend fun getLatestTitles(page: Int, addonId: Int) = coroutineScope {
        val data = async { repository.getLatestTitles(addonId, page) }.await()
        val buffer = ListComicsModel(
            content = data.comics.map {
                ComicsModel(
                    remoteId = it.remoteId,
                    name = it.name,
                    poster = it.poster,
                    description = it.description,
                    genres = it.genres
                )
            },
            allPage = data.allPages
        )
        call.respond(buffer)
    }

    suspend fun searchTitle(
        addonId: Int,
        page: Int,
        sortType: Boolean,
        name: String?,
        years: List<Int>?,
        genres: List<String>?
    ) = coroutineScope {
        val data = async {
            repository.searchTitle(
                addonId, page,
                sortType, name,
                years, genres
            )
        }.await()
        val buffer = ListComicsModel(
            content = data.comics.map {
                ComicsModel(
                    remoteId = it.remoteId,
                    name = it.name,
                    poster = it.poster,
                    description = it.description,
                    genres = it.genres
                )
            },
            allPage = data.allPages
        )
        call.respond(buffer)
    }

    suspend fun getTitleChapterById(addonId: Int, chapterId: Int, remoteTitleId: String) = coroutineScope {
        val data = async { repository.getTitleChapterImages(addonId, chapterId, remoteTitleId) }.await()
        val buffer = ChapterImagesModel(
            images = data,
            allPages = data.size
        )
        call.respond(buffer)
    }

}