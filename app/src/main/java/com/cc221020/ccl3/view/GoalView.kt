package com.cc221020.ccl3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.cc221020.ccl3.MainViewModel
import com.cc221020.ccl3.data.Goal
import com.cc221020.ccl3.ui.components.AddWindow
import com.cc221020.ccl3.ui.components.BackButton
import com.cc221020.ccl3.ui.components.GoalList
import com.cc221020.ccl3.ui.components.TodoList

@Composable
fun GoalView(navController: NavController, mainViewModel: MainViewModel, goal: Goal){
    Text(text = goal.title)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        BackButton(navController = navController)
        Text(text = "Goals")
        Box(modifier = Modifier.
        background(Color.Gray)){
            Button(onClick = { mainViewModel.addGoal() }){
                Text(text = "Add Todo")
            }
        }
    }
    AddWindow(mainViewModel = mainViewModel, goalId = goal.id)
    TodoList(navController, mainViewModel, goal.id)
}