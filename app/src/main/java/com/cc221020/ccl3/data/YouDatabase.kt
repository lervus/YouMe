package com.cc221020.ccl3.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Goal::class, TodoItem::class], version = 1)
abstract class YouDatabase : RoomDatabase() {
    abstract val goalDao: GoalDao
    abstract val todoDao: TodoDao
    abstract val userDao: UserDao
}