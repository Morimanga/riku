package io.morimanga.riku.data.table

import org.jetbrains.exposed.dao.id.IntIdTable

object FoldersTable : IntIdTable() {
    val name = varchar("name", Int.MAX_VALUE)
}