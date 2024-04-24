package io.morimanga.riku.data.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object ComicsFoldersTable : IntIdTable() {
    val comicsLocalId = reference("comics", ComicsTable.id, onDelete = ReferenceOption.CASCADE)
    val foldersLocalId = reference("folders", FoldersTable.id, onDelete = ReferenceOption.CASCADE)
}