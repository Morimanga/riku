package io.morimanga.riku.domain.repository

import io.morimanga.riku.domain.model.Comics
import io.morimanga.riku.domain.model.FolderInfo

interface FoldersRepository {
    suspend fun getAllFolders(): List<FolderInfo>
    suspend fun editFolder(folder: FolderInfo)
    suspend fun deleteFolder(folderId: Int)
    suspend fun getFolderComics(folderId: Int): List<Comics>
    suspend fun editComicsInfo(folderId: Int, comics: Comics)
    suspend fun deleteComics(localId: Int)
}