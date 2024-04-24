package io.morimanga.riku.data.map

import io.morimanga.riku.data.model.Folder
import io.morimanga.riku.domain.model.FolderInfo

fun folderInfoMapToDomain(folder: Folder) = FolderInfo(
    id = folder.id,
    name = folder.name
)

fun folderInfoMapToData(folderInfo: FolderInfo) = Folder(
    id = folderInfo.id,
    name = folderInfo.name
)