package com.cc221020.ccl3.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert
    suspend fun insertTodoItem(item: TodoItem)

    @Update
    suspend fun updateTodoItem(item: TodoItem)

    @Delete
    suspend fun deleteTodoItem(item: TodoItem)

    @Query("SELECT * FROM TodoItems WHERE goalId = :goalId")
    fun getTodoItem(goalId: Int): Flow<List<TodoItem>>

    @Query("DELETE FROM TodoItems")
    suspend fun deleteAll()
}