package io.morimanga.riku.data.table

import org.jetbrains.exposed.dao.id.IntIdTable

object ComicsTable : IntIdTable() {
    val remoteId = varchar("remote_id", Int.MAX_VALUE)
    val addonId = integer("addon_id")
    val name = varchar("name", Int.MAX_VALUE)
    val poster = varchar("poster", Int.MAX_VALUE)
    val lastReadChapter = integer("last_read_chapter")
}