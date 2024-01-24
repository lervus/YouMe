package com.cc221020.ccl3.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cc221020.ccl3.MainViewModel
import com.cc221020.ccl3.ui.components.InfoBox
import com.cc221020.ccl3.ui.components.InfoPopup

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(mainViewModel: MainViewModel) {
    val state = mainViewModel.mainViewState.collectAsState()
    mainViewModel.getUserData()
    val navController = rememberNavController()
    Scaffold {
        NavHost(
            navController = navController,
            modifier = Modifier.padding(it),
            startDestination = "avatar"
        ) {
            composable("me") { MeView(navController, mainViewModel) }
            composable("you") { YouView(navController, mainViewModel) }
            composable("avatar") { Avatar(navController, mainViewModel) }
            composable("goalView/{goalId}") { backStackEntry ->
                val goalId = backStackEntry.arguments?.getString("goalId")
                val goal = state.value.goals.firstOrNull { it.id.toString() == goalId }
                if (goal != null) {
                    GoalView(navController, mainViewModel, goal)
                }
            }
            composable("settings") { SettingView(navController, mainViewModel) }
        }
        InfoPopup(mainViewModel)
        InfoBox(mainViewModel)
    }
}