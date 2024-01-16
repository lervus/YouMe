package com.cc221020.ccl3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cc221020.ccl3.data.Goal
import com.cc221020.ccl3.data.GoalDao
import com.cc221020.ccl3.data.TodoDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(private val goalDao: GoalDao, private val todoDao: TodoDao) : ViewModel() {
    private val _goalState = MutableStateFlow(Goal(0, "", false))
    val goalState: StateFlow<Goal> = _goalState.asStateFlow()
    private val _mainViewState = MutableStateFlow(MainViewState())
    val mainViewState: StateFlow<MainViewState> = _mainViewState.asStateFlow()

    fun saveGoal(goal: Goal){
        viewModelScope.launch {
            goalDao.insertGoal(goal)
        }
        getGoals()
    }

    fun getGoals(){
        viewModelScope.launch {
            goalDao.getGoal().collect(){ allGoals ->
                _mainViewState.update { it.copy(goals = allGoals) }
            }
        }
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
