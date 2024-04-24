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

    // Rewrite this
    suspend fun editFolder(folder: Folder) {
        val buffer = if (folder.id == 0) null else folder.id
        dbQuery {
            FoldersTable.upsert { table ->
                buffer?.let { table[id] = buffer }
                table[name] = folder.name
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

    // Rewrite this
    suspend fun editComics(folderId: Int, comics: LocalComicsInfo) {
        val buffer = if (comics.localId == 0) null else  comics.localId

        dbQuery {
            ComicsTable.upsert { table ->
                // If null, just add. Else
                buffer?.let { localId ->
                    table[id] = localId
                }
                table[remoteId] = comics.remoteId
                table[addonId] = comics.addonId
                table[name] = comics.name
                table[poster] = comics.poster
                table[lastReadChapter] = comics.lastReadChapter
            }
            ComicsFoldersTable.upsert {
                it[comicsLocalId] = comics.localId
                it[foldersLocalId] = folderId
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