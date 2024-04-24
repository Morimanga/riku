package io.morimanga.riku.presentation.controller

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.morimanga.riku.data.repository.FoldersRepositoryImpl
import io.morimanga.riku.domain.model.Comics
import io.morimanga.riku.domain.model.FolderInfo
import io.morimanga.riku.presentation.model.folders.ComicsFolderModel
import io.morimanga.riku.presentation.model.folders.FolderModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class FoldersController(private val call: ApplicationCall) {
    private val repository = FoldersRepositoryImpl()

    suspend fun getFolders() = coroutineScope {
        val data = async { repository.getAllFolders() }.await()
            .map {
                FolderModel(
                    id = it.id,
                    name = it.name
                )
            }
        call.respond(data)
    }

    suspend fun getFolderContent(id: Int) = coroutineScope {
        val data = async { repository.getFolderComics(id) }.await()
            .map {
                ComicsFolderModel(
                    localId = it.localId,
                    remoteId = it.remoteId,
                    addonId = it.addonId,
                    name = it.name,
                    poster = it.poster,
                    lastReadChapter = it.lastReadChapter
                )
            }
        call.respond(data)
    }

    suspend fun editFolder() = coroutineScope {
        val remote = call.receive<FolderModel>()
        val data = FolderInfo(remote.id, remote.name)
        launch { repository.editFolder(data) }
    }

    suspend fun deleteFolder(id: Int) = coroutineScope {
        launch { repository.deleteFolder(id) }
    }

    suspend fun editComics(folderId: Int) = coroutineScope {
        val remote = call.receive<ComicsFolderModel>()
        val data = Comics(
            localId = remote.localId,
            remoteId = remote.remoteId,
            addonId = remote.addonId,
            name = remote.name,
            poster = remote.poster,
            lastReadChapter = remote.lastReadChapter
        )
        launch { repository.editComicsInfo(folderId, data) }
    }

    suspend fun deleteComics(comicsId: Int) = coroutineScope {
        launch { repository.deleteComics(comicsId) }
    }

}