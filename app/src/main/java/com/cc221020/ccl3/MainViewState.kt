package com.cc221020.ccl3

import com.cc221020.ccl3.data.Goal
import com.cc221020.ccl3.data.TodoItem
import com.cc221020.ccl3.data.User

data class MainViewState(
    val goals: List<Goal> = emptyList(),
    val todos: List<TodoItem> = emptyList(),
    var addGoal: Boolean = false,
    var completed: Boolean = false,
    var showXpPopup: Boolean = false,
    var xpChange: Int = 0,
    var userInfo: User = User(),
    var showInfo: Boolean = false
)