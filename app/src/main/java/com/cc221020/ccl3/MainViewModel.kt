package com.cc221020.ccl3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cc221020.ccl3.data.Goal
import com.cc221020.ccl3.data.GoalDao
import com.cc221020.ccl3.data.TodoDao
import com.cc221020.ccl3.data.TodoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(private val goalDao: GoalDao, private val todoDao: TodoDao) : ViewModel() {
    private val _goalState = MutableStateFlow(Goal(title = "", completed =  false))
    val goalState: StateFlow<Goal> = _goalState.asStateFlow()
    private val _mainViewState = MutableStateFlow(MainViewState())
    val mainViewState: StateFlow<MainViewState> = _mainViewState.asStateFlow()

    fun saveGoal(goal: Goal){
        viewModelScope.launch {
            goalDao.insertGoal(goal)
        }
        closeAddWindow()
        getGoals()
    }

    fun getGoals(){
        viewModelScope.launch {
            goalDao.getGoal().collect(){ allGoals ->
                _mainViewState.update { it.copy(goals = allGoals) }
            }
        }
    }

    fun getTodos(goalId: Int){
        viewModelScope.launch {
            todoDao.getTodoItem(goalId).collect(){ allTodos ->
                _mainViewState.update { it.copy(todos = allTodos) }
            }
        }
    }

    fun saveTodo(todoItem: TodoItem){
        viewModelScope.launch {
            todoDao.insertTodoItem(todoItem)
        }
        closeAddWindow()
        getTodos(todoItem.goalId)
    }

    fun addGoal(){
        viewModelScope.launch {
            _mainViewState.update { it.copy(addGoal = true) }
        }
    }

    fun closeAddWindow(){

        _mainViewState.update { it.copy(addGoal = false) }

    }

    fun deleteGoal(goal: Goal){
        viewModelScope.launch {
            goalDao.deleteGoal(goal = goal)
        }
        getGoals()
    }

    fun deleteTodo(todoItem: TodoItem){
        viewModelScope.launch {
            todoDao.deleteTodoItem(todoItem)
        }
        getTodos(todoItem.goalId)
    }

    fun completeGoal(it: Goal) {

    }

    /*
    fun getSpecificItems(itemType: String){
        viewModelScope.launch{
            dao.getSpecificItem(itemType).collect(){ allItems ->
                _mainViewState.update { it.copy(items = allItems) }
            }
        }
    }

    fun editItem(item: ShelfItem){
        viewModelScope.launch{
            _itemState.value = item
            _mainViewState.update { it.copy(openDialog = true) }
        }
    }

    fun updateItem(item: ShelfItem, itemType: String){
        viewModelScope.launch {
            dao.updateItem(item)
        }
        //getSpecificItems(itemType)
        closeDialog()
    }*/
}
