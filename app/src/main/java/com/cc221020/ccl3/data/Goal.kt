package com.cc221020.ccl3.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Goal")
data class Goal(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var title: String,
    var completed: Boolean
)