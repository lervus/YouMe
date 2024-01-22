package com.cc221020.ccl3.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cc221020.ccl3.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(mainViewModel: MainViewModel) {
    val state = mainViewModel.mainViewState.collectAsState()
    val navController = rememberNavController()
    Scaffold {
        NavHost(
            navController = navController,
            modifier = Modifier.padding(it),
            startDestination = "enter"
        ) {
            composable("me") { MeView(navController) }
            composable("you") { YouView(navController, mainViewModel) }
            composable("avatar") { Avatar(navController, mainViewModel) }
            composable("enter") { Enter(navController) }
            composable("goalView/{goalId}") { backStackEntry ->
                val goalId = backStackEntry.arguments?.getString("goalId")
                val goal = state.value.goals.firstOrNull { it.id.toString() == goalId }
                if (goal != null) {
                    GoalView(navController, mainViewModel, goal)
                }
            }
        }
    }
}