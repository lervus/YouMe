package com.cc221020.ccl3.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cc221020.ccl3.R

@Entity(tableName = "User")
data class User(
    @PrimaryKey val id: Int = 0,
    val xp: Int = 0,
    val selectedSkin: Int? = null,
    val wellBeingScore: Int = 0,
    val waterGoal: Float = 2f,
    val goalsCompleted: Int = 0,
    val foodScore: Float = 0f,
    val dailyComplete: Boolean = false,
    val waterProgress: Float = 0f,
    val currentDaily: Int = R.string.challenge1,
    val lastTimeUpdate: String = "",
    val xpGain: Int = 0
)