package io.morimanga.riku.data.repository

import io.morimanga.riku.data.map.comicsMapToData
import io.morimanga.riku.data.map.comicsMapToDomain
import io.morimanga.riku.data.map.folderInfoMapToData
import io.morimanga.riku.data.map.folderInfoMapToDomain
import io.morimanga.riku.data.source.FoldersSource
import io.morimanga.riku.domain.model.Comics
import io.morimanga.riku.domain.model.FolderInfo
import io.morimanga.riku.domain.repository.FoldersRepository

class FoldersRepositoryImpl : FoldersRepository {
    private val source = FoldersSource()

    override suspend fun getAllFolders(): List<FolderInfo> {
        val data = source.getAllFolders()
        return data.map { folder -> folderInfoMapToDomain(folder) }
    }

    override suspend fun editFolder(folder: FolderInfo) {
        source.editFolder(
            folderInfoMapToData(folder)
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