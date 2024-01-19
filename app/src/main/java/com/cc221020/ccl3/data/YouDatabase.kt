package com.cc221020.ccl3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Goal::class, TodoItem::class], version = 1)
abstract class YouDatabase : RoomDatabase() {
    abstract val goalDao: GoalDao
    abstract val todoDao: TodoDao
/*
    companion object {
        private const val DATABASE_NAME = "You.db"

        fun buildDatabase(context: Context): YouDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                YouDatabase::class.java,
                DATABASE_NAME
            )
                .addMigrations(MIGRATION_1_2)
                .build()
        }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
               // database.execSQL("ALTER TABLE Goal ADD COLUMN new_column_name TEXT")
            }
        }
    }
    */
}