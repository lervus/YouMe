package com.cc221020.ccl3

import com.cc221020.ccl3.data.Goal
import com.cc221020.ccl3.data.TodoItem

data class MainViewState(
    val goals: List<Goal> = emptyList(),
    val todos: List<TodoItem> = emptyList(),
    var addGoal: Boolean = false
)