package com.cc221020.ccl3

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cc221020.ccl3.data.Goal
import com.cc221020.ccl3.data.GoalDao
import com.cc221020.ccl3.data.TodoDao
import com.cc221020.ccl3.data.TodoItem
import com.cc221020.ccl3.data.User
import com.cc221020.ccl3.data.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class MainViewModel(
    private val goalDao: GoalDao,
    private val todoDao: TodoDao,
    private val userDao: UserDao
) : ViewModel() {
    private val _goalState = MutableStateFlow(Goal(title = "", completed = false))
    private val _mainViewState = MutableStateFlow(MainViewState())
    val mainViewState: StateFlow<MainViewState> = _mainViewState.asStateFlow()

    fun saveGoal(goal: Goal) {
        viewModelScope.launch {
            goalDao.insertGoal(goal)
        }
        closeAddWindow()
        getGoals()
    }

    fun getGoals() {
        viewModelScope.launch {
            goalDao.getGoal().collect { allGoals ->
                _mainViewState.update { it.copy(goals = allGoals) }
            }
        }
    }

    fun getTodos(goalId: Int) {
        viewModelScope.launch {
            todoDao.getTodoItem(goalId).collect { allTodos ->
                _mainViewState.update { it.copy(todos = allTodos) }
            }
        }
    }

    fun saveTodo(todoItem: TodoItem) {
        viewModelScope.launch {
            todoDao.insertTodoItem(todoItem)
        }
        closeAddWindow()
        getTodos(todoItem.goalId)
    }

    fun addGoal() {
        viewModelScope.launch {
            _mainViewState.update { it.copy(addGoal = true) }
        }
    }

    fun closeAddWindow() {
        viewModelScope.launch {
            _mainViewState.update { it.copy(addGoal = false) }
            _mainViewState.update { it.copy(completed = false) }
        }
    }

    fun editGoal(goal: Goal) {
        viewModelScope.launch {
            async { userAddXp(10) }.await()
            val data = async { getUser() }.await()
            data.let {
                async { updateUser(data.copy(goalsCompleted = data.goalsCompleted + 1)) }.await()
            }

            _mainViewState.update { it.copy(completed = false) }
            _goalState.update { it.copy(title = goal.title, completed = goal.completed) }

            deleteGoal(goal).join()
        }
        deleteGoal(goal)
    }

    //private
    fun deleteGoal(goal: Goal): Job {
        return viewModelScope.launch {
            goalDao.deleteGoal(goal = goal)
            getGoals()
        }
    }

    //private
    fun deleteTodo(todoItem: TodoItem) {
        viewModelScope.launch {
            todoDao.deleteTodoItem(todoItem)
        }
        getTodos(todoItem.goalId)
    }


    fun editTodo(todoItem: TodoItem) {
        viewModelScope.launch {
            _mainViewState.update { it.copy(completed = false) }
            _goalState.update { it.copy(title = todoItem.title, completed = todoItem.completed) }
        }
        deleteTodo(todoItem)
        getTodos(todoItem.goalId)
    }

    fun completeGoal(goal: Goal) {
        viewModelScope.launch {
            goalDao.updateGoal(Goal(id = goal.id, title = goal.title, completed = !goal.completed))
        }
        getGoals()
    }

    fun completeTodo(todoItem: TodoItem) {
        viewModelScope.launch {
            todoDao.updateTodoItem(
                TodoItem(
                    todoItem.id,
                    todoItem.title,
                    !todoItem.completed,
                    todoItem.goalId
                )
            )
        }
        getTodos(todoItem.goalId)
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            if (isUserInDatabase()) {
                val userData = async { getUser() }.await()
                if (userData != null) {
                    async { userDao.updateUser(user) }.await()
                }
            } else {
                userDao.insertUser(user)
            }
            _mainViewState.update { it.copy(userInfo = user) }
        }
    }

    suspend fun getUser(): User {
        return withContext(Dispatchers.IO) {
            userDao.getUser()
        }
    }


    suspend fun isUserInDatabase(): Boolean {
        return withContext(Dispatchers.IO) {
            val userCount = userDao.getUserCount()
            userCount > 0
        }
    }

    fun showPopup(xp: Int) {
        _mainViewState.update { it.copy(showXpPopup = true, xpChange = xp) }
    }

    fun closeXpPopup() {
        _mainViewState.update { it.copy(showXpPopup = false) }
    }

    fun userAddXp(xp: Int) {
        showPopup(xp)
        viewModelScope.launch {
            val data = getUser()
            if (data != null) {
                async {
                    updateUser(
                        data.copy(
                            xp = data.xp + xp,
                            xpGain = data.xpGain + xp
                        )
                    )
                }.await()
            }
        }
    }

    fun getUserData() {
        viewModelScope.launch {
            val data = async { getUser() }.await()
            if (data != null) {
                _mainViewState.update { it.copy(userInfo = data) }
            } else {
                updateUser(User())
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun checkTime() {

        val challenges: List<Int> = listOf(
            R.string.challenge1,
            R.string.challenge2,
            R.string.challenge3,
            R.string.challenge4,
            R.string.challenge5,
            R.string.challenge6,
            R.string.challenge7
        )

        viewModelScope.launch {

            val currentDateString = LocalDateTime.now(ZoneId.of("CET")).toLocalDate()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

            val data = getUser()
            if (data != null) {

                val stateUpdateJob = async { _mainViewState.update { it.copy(userInfo = data) } }
                stateUpdateJob.await()

                val lastUpdateTimeString = mainViewState.value.userInfo.lastTimeUpdate

                if (currentDateString != lastUpdateTimeString) {
                    var index = challenges.indexOf(mainViewState.value.userInfo.currentDaily)
                    index++
                    if (index >= challenges.size) {
                        index = 0
                    }
                    updateUser(
                        data.copy(
                            lastTimeUpdate = currentDateString,
                            currentDaily = challenges[index],
                            waterProgress = 0f,
                            dailyComplete = false,
                            goalsCompleted = 0,
                            foodScore = 0f,
                            xpGain = 0
                        )
                    )
                }
            }

        }
    }

    fun completeDaily() {

        viewModelScope.launch {

            async { userAddXp(20) }.await()

            val data: User = async { getUser() }.await()

            data.let {
                updateUser(it.copy(dailyComplete = true))
            }
        }
    }

    fun calcWellB() {

        viewModelScope.launch {

            var wb = 0

            val data: User = async { getUser() }.await()
            data.let {

                //                if (data.waterProgress >= data.waterGoal) wb = wb + data.xpGain / 10 - 1
                if (data.dailyComplete) wb = wb + data.xpGain / 10 - 1
                if (data.goalsCompleted >= 3) wb = wb + data.xpGain / 10 - 1
                if (data.foodScore >= 1) wb = wb + data.xpGain / 10 - 1

                updateUser(data.copy(wellBeingScore = wb))

            }
        }

    }

    fun openInfo() {
        _mainViewState.update { it.copy(showInfo = true) }
    }

    fun closeInfo() {
        _mainViewState.update { it.copy(showInfo = false) }
    }

}
