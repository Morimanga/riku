package io.morimanga.riku.config

import io.morimanga.riku.data.table.ComicsFoldersTable
import io.morimanga.riku.data.table.ComicsTable
import io.morimanga.riku.data.table.FoldersTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction

fun database() {
    initDatabase()
    initTables()
}

private fun initDatabase() {
    val db = Database.connect("jdbc:sqlite:./riku_data.db", "org.sqlite.JDBC")
    TransactionManager.defaultDatabase = db
}

private fun initTables() {
    transaction {
        SchemaUtils.create(
            ComicsFoldersTable,
            ComicsTable,
            FoldersTable
        )
    }
}