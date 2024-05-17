package io.morimanga.riku.data.source

import io.morimanga.riku.data.dbQuery
import io.morimanga.riku.data.model.Folder
import io.morimanga.riku.data.model.LocalComicsInfo
import io.morimanga.riku.data.table.ComicsFoldersTable
import io.morimanga.riku.data.table.ComicsTable
import io.morimanga.riku.data.table.FoldersTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class FoldersSource {

    suspend fun getAllFolders(): List<Folder> {
        val data = dbQuery {
            FoldersTable.selectAll().map(::resultToFolder)
        }
        return data
    }

    suspend fun editFolder(folderId: Int?, folderName: String) {
        dbQuery {
            FoldersTable.upsert { table ->
                folderId?.let { table[id] = it }
                table[name] = folderName
            }
        }
    }

    suspend fun deleteFolder(folderId: Int) {
        dbQuery {
            FoldersTable.deleteWhere { id eq folderId }
        }
    }

    suspend fun getFolderComics(id: Int): List<LocalComicsInfo> {
        val data = dbQuery {
            ComicsFoldersTable
                .join(ComicsTable, JoinType.INNER, ComicsTable.id, ComicsFoldersTable.comicsLocalId)
                .selectAll()
                .where {
                    ComicsFoldersTable.id eq id
                }
                .map(::resultToComics)
        }
        return data
    }

    suspend fun editComics(folderId: Int?, comics: LocalComicsInfo) {
        dbQuery {
            ComicsTable.upsert { table ->
                folderId?.let { table[id] = folderId }
                table[remoteId] = comics.remoteId
                table[addonId] = comics.addonId
                table[name] = comics.name
                table[poster] = comics.poster
                table[lastReadChapter] = comics.lastReadChapter
            }
            ComicsFoldersTable.upsert {table ->
                table[comicsLocalId] = comics.localId
                folderId?.let { id -> table[foldersLocalId] = id }
            }
        }
    }

    suspend fun deleteComics(comicsId: Int) {
        dbQuery {
            ComicsTable.deleteWhere { id eq comicsId }
        }
    }

    private fun resultToFolder(result: ResultRow) = Folder(
        id = result[FoldersTable.id].value,
        name = result[FoldersTable.name]
    )

    private fun resultToComics(result: ResultRow) = LocalComicsInfo(
        localId = result[ComicsTable.id].value,
        remoteId = result[ComicsTable.remoteId],
        addonId = result[ComicsTable.addonId],
        name = result[ComicsTable.name],
        poster = result[ComicsTable.poster],
        lastReadChapter = result[ComicsTable.lastReadChapter]
    )
}