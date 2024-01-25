package com.cc221020.ccl3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cc221020.ccl3.MainViewModel
import com.cc221020.ccl3.data.Goal
import com.cc221020.ccl3.ui.components.AddWindow
import com.cc221020.ccl3.ui.components.RotatingIconButton
import com.cc221020.ccl3.ui.components.TodoList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalView(navController: NavController, mainViewModel: MainViewModel, goal: Goal) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "TO-DO´s",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                modifier = Modifier.background(MaterialTheme.colorScheme.background),
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Go Back",
                        modifier = Modifier
                            .clickable {
                                navController.navigate("you")
                            }
                            .size(50.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                actions = {
                    RotatingIconButton(
                        onClick = { navController.navigate("settings") },
                        imageVector = Icons.Filled.Settings
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.height(8.dp))
            Spacer(modifier = Modifier.height(96.dp)) // replaces challenge of the day
            /*
            Text(text = "Challenge of the day:")
            Text(
                text = stringResource(id = R.string.challenge7),
                style = MaterialTheme.typography.titleSmall,
            )
            IconButton(onClick = {
                mainViewModel.userAddXp(10)
                // mainViewModel.completeDaily(it)
            }
            ) {
                Icon(
                    Icons.Default.CheckCircle,
                    "Complete",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            */

            Card(
                modifier = Modifier
                    .height(500.dp)
                    .width(300.dp)
                    .padding(16.dp)
                    .shadow(elevation = 10.dp, shape = MaterialTheme.shapes.medium),
                shape = MaterialTheme.shapes.medium
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.onSecondary)
                        .padding(16.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    TodoList(
                        navController = navController,
                        mainViewModel = mainViewModel,
                        goal = goal
                    )
                }
            }

            Button(
                onClick = { mainViewModel.addGoal() },
                modifier = Modifier
                    .padding(16.dp)
                    .size(75.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 5.dp,
                    pressedElevation = 1.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                ),
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add button")
            }
            // BackButton(navController = navController)
        }
        AddWindow(mainViewModel = mainViewModel, goalId = goal.id)
    }
}
