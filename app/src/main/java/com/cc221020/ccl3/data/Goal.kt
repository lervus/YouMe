package com.cc221020.ccl3.data

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "Goals")
data class Goal(
    @PrimaryKey val id : Int = 0,
    var title : String,
    var completed : Boolean
)