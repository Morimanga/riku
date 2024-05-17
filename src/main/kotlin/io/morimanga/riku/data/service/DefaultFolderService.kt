package io.morimanga.riku.data.service

import io.morimanga.riku.data.map.comicsMapToData
import io.morimanga.riku.data.map.comicsMapToDomain
import io.morimanga.riku.data.map.folderInfoMapToDomain
import io.morimanga.riku.data.source.FoldersSource
import io.morimanga.riku.domain.model.Comics
import io.morimanga.riku.domain.model.FolderInfo
import io.morimanga.riku.domain.service.FoldersService

class DefaultFolderService : FoldersService {
    private val source = FoldersSource()

    override suspend fun getAllFolders(): List<FolderInfo> {
        val data = source.getAllFolders()
        return data.map { folder -> folderInfoMapToDomain(folder) }
    }

    override suspend fun editFolder(folder: FolderInfo) {
        val id = if (folder.id == 0) null else folder.id
        source.editFolder(
            id,
            folder.name
        )
    }

    override suspend fun deleteFolder(folderId: Int) {
        source.deleteFolder(folderId)
    }

    override suspend fun getFolderComics(folderId: Int): List<Comics> {
        val data = source.getFolderComics(folderId)
        return data.map { comicsMapToDomain(it) }
    }

    override suspend fun editComicsInfo(folderId: Int, comics: Comics) {
        source.editComics(
            folderId,
            comicsMapToData(comics)
        )
    }

    override suspend fun deleteComics(localId: Int) {
        source.deleteComics(localId)
    }
}