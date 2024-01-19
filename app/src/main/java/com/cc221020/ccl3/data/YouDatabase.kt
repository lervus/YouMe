package com.cc221020.ccl3.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Goal::class, TodoItem::class, User::class], version = 2)
abstract class YouDatabase : RoomDatabase() {
    abstract val goalDao: GoalDao
    abstract val todoDao: TodoDao
    abstract val userDao: UserDao

    companion object {
        private const val FROM_VERSION = 1
        private const val TO_VERSION = 2

        val migration1to2: Migration = object : Migration(FROM_VERSION, TO_VERSION) {
            override fun migrate(database: SupportSQLiteDatabase) {
            }
        }
    }
}
